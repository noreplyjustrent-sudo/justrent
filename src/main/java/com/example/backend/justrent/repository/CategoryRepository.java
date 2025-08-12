package com.example.backend.justrent.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.justrent.model.Category;



@Repository
public interface CategoryRepository extends CrudRepository<Category, String> {
    Optional<Category> findByName(String name);
}