package com.example.backend.justrent.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageUploadResponse {
    private List<String> urls;

    public ImageUploadResponse(List<String> urls) {
        this.urls = urls;
    }
}