package com.vishaal.chatbot.service;

import org.springframework.stereotype.Service;

@Service
public class ChatService {

    public String getReply(String message) {
        if (message == null) return "I didn't understand";

        switch (message.toLowerCase()) {
            case "hi":
                return "Hello";
            case "bye":
                return "Goodbye";
            default:
                return "Sorry, I don't understand";
        }
    }
}
