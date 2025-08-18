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

