package com.chatbot.whatsapp.service;

import com.chatbot.whatsapp.model.ChatbotResponse;
import com.chatbot.whatsapp.model.IncomingMessage;
import com.chatbot.whatsapp.model.MessageLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Core business logic for the WhatsApp chatbot.
 *
 * Handles:
 *  - Mapping incoming messages to predefined bot replies
 *  - Logging all conversations in memory
 *  - Providing message history for audit/demo purposes
 */
@Service
public class ChatbotService {

    private static final Logger logger = LoggerFactory.getLogger(ChatbotService.class);

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /** In-memory log store (thread-safe). Replace with DB in production. */
    private final List<MessageLog> messageLogs = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong idCounter = new AtomicLong(1);

    /**
     * Predefined reply map — extend this freely for richer conversations.
     * Keys are LOWERCASE so matching is case-insensitive.
     */
    private static final Map<String, String> REPLY_MAP = Map.ofEntries(
        Map.entry("hi",          "Hello! 👋 How can I help you today?"),
        Map.entry("hello",       "Hey there! 😊 What can I do for you?"),
        Map.entry("bye",         "Goodbye! 👋 Have a wonderful day!"),
        Map.entry("goodbye",     "Take care! See you soon. 😊"),
        Map.entry("help",        "Sure! You can say: Hi, Hello, Bye, Thanks, Hours, Location"),
        Map.entry("thanks",      "You're welcome! 🙏 Is there anything else I can help with?"),
        Map.entry("thank you",   "Happy to help! 😊"),
        Map.entry("hours",       "We're open Mon–Sat, 9 AM to 6 PM IST."),
        Map.entry("location",    "We're based in Gorakhpur, Uttar Pradesh, India 📍"),
        Map.entry("price",       "Please visit our website or type 'help' for more options.")
    );

    private static final String DEFAULT_REPLY =
            "I didn't quite understand that. 🤔 Type 'help' to see what I can do!";

    /**
     * Process an incoming message and return a bot response.
     *
     * @param incoming the parsed WhatsApp-style message payload
     * @return ChatbotResponse containing the bot's reply
     */
    public ChatbotResponse processMessage(IncomingMessage incoming) {
        String now = LocalDateTime.now().format(FORMATTER);
        String normalizedMessage = incoming.getMessage().trim().toLowerCase();

        // --- Log incoming message ---
        logger.info("📩 Received from [{}] at [{}]: \"{}\"",
                incoming.getFrom(), now, incoming.getMessage());

        // --- Determine reply ---
        String reply = REPLY_MAP.getOrDefault(normalizedMessage, DEFAULT_REPLY);

        // --- Log bot reply ---
        logger.info("🤖 Replying to  [{}]: \"{}\"", incoming.getFrom(), reply);

        // --- Persist to in-memory log ---
        MessageLog log = new MessageLog(
                idCounter.getAndIncrement(),
                incoming.getFrom(),
                incoming.getMessage(),
                reply,
                now
        );
        messageLogs.add(log);

        // --- Build and return response ---
        return new ChatbotResponse(incoming.getFrom(), reply, "sent", now);
    }

    /**
     * Returns a snapshot of all logged messages (most recent first).
     */
    public List<MessageLog> getAllLogs() {
        List<MessageLog> copy = new ArrayList<>(messageLogs);
        Collections.reverse(copy);
        return copy;
    }

    /**
     * Returns total message count for health/stats endpoint.
     */
    public long getMessageCount() {
        return messageLogs.size();
    }
}
