package com.example.dto;

public class UserDto {
    public String id;
    public String username;
    public String displayName;
    public String email;

    public UserDto() {}
    public UserDto(String id, String username, String displayName, String email) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.email = email;
    }
}
