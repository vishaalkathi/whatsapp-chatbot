package com.chatbot.whatsapp;

import com.chatbot.whatsapp.model.ChatbotResponse;
import com.chatbot.whatsapp.model.IncomingMessage;
import com.chatbot.whatsapp.service.ChatbotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatbotServiceTest {

    private ChatbotService chatbotService;

    @BeforeEach
    void setUp() {
        chatbotService = new ChatbotService();
    }

    @Test
    @DisplayName("Hi → Hello")
    void testHiReply() {
        IncomingMessage msg = new IncomingMessage("+911234567890", "Hi", null);
        ChatbotResponse response = chatbotService.processMessage(msg);

        assertEquals("+911234567890", response.getTo());
        assertTrue(response.getReply().toLowerCase().contains("hello"));
        assertEquals("sent", response.getStatus());
    }

    @Test
    @DisplayName("Bye → Goodbye")
    void testByeReply() {
        IncomingMessage msg = new IncomingMessage("+911234567890", "Bye", null);
        ChatbotResponse response = chatbotService.processMessage(msg);

        assertTrue(response.getReply().toLowerCase().contains("goodbye")
                || response.getReply().toLowerCase().contains("take care"));
    }

    @Test
    @DisplayName("Case-insensitive matching — HI, hi, Hi all work")
    void testCaseInsensitiveMatching() {
        String[] variants = {"HI", "hi", "Hi", "hI"};
        for (String variant : variants) {
            IncomingMessage msg = new IncomingMessage("+910000000000", variant, null);
            ChatbotResponse response = chatbotService.processMessage(msg);
            assertTrue(response.getReply().toLowerCase().contains("hello"),
                    "Failed for input: " + variant);
        }
    }

    @Test
    @DisplayName("Unknown message → default fallback reply")
    void testUnknownMessageFallback() {
        IncomingMessage msg = new IncomingMessage("+911234567890", "xyzzy", null);
        ChatbotResponse response = chatbotService.processMessage(msg);

        assertNotNull(response.getReply());
        assertFalse(response.getReply().isBlank());
    }

    @Test
    @DisplayName("Messages are logged and count increases")
    void testMessageLogging() {
        assertEquals(0, chatbotService.getMessageCount());

        chatbotService.processMessage(new IncomingMessage("+91111", "Hi", null));
        chatbotService.processMessage(new IncomingMessage("+91222", "Bye", null));

        assertEquals(2, chatbotService.getMessageCount());
        assertEquals(2, chatbotService.getAllLogs().size());
    }
}
