package com.app.playerservicejava.model;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Players implements Serializable {
    private List<Player> players;
    private PageMetadata metadata;

    public Players() {
        this(new ArrayList<>(), new PageMetadata());
    }

    public Players(List<Player> players, PageMetadata metadata) {
        this.players = players == null ? new ArrayList<>() : new ArrayList<>(players);
        this.metadata = metadata == null ? new PageMetadata() : metadata;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void setPlayers(List<Player> players) {
        this.players = players == null ? new ArrayList<>() : new ArrayList<>(players);
    }

    public PageMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(PageMetadata metadata) {
        this.metadata = Objects.requireNonNullElseGet(metadata, PageMetadata::new);
    }

    public static class PageMetadata implements Serializable {
        private long totalElements;
        private int totalPages;
        private int page;
        private int size;

        public long getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(long totalElements) {
            this.totalElements = totalElements;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }
}
