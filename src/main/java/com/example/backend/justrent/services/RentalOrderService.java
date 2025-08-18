package com.example.backend.justrent.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.justrent.dto.RentalOrderCreateDto;
import com.example.backend.justrent.model.Rental;
import com.example.backend.justrent.model.RentalOrder;
import com.example.backend.justrent.model.User;
import com.example.backend.justrent.repository.RentalOrderRepository;
import com.example.backend.justrent.repository.RentalRepository;
import com.example.backend.justrent.repository.UserRepository;

@Service
public class RentalOrderService {

    @Autowired
    private RentalOrderRepository rentalOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentalRepository rentalRepository;

    // Create a new rental order
    public RentalOrder createRentalOrder(RentalOrderCreateDto dto) {
        Optional<User> user = userRepository.findById(dto.getUserId());
  
        RentalOrder order = new RentalOrder(// orderId will be generated in constructor
            dto.getRentalId(),
            dto.getUserId(),user.get().getFirstName()+ " " + user.get().getLastName(),
            dto.getStartDate(), 
            dto.getEndDate(),
            dto.getDeliveryOption(),
            dto.getMessage()
        );
        return rentalOrderRepository.save(order);
    }

    // Delete rental order by ID
    public void deleteRentalOrder(String orderId) {
        rentalOrderRepository.deleteById(orderId);
    }

    // Get all rental orders
    public List<RentalOrder> getAllRentalOrders() {
        return rentalOrderRepository.findAll();
    }

    // Get rental order by ID
    public Optional<RentalOrder> getRentalOrderById(String orderId) {
        return rentalOrderRepository.findById(orderId);
    }

    // Get all rental orders by rentalId
    public List<RentalOrder> getRentalOrdersByRentalId(String rentalId) {
        return rentalOrderRepository.findAll()
            .stream()
            .filter(order -> order.getRentalId().equals(rentalId))
            .toList();
    }

    // Get all rentals ordered by a user (using userId)
    public List<Rental> getRentalsOrderedByUser(String userId) {
        // Find all orders by userId
        List<RentalOrder> orders = rentalOrderRepository.findAll()
            .stream()
            .filter(order -> order.getUserId().equals(userId))
            .toList();

        // Collect rentalIds from orders
        List<String> rentalIds = new ArrayList<>();
        for (RentalOrder order : orders) {
            rentalIds.add(order.getRentalId());
        }

        // Fetch rentals by rentalIds
        List<Rental> rentals = new ArrayList<>();
        for (String rentalId : rentalIds) {
            rentalRepository.findById(rentalId).ifPresent(rentals::add);
        }

        return rentals;
    }

}