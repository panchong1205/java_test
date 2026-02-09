package com.example.dto;

import jakarta.validation.constraints.NotBlank;

public class ItemDto {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    
    private String description;

    public ItemDto() {}

    public ItemDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
