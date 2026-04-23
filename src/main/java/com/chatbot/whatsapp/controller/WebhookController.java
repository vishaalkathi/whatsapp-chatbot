package com.chatbot.whatsapp.controller;

import com.chatbot.whatsapp.model.ChatbotResponse;
import com.chatbot.whatsapp.model.IncomingMessage;
import com.chatbot.whatsapp.model.MessageLog;
import com.chatbot.whatsapp.service.ChatbotService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST Controller exposing the chatbot webhook API.
 *
 * Endpoints:
 *  POST /webhook          — receive and process a WhatsApp-style message
 *  GET  /webhook/health   — health check + stats
 *  GET  /webhook/logs     — view all logged conversations
 */
@RestController
@RequestMapping("/webhook")
@CrossOrigin(origins = "*") // Allow testing from Postman / browser
public class WebhookController {

    private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);

    private final ChatbotService chatbotService;

    public WebhookController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    /**
     * Main webhook endpoint — simulates receiving a WhatsApp message.
     *
     * Sample request body:
     * {
     *   "from": "+911234567890",
     *   "message": "Hi",
     *   "timestamp": "2024-01-15T10:30:00"
     * }
     */
    @PostMapping
    public ResponseEntity<ChatbotResponse> receiveMessage(
            @Valid @RequestBody IncomingMessage incoming) {

        logger.info("➡️  Webhook hit from: {}", incoming.getFrom());

        ChatbotResponse response = chatbotService.processMessage(incoming);

        return ResponseEntity.ok(response);
    }

    /**
     * Health check endpoint — useful for Render / Railway keep-alive pings.
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        return ResponseEntity.ok(Map.of(
                "status",        "UP ✅",
                "service",       "WhatsApp Chatbot Backend",
                "version",       "1.0.0",
                "totalMessages", chatbotService.getMessageCount()
        ));
    }

    /**
     * Returns all logged conversations — handy for demo/screenshot.
     */
    @GetMapping("/logs")
    public ResponseEntity<Map<String, Object>> getLogs() {
        List<MessageLog> logs = chatbotService.getAllLogs();
        return ResponseEntity.ok(Map.of(
                "totalMessages", logs.size(),
                "conversations", logs
        ));
    }
}
