package com.example.backend.justrent.dto;

import java.util.List;

import com.example.backend.justrent.model.Rental;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class userResponse {
    private String id;
    private String name;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private List<Rental> favoriteRentals;
     private boolean enabled;

    public userResponse(String id, String email, String firstName, String lastName, String gender, List<Rental> favoriteRentals, boolean enabled) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.favoriteRentals = favoriteRentals;
        this.enabled = enabled;
    }

    

}