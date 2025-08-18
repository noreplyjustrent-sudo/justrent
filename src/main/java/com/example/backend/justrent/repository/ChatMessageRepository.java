package com.example.backend.justrent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.justrent.model.ChatMessage;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {
    // Get all messages between two users (both directions), ordered by timestamp
    List<ChatMessage> findBySenderIdAndReceiverIdOrderByTimestampAsc(String senderId, String receiverId);
    List<ChatMessage> findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByTimestampAsc(
        String senderId1, String receiverId1, String senderId2, String receiverId2
    );
    // Get all messages for a rental between two users (both directions), ordered by timestamp
    List<ChatMessage> findByRentalIdAndSenderIdInAndReceiverIdInOrderByTimestampAsc(
        String rentalId, List<String> senderIds, List<String> receiverIds
    );

    List<ChatMessage> findByRentalIdOrderByTimestampAsc(String rentalId);
}