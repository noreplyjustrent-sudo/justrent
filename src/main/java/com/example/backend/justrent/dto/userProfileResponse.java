package com.example.backend.justrent.dto;

import java.util.List;

import com.example.backend.justrent.model.Rental;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class userProfileResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private float rating;
    private int rentals;
    private int rented;
    private List<Rental>myRental;
    private List<Rental>myRentalPost;
    private List<Rental>favRentals;

    

    public userProfileResponse(String userId, String firstName, String lastName, String email, String username, int rentals, int rented, List<Rental> myRental, List<Rental> myRentalPost, List<Rental> favRentals, float rating) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.rentals = rentals;
        this.rented = rented;
        this.myRental = myRental;
        this.myRentalPost = myRentalPost;
        this.favRentals = favRentals;
        this.rating = rating;
    }

    // Getters and Setters
}
