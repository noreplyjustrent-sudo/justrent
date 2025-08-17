package com.example.backend.justrent.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.justrent.model.Rental;


@Repository
public interface RentalRepository  extends CrudRepository<Rental, String> {
    List<Rental> findByOwnerId(String ownerId);
    List<Rental> findByIdIn(List<String> rentalIds);
}
