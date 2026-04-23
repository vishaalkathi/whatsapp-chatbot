package com.vishaal.chatbot.controller;

import com.vishaal.chatbot.model.MessageRequest;
import com.vishaal.chatbot.model.MessageResponse;
import com.vishaal.chatbot.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebhookController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/webhook")
    public MessageResponse receiveMessage(@RequestBody MessageRequest request) {
        
        System.out.println("Incoming message: " + request.getMessage());

        String reply = chatService.getReply(request.getMessage());

        return new MessageResponse(reply);
    }
}