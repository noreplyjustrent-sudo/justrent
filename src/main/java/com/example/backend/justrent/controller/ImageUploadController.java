package com.example.backend.justrent.controller;

import com.example.backend.justrent.model.ImageUploadResponse;
import com.example.backend.justrent.services.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageUploadController {

    @Autowired
    private ImageUploadService imageUploadService;

    @PostMapping("/upload")
    public ResponseEntity<ImageUploadResponse> uploadImages(@RequestParam("files") MultipartFile[] files) {
        try {
            ImageUploadResponse response = imageUploadService.uploadImages(files);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}