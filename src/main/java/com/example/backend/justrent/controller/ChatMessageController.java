package com.example.backend.justrent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.justrent.dto.ChatMessageCreateDto;
import com.example.backend.justrent.dto.userResponse;
import com.example.backend.justrent.model.ChatMessage;
import com.example.backend.justrent.repository.RentalRepository;
import com.example.backend.justrent.services.ChatMessageService;

@RestController
@RequestMapping("/api/chat")
public class ChatMessageController {

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private RentalRepository rentalRepository;

    // Send a message
    @PostMapping("/send")
    public ResponseEntity<ChatMessage> sendMessage(@RequestBody ChatMessageCreateDto dto) {
        ChatMessage savedMessage = chatMessageService.sendMessage(dto);
        return ResponseEntity.ok(savedMessage);
    }

    // Get all messages between two users
    @GetMapping("/messages")
    public ResponseEntity<List<ChatMessage>> getMessagesBetweenUsers(
            @RequestParam String userAId,
            @RequestParam String userBId,
            @RequestParam String rentalId) {
        List<ChatMessage> messages = chatMessageService.getMessagesForRentalBetweenUsers(rentalId, userAId, userBId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/list-users")
    public ResponseEntity<List<userResponse>> getChatUsersForRental(@RequestParam String rentalId) {
        List<userResponse> users = chatMessageService.getChatUsersForRental(rentalId);
        return ResponseEntity.ok(users);
    }

}