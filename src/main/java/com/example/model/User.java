package com.example.model;

public class User {
    public String id;
    public String username;
    public String password; // 示例为明文，生产请使用哈希
    public String displayName;
    public String email;

    public User() {}
    public User(String id, String username, String password, String displayName, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.email = email;
    }
}
