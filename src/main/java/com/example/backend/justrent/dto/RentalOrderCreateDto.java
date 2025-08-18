package com.example.backend.justrent.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalOrderCreateDto {
    private String rentalId;
    private String userId;
    private Date startDate;
    private Date endDate;
    private String deliveryOption;
    private String message;
}