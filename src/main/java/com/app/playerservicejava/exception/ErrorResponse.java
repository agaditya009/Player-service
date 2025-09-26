package com.app.playerservicejava.exception;

import java.time.Instant;

public record ErrorResponse(String message, Instant timestamp) {
}
