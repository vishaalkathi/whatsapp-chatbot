package com.chatbot.whatsapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the chatbot's reply sent back to the caller.
 */
public class ChatbotResponse {

    @JsonProperty("to")
    private String to;

    @JsonProperty("reply")
    private String reply;

    @JsonProperty("status")
    private String status;

    @JsonProperty("processedAt")
    private String processedAt;

    // Constructors
    public ChatbotResponse() {}

    public ChatbotResponse(String to, String reply, String status, String processedAt) {
        this.to = to;
        this.reply = reply;
        this.status = status;
        this.processedAt = processedAt;
    }

    // Getters and Setters
    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }

    public String getReply() { return reply; }
    public void setReply(String reply) { this.reply = reply; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getProcessedAt() { return processedAt; }
    public void setProcessedAt(String processedAt) { this.processedAt = processedAt; }
}
