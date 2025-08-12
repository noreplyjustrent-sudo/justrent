package com.example.backend.justrent.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.justrent.dto.RentalCreateDto;
import com.example.backend.justrent.model.Rental;
import com.example.backend.justrent.services.RentalServices;

@RestController
@RequestMapping("api/rentals")
public class RentalController {

    @Autowired
    private RentalServices rentalServices;

    // Create rental
    @PostMapping("/create")
    public ResponseEntity<Rental> createRental(@RequestBody RentalCreateDto dto) {
        Rental rental = rentalServices.createRental(dto);
        return ResponseEntity.ok(rental);
    }

    // Get rental by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable String id) {
        Optional<Rental> rental = rentalServices.getRentalById(id);
        return rental.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all rentals
    @GetMapping("/getall") 
    public ResponseEntity<List<Rental>> getAllRentals() {
        List<Rental> rentals = rentalServices.getAllRentals();
        return ResponseEntity.ok(rentals);
    }

    // Update rental
    @PutMapping("/update/{id}")
    public ResponseEntity<Rental> updateRental(@PathVariable String id, @RequestBody RentalCreateDto dto) {
        Rental updatedRental = new Rental(
            dto.getCategoryId(),
            dto.getOwnerId(),
            dto.getItemName(),
            dto.getDescription(),
            dto.getStatus(),
            dto.getPricePerDay(),
            dto.getLat(),
            dto.getLon(),
            dto.getImages()
        );
        Rental rental = rentalServices.updateRental(id, updatedRental);
        return ResponseEntity.ok(rental);
    }

    // Delete rental
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable String id) {
        rentalServices.deleteRental(id);
        return ResponseEntity.noContent().build();
    }
}

/*
API Endpoints:

1. POST /api/rentals/create
    - Description: Create a new rental.
    - Request Body Example:
      {
         "categoryId": "cat123",
         "ownerId": "owner456",
         "itemName": "Mountain Bike",
         "description": "A sturdy mountain bike for rent.",
         "status": "AVAILABLE",
         "pricePerDay": 15.0,
         "lat": 37.7749,
         "lon": -122.4194,
         "images": ["image1.jpg", "image2.jpg"]
      }

2. GET /api/rentals/get/{id}
    - Description: Get rental details by rental ID.
    - No request body.

3. GET /api/rentals/getall
    - Description: Get all rentals.
    - No request body.

4. PUT /api/rentals/update/{id}
    - Description: Update an existing rental by ID.
    - Request Body Example:
      {
         "categoryId": "cat123",
         "ownerId": "owner456",
         "itemName": "Updated Mountain Bike",
         "description": "Updated description.",
         "status": "RENTED",
         "pricePerDay": 18.0,
         "lat": 37.7750,
         "lon": -122.4195,
         "images": ["image1.jpg", "image3.jpg"]
      }

5. DELETE /api/rentals/delete/{id}
    - Description: Delete a rental by ID.
    - No request body.
*/