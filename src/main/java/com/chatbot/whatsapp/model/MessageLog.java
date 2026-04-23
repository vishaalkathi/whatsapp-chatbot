package com.chatbot.whatsapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Stores a record of each incoming message and the bot's reply.
 */
public class MessageLog {

    @JsonProperty("id")
    private long id;

    @JsonProperty("from")
    private String from;

    @JsonProperty("receivedMessage")
    private String receivedMessage;

    @JsonProperty("botReply")
    private String botReply;

    @JsonProperty("loggedAt")
    private String loggedAt;

    public MessageLog() {}

    public MessageLog(long id, String from, String receivedMessage, String botReply, String loggedAt) {
        this.id = id;
        this.from = from;
        this.receivedMessage = receivedMessage;
        this.botReply = botReply;
        this.loggedAt = loggedAt;
    }

    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getReceivedMessage() { return receivedMessage; }
    public void setReceivedMessage(String receivedMessage) { this.receivedMessage = receivedMessage; }

    public String getBotReply() { return botReply; }
    public void setBotReply(String botReply) { this.botReply = botReply; }

    public String getLoggedAt() { return loggedAt; }
    public void setLoggedAt(String loggedAt) { this.loggedAt = loggedAt; }
}
