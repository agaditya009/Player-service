package com.app.playerservicejava.controller;

import com.app.playerservicejava.exception.ErrorResponse;
import com.app.playerservicejava.exception.PlayerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice(assignableTypes = PlayerController.class)
public class PlayerControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(PlayerControllerAdvice.class);
    private static final String GENERIC_ERROR_MESSAGE = "An unexpected error occurred";

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlayerNotFound(PlayerNotFoundException exception) {
        ErrorResponse response = new ErrorResponse(exception.getMessage(), Instant.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(Exception exception) {
        logger.error("Unexpected error while processing request", exception);
        ErrorResponse response = new ErrorResponse(GENERIC_ERROR_MESSAGE, Instant.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
