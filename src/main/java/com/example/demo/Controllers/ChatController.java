package com.example.demo.Controllers;

import com.example.demo.Models.Chat;
import com.example.demo.Models.Message;
import com.example.demo.Services.AuthorizationService;
import com.example.demo.Services.ChatService;
import com.example.demo.Models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ChatController {

    private final ChatService chatService;
    private final AuthorizationService authorizationService;

    public ChatController(ChatService chatService, AuthorizationService authorizationService) {
        this.chatService = chatService;
        this.authorizationService = authorizationService;
    }

    @GetMapping("/mainPage")
    public String mainPage(Model model) {
        Optional<User> currentUser = authorizationService.getCurrentUser();
        if(currentUser.isPresent()) {
            List<Chat> userChats = chatService.getUserChats(currentUser.get().getId());
            model.addAttribute("chats", userChats);

            if (!userChats.isEmpty()) {
                List<Message> messages = chatService.getMessagesFromChat(userChats.getFirst().getId());
                model.addAttribute("messages", messages);
            }
        }
        return "mainPage";
    }
}

