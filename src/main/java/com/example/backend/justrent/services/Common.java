package com.example.backend.justrent.services;

import org.springframework.stereotype.Service;

@Service
public class Common {

    public String generateUserId(String shortCode) {
        StringBuilder sb = new StringBuilder();
        sb.append(shortCode);
        for (int i = 0; i < 5; i++) {
            sb.append((int)(Math.random() * 10));
        }
        return sb.toString();
    }

    
}
