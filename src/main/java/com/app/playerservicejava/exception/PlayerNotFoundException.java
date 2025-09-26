package com.app.playerservicejava.exception;

public class PlayerNotFoundException extends RuntimeException {
    private final String playerId;

    public PlayerNotFoundException(String playerId) {
        super("Player with id %s not found".formatted(playerId));
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }
}
