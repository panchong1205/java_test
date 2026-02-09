package com.example.controller;

import com.example.Application;
import com.example.dto.ItemDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateItem() throws Exception {
        ItemDto itemDto = new ItemDto("Test Item", "Test Description");
        
        mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Test Item"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    public void testGetAllItems() throws Exception {
        // Create an item first
        ItemDto itemDto = new ItemDto("Item 1", "Description 1");
        mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemDto)))
                .andExpect(status().isCreated());

        // Get all items
        mockMvc.perform(get("/api/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetItemById() throws Exception {
        // Create an item first
        ItemDto itemDto = new ItemDto("Item 2", "Description 2");
        MvcResult result = mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemDto)))
                .andExpect(status().isCreated())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Long itemId = objectMapper.readTree(response).get("id").asLong();

        // Get the item by id
        mockMvc.perform(get("/api/items/" + itemId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(itemId))
                .andExpect(jsonPath("$.name").value("Item 2"))
                .andExpect(jsonPath("$.description").value("Description 2"));
    }

    @Test
    public void testGetItemByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/items/999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateItem() throws Exception {
        // Create an item first
        ItemDto itemDto = new ItemDto("Item 3", "Description 3");
        MvcResult result = mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemDto)))
                .andExpect(status().isCreated())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Long itemId = objectMapper.readTree(response).get("id").asLong();

        // Update the item
        ItemDto updatedDto = new ItemDto("Updated Item", "Updated Description");
        mockMvc.perform(put("/api/items/" + itemId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(itemId))
                .andExpect(jsonPath("$.name").value("Updated Item"))
                .andExpect(jsonPath("$.description").value("Updated Description"));
    }

    @Test
    public void testUpdateItemNotFound() throws Exception {
        ItemDto updatedDto = new ItemDto("Updated Item", "Updated Description");
        mockMvc.perform(put("/api/items/999999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteItem() throws Exception {
        // Create an item first
        ItemDto itemDto = new ItemDto("Item 4", "Description 4");
        MvcResult result = mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemDto)))
                .andExpect(status().isCreated())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Long itemId = objectMapper.readTree(response).get("id").asLong();

        // Delete the item
        mockMvc.perform(delete("/api/items/" + itemId))
                .andExpect(status().isNoContent());

        // Verify the item is deleted
        mockMvc.perform(get("/api/items/" + itemId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteItemNotFound() throws Exception {
        mockMvc.perform(delete("/api/items/999999"))
                .andExpect(status().isNotFound());
    }
}
