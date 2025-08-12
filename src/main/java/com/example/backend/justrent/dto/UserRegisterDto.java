package com.example.backend.justrent.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDto {
    private String email;
    private String password;
    private String username;
    private String firstName;
    private String lastName;
    private String Gender;


}
