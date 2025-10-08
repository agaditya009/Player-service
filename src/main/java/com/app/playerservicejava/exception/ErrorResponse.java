package com.app.playerservicejava.exception;
import java.time.Instant;

public class ErrorResponse {
    private String code;
    private String message;
    private Instant timestamp;
    private int status;

    public ErrorResponse(String code, String message, Instant timestamp, int status) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
        this.status = status;
    }

    // getters
    public String getCode() { return code; }
    public String getMessage() { return message; }
    public Instant getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
}