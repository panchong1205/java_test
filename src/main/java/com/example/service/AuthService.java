package com.example.service;

import com.example.dto.LoginResponse;
import com.example.dto.UserDto;
import com.example.model.User;
import com.example.repository.InMemoryTokenStore;
import com.example.repository.InMemoryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    private final InMemoryUserRepository userRepo;
    private final InMemoryTokenStore tokenStore;

    @Autowired
    public AuthService(InMemoryUserRepository userRepo, InMemoryTokenStore tokenStore) {
        this.userRepo = userRepo;
        this.tokenStore = tokenStore;
    }

    public LoginResponse login(String username, String password) {
        User user = userRepo.findByUsername(username);
        if (user == null) return null;
        // 示例为明文对比，生产请使用 BCrypt 等哈希比对
        if (!user.password.equals(password)) return null;

        String token = UUID.randomUUID().toString();
        tokenStore.store(token, user.id);

        UserDto dto = new UserDto(user.id, user.username, user.displayName, user.email);
        return new LoginResponse(token, dto);
    }
}
