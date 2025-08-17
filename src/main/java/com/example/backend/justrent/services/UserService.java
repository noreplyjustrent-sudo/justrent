package com.example.backend.justrent.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.justrent.dto.userResponse;
import com.example.backend.justrent.model.Rental;
import com.example.backend.justrent.model.User;
import com.example.backend.justrent.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RentalServices rentalServices;

    @Autowired
    public UserService(UserRepository userRepository, EmailService emailService, RentalServices rentalServices) {
        this.userRepository = userRepository;
        this.rentalServices = rentalServices;
    }

    public List<userResponse> allUsers() {
        List<userResponse> responses = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            List<Rental> favoriteRentals = new ArrayList<>();
            if (user.getFavouriteRentals() != null && !user.getFavouriteRentals().isEmpty()) {
                favoriteRentals = rentalServices.getRentalsByIds(user.getFavouriteRentals());
            }
            userResponse resp = new userResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getGender(),
                favoriteRentals,
                user.isEnabled()
            );
            responses.add(resp);
        });
        return responses;
    }

    public Optional<userResponse> getUserById(String id) {
        return userRepository.findById(id).map(user -> {
            List<Rental> favoriteRentals = new ArrayList<>();
            if (user.getFavouriteRentals() != null && !user.getFavouriteRentals().isEmpty()) {
                favoriteRentals = rentalServices.getRentalsByIds(user.getFavouriteRentals());
            }
            return new userResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getGender(),
                favoriteRentals,
                user.isEnabled()
            );
        });
    }

    public String addRentalToFavorites(String userId, String rentalId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            List<String> favorites = user.getFavouriteRentals();
            if (favorites == null) {
                favorites = new ArrayList<>();
            }
            if (!favorites.contains(rentalId)) {
                favorites.add(rentalId);
                user.setFavouriteRentals(favorites);
                userRepository.save(user);
                return "Added successfully";
            } else {
                return "Rental already in favorites";
            }
        }
        return "Error in adding";
    }
}
