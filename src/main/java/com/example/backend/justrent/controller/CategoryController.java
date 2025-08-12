package com.example.backend.justrent.controller;

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

import com.example.backend.justrent.dto.CategoryCreateDto;
import com.example.backend.justrent.model.Category;
import com.example.backend.justrent.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Create category
    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryCreateDto dto) {
        Category category = categoryService.createCategory(dto.getName(), dto.getDescription());
        return ResponseEntity.ok(category);
    }

    // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable String id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all categories
    @GetMapping("/getall")
    public ResponseEntity<Iterable<Category>> getAllCategories() {
        Iterable<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // Delete category
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }

    // Edit category
    @PutMapping("/edit/{id}")
    public ResponseEntity<Category> editCategory(@PathVariable String id, @RequestBody CategoryCreateDto dto) {
        Category updated = categoryService.editCategory(id, dto.getName(), dto.getDescription());
        return ResponseEntity.ok(updated);
    }
}