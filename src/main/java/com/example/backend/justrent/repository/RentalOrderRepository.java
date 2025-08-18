package com.example.backend.justrent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.justrent.model.RentalOrder;

@Repository
public interface RentalOrderRepository extends JpaRepository<RentalOrder, String> {
}