package com.app.playerservicejava.exception;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String id) {
        super("player: "+id + " not found");
    }
}
