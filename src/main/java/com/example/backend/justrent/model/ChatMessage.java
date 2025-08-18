package com.example.backend.justrent.model;

import java.util.Date;
import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "chat_messages")
@Getter
@Setter
public class ChatMessage {
    @Id
    @Column(name = "chat_id", unique = true, nullable = false)
    private String chatId;

    @Column(name = "sender_id", nullable = false)
    private String senderId;

    @Column(name = "receiver_id", nullable = false)
    private String receiverId;

    @Column(name = "message", nullable = false, length = 1000)
    private String message;

    // Add this field to ChatMessage.java
    @Column(name = "rental_id", nullable = false)
    private String rentalId;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    public ChatMessage() {
        this.chatId = generateChatId();
        this.timestamp = new Date();
    }

    public ChatMessage(String senderId, String receiverId, String message, String rentalId) {
        this.chatId = generateChatId();
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.rentalId = rentalId;
        this.timestamp = new Date();
    }

    private String generateChatId() {
        Random random = new Random();
        int number = 10000 + random.nextInt(90000);
        return "CHAT-" + number;
    }
}