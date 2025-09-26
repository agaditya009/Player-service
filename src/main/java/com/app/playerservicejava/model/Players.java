package com.app.playerservicejava.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players implements Serializable {
    private final List<Player> players;
    private final PageMetadata metadata;

    public Players() {
        this(Collections.emptyList(), PageMetadata.empty());
    }

    @JsonCreator
    public Players(
            @JsonProperty("players") List<Player> players,
            @JsonProperty("metadata") PageMetadata metadata
    ) {
        List<Player> safePlayers = players == null ? Collections.emptyList() : new ArrayList<>(players);
        this.players = Collections.unmodifiableList(safePlayers);
        this.metadata = metadata == null ? PageMetadata.empty() : metadata;
    }

    public static Players of(List<Player> players, PageMetadata metadata) {
        return new Players(players, metadata);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public PageMetadata getMetadata() {
        return metadata;
    }
}
