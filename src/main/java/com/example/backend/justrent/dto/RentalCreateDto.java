package com.example.backend.justrent.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalCreateDto {
    private String categoryId;
    private String ownerId;
    private String itemName;
    private String description;
    private String status;
    private double pricePerDay;
    private String lat;
    private String lon;
    private List<String> images;

   
}
