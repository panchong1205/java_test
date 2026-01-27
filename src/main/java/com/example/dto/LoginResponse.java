package com.example.dto;

public class LoginResponse {
    public String token;
    public UserDto user;

    public LoginResponse() {}
    public LoginResponse(String token, UserDto user) {
        this.token = token;
        this.user = user;
    }
}
