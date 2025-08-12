package com.example.backend.justrent.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVerifyDto {
    private String email;
    private String verificationCode;
}
