package com.example.backend.justrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.backend.justrent.dto.ChatMessageCreateDto;
import com.example.backend.justrent.model.ChatMessage;
import com.example.backend.justrent.services.ChatMessageService;

@Controller
public class ChatWebSocketController {

    @Autowired
    private ChatMessageService chatMessageService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessageCreateDto dto) {
        return chatMessageService.sendMessage(dto);
    }
}