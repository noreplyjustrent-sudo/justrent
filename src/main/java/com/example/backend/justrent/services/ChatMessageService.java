package com.example.backend.justrent.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.justrent.dto.ChatMessageCreateDto;
import com.example.backend.justrent.dto.userResponse;
import com.example.backend.justrent.model.ChatMessage;
import com.example.backend.justrent.model.Rental;
import com.example.backend.justrent.repository.ChatMessageRepository;
import com.example.backend.justrent.repository.RentalRepository;
import com.example.backend.justrent.repository.UserRepository;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    // Save a new chat message (send message)
    public ChatMessage sendMessage(ChatMessageCreateDto dto) {
        ChatMessage chatMessage = new ChatMessage(dto.getSenderId(), dto.getReceiverId(), dto.getMessage(), dto.getRentalId());

        // After saving a ChatMessage
        Rental rental = rentalRepository.findById(chatMessage.getRentalId()).orElse(null);
        if (rental != null && !(rental.getOwnerId().equals(chatMessage.getSenderId()))) {
            List<String> chatUserIds = rental.getChatUserIds();
            if (chatUserIds == null) {
                chatUserIds = new java.util.ArrayList<>();
            }
            if (!chatUserIds.contains(chatMessage.getSenderId())) {
                chatUserIds.add(chatMessage.getSenderId());
                rental.setChatUserIds(chatUserIds);
                rentalRepository.save(rental);
            }
        }
        return chatMessageRepository.save(chatMessage);
    }

    // Get all messages between two users for a rental, regardless of who is sender or receiver
    public List<ChatMessage> getMessagesForRentalBetweenUsers(String rentalId, String userAId, String userBId) {
        return chatMessageRepository.findByRentalIdAndSenderIdInAndReceiverIdInOrderByTimestampAsc(
                rentalId,
                java.util.Arrays.asList(userAId, userBId),
                java.util.Arrays.asList(userAId, userBId)
        );
    }

public List<userResponse> getChatUsersForRental(String rentalId) {
    List<ChatMessage> messages = chatMessageRepository.findByRentalIdOrderByTimestampAsc(rentalId);
    java.util.Set<String> senderIds = new java.util.HashSet<>();
    for (ChatMessage msg : messages) {
        senderIds.add(msg.getSenderId());
    }
    List<userResponse> users = new java.util.ArrayList<>();
    for (String senderId : senderIds) {
        userRepository.findById(senderId).ifPresent(user -> {
            users.add(new userResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getGender(),
                null, // favoriteRentals, set if needed
                user.isEnabled()
            ));
        });
    }
    return users;
}
}