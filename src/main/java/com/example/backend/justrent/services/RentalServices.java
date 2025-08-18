package com.example.backend.justrent.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.justrent.dto.RentalCreateDto;
import com.example.backend.justrent.model.Rental;
import com.example.backend.justrent.repository.RentalRepository;

@Service
public class RentalServices {

    @Autowired
    private RentalRepository rentalRepository;

    // Create rental
    public Rental createRental(RentalCreateDto rental) {
        Rental newRental = new Rental(rental.getCategoryId(), rental.getOwnerId(), rental.getItemName(), rental.getDescription(), rental.getStatus(), rental.getPricePerDay(), rental.getLat(), rental.getLon(), rental.getImages());
        return rentalRepository.save(newRental);
    }

    // Get rental by ID
    public Optional<Rental> getRentalById(String id) {
        return rentalRepository.findById(id);
    }

    // Get all rentals
    public List<Rental> getAllRentals() {
        return (List<Rental>) rentalRepository.findAll();
    }

    // Update rental
    public Rental updateRental(String id, Rental updatedRental) {
        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if (optionalRental.isPresent()) {
            Rental rental = optionalRental.get();
            rental.setCategoryId(updatedRental.getCategoryId());
            rental.setOwnerId(updatedRental.getOwnerId());
            rental.setItemName(updatedRental.getItemName());
            rental.setDescription(updatedRental.getDescription());
            rental.setStatus(updatedRental.getStatus());
            rental.setPricePerDay(updatedRental.getPricePerDay());
            rental.setLat(updatedRental.getLat());
            rental.setLon(updatedRental.getLon());
            rental.setImages(updatedRental.getImages());
            return rentalRepository.save(rental);
        } else {
            throw new RuntimeException("Rental not found");
        }
    }

    // Delete rental
    public void deleteRental(String id) {
        rentalRepository.deleteById(id);
    }

    public List<Rental> getRentalsByIds(List<String> ids) {
        return rentalRepository.findByIdIn(ids);
    }

    public List<Rental> getRentalsByOwnerId(String userId) {
        return rentalRepository.findByOwnerId(userId);
    }
}
