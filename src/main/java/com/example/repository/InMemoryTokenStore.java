package com.example.repository;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryTokenStore {
    private final Map<String, String> tokenToUser = new ConcurrentHashMap<>();

    public void store(String token, String userId) {
        tokenToUser.put(token, userId);
    }

    public String getUserIdByToken(String token) {
        return tokenToUser.get(token);
    }
}
