package com.example.demo.Repositories;

import com.example.demo.Models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT * FROM message WHERE chat_id = :chatId", nativeQuery = true)
    List<Message> findMessagesByChatId(@Param("chatId") Long chatId);

    @Modifying
    @Query(value = "INSERT INTO message (sender_id, chat_id, message_text) VALUES (:senderId, :chatId, :messageText)", nativeQuery = true)
    void saveMessage(@Param("senderId") Long senderId, @Param("chatId") Long chatId, @Param("messageText") String messageText);
}