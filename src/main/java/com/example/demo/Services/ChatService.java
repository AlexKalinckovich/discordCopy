package com.example.demo.Services;

import com.example.demo.Models.Chat;
import com.example.demo.Models.Message;
import com.example.demo.Repositories.MessageRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final User_ChatRepository user_chatRepository;

    public ChatService(MessageRepository messageRepository, UserRepository userRepository, ChatRepository chatRepository, User_ChatRepository userChatRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
        user_chatRepository = userChatRepository;
    }

    // Получить все чаты пользователя
    public List<Chat> getUserChats(Long userId) {
        return chatRepository.findAllChatsForUser(userId);
    }

    // Получить сообщения для выбранного чата
    public List<Message> getMessagesFromChat(Long chatId) {
        return messageRepository.findMessagesByChatId(chatId);
    }

    @Transactional
    public void saveMessage(Long userId, Long chatId, String messageText) {
        messageRepository.saveMessage(userId, chatId, messageText);
    }

    public Optional<Chat> findChatByName(String chatName) {
        return chatRepository.findByName(chatName);
    }

    @Transactional
    public void addChatToUser(long userId, long chatId) {
        user_chatRepository.insertUserChat(userId, chatId);  // Добавляем связь пользователя с чатом
    }

    public Optional<Chat> searchChatsByName(String chatName) {
        return chatRepository.findByName(chatName);  // Поиск чатов по имени
    }

    public Optional<Chat> searchUserChatById(long chatId) {
        return user_chatRepository.findById(chatId);
    }

    // Создать новый чат
    @Transactional
    public void createChat(String chatName,long userId) {
        chatRepository.createChat(chatName);
        Optional<Chat> chat = chatRepository.findByName(chatName);
        chat.ifPresent(value -> user_chatRepository.insertUserChat(userId,value.getId()));
    }

    // Поиск пользователя по имени
    public Optional<User> findUserByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }
}

