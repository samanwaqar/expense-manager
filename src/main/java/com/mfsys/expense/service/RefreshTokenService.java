package com.mfsys.expense.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final Map<String, String> store = new HashMap<>();

    // create refresh token
    public String createRefreshToken(String email) {
        String token = UUID.randomUUID().toString();
        store.put(token, email);
        return token;
    }

    // validate refresh token
    public String validateRefreshToken(String token) {
        return store.get(token); // returns email or null
    }

    // delete refresh token (logout optional)
    public void deleteRefreshToken(String token) {
        store.remove(token);
    }
}
