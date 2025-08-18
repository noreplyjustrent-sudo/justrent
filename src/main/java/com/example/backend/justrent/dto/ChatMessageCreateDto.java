package com.example.backend.justrent.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageCreateDto {
    private String senderId;
    private String receiverId;
    private String rentalId;
    private String message;
}