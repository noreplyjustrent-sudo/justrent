package com.example.backend.justrent.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.justrent.dto.RentalOrderCreateDto;
import com.example.backend.justrent.model.RentalOrder;
import com.example.backend.justrent.services.RentalOrderService;

@RestController
@RequestMapping("/api/rental-orders")
public class RentalOrderController {

    @Autowired
    private RentalOrderService rentalOrderService;

    // Create a new rental order

    @PostMapping("/create")
    public ResponseEntity<RentalOrder> createRentalOrder(@RequestBody RentalOrderCreateDto dto) {
        RentalOrder order = rentalOrderService.createRentalOrder(dto);
        return ResponseEntity.ok(order);
    }

    // Delete rental order by ID
    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<Void> deleteRentalOrder(@PathVariable String orderId) {
        rentalOrderService.deleteRentalOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    // Get all rental orders
    @GetMapping("/get-all")
    public ResponseEntity<List<RentalOrder>> getAllRentalOrders() {
        List<RentalOrder> orders = rentalOrderService.getAllRentalOrders();
        return ResponseEntity.ok(orders);
    }

    // Get rental order by ID
    @GetMapping("/get/{orderId}")
    public ResponseEntity<RentalOrder> getRentalOrderById(@PathVariable String orderId) {
        Optional<RentalOrder> order = rentalOrderService.getRentalOrderById(orderId);
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all rental orders by rentalId
    @GetMapping("/by-rental")
    public ResponseEntity<List<RentalOrder>> getRentalOrdersByRentalId(@RequestParam String rentalId) {
        List<RentalOrder> orders = rentalOrderService.getRentalOrdersByRentalId(rentalId);
        return ResponseEntity.ok(orders);
    }
}