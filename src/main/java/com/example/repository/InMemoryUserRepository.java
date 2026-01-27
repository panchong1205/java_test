package com.example.repository;

import com.example.model.User;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserRepository {
    private final Map<String, User> users = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        // 示例用户，生产请使用数据库与密码哈希
        User u = new User("1", "admin", "password123", "管理员", "admin@example.com");
        users.put(u.username, u);
    }

    public User findByUsername(String username) {
        return users.get(username);
    }
}
