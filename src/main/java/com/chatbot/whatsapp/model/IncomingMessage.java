package com.chatbot.whatsapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents an incoming WhatsApp-style message payload.
 *
 * Expected JSON format:
 * {
 *   "from": "+911234567890",
 *   "message": "Hi",
 *   "timestamp": "2024-01-15T10:30:00"
 * }
 */
public class IncomingMessage {

    @NotBlank(message = "Sender number is required")
    @JsonProperty("from")
    private String from;

    @NotBlank(message = "Message content is required")
    @JsonProperty("message")
    private String message;

    @JsonProperty("timestamp")
    private String timestamp;

    // Constructors
    public IncomingMessage() {}

    public IncomingMessage(String from, String message, String timestamp) {
        this.from = from;
        this.message = message;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return "IncomingMessage{from='" + from + "', message='" + message + "', timestamp='" + timestamp + "'}";
    }
}
