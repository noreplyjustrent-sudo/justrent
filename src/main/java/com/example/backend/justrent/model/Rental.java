package com.example.backend.justrent.model;

import java.util.Date;
import java.util.List;

import com.example.backend.justrent.services.Common;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "rental")
@Getter
@Setter
public class Rental {
    private String shortcode="RET-";
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;
    private String categoryId;
    private String ownerId;
    private String itemName;
    private String description;
    private String status;
    private double pricePerDay;
    private String lat;
    private String lon;
    private List<String> images;
    private List<String> chatUserIds;
    private List<Date> notAvailableDates;

    public Rental( String categoryId, String ownerId, String itemName, String description, String status, double pricePerDay, String lat, String lon, List<String> images) {
        Common common = new Common();
        this.id = common.generateUserId(shortcode);
        this.categoryId = categoryId;
        this.ownerId = ownerId;
        this.itemName = itemName;
        this.description = description;
        this.status = status;
        this.pricePerDay = pricePerDay;
        this.lat = lat;
        this.lon = lon;
        this.images = images;
    }
    // Getters and Setters

    public Rental() {
        Common common = new Common();
        this.id = common.generateUserId(shortcode);
    }
}