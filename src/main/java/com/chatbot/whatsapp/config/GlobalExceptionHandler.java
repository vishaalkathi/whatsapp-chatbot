package com.chatbot.whatsapp.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles validation and parsing errors gracefully,
 * returning clean JSON error responses instead of stack traces.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /** Handles @Valid failures (missing/blank fields) */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError err : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(err.getField(), err.getDefaultMessage());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("status",    "error");
        response.put("message",   "Validation failed");
        response.put("errors",    fieldErrors);
        response.put("timestamp", LocalDateTime.now().format(FORMATTER));

        return ResponseEntity.badRequest().body(response);
    }

    /** Handles malformed JSON in request body */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleMalformedJson(
            HttpMessageNotReadableException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "status",    "error",
                "message",   "Invalid JSON format. Please check your request body.",
                "timestamp", LocalDateTime.now().format(FORMATTER)
        ));
    }

    /** Catch-all for unexpected errors */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "status",    "error",
                "message",   "An unexpected error occurred: " + ex.getMessage(),
                "timestamp", LocalDateTime.now().format(FORMATTER)
        ));
    }
}
