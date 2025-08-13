package com.example.backend.justrent.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private long expiresIn;
    private String id;
    private Boolean isVerified;

    public LoginResponse(String token, long expiresIn, String id, Boolean isVerified) {
        this.token = token;
        this.expiresIn = expiresIn;
        this.id = id;
        this.isVerified = isVerified;
    }
}
