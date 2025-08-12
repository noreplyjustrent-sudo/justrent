package com.example.backend.justrent.services;

import com.example.backend.justrent.model.Category;
import com.example.backend.justrent.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Create a new category
    public Category createCategory(String name, String description) {
        Category category = new Category(name, description);
        return categoryRepository.save(category);
    }

    // Get category by ID
    public Optional<Category> getCategoryById(String id) {
        return categoryRepository.findById(id);
    }

    // Get all categories
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Delete category by ID
    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }

    // Edit (update) category
    public Category editCategory(String id, String name, String description) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(name);
            category.setDescription(description);
            return categoryRepository.save(category);
        } else {
            throw new RuntimeException("Category not found");
        }
    }
}