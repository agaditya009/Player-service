package com.app.playerservicejava.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Represents metadata associated with a paginated collection response.
 */
public class PageMetadata implements Serializable {
    private final long totalElements;
    private final int totalPages;
    private final int page;
    private final int size;

    public PageMetadata() {
        this(0L, 0, 0, 0);
    }

    @JsonCreator
    public PageMetadata(
            @JsonProperty("totalElements") long totalElements,
            @JsonProperty("totalPages") int totalPages,
            @JsonProperty("page") int page,
            @JsonProperty("size") int size
    ) {
        this.totalElements = Math.max(totalElements, 0L);
        this.totalPages = Math.max(totalPages, 0);
        this.page = Math.max(page, 0);
        this.size = Math.max(size, 0);
    }

    public static PageMetadata empty() {
        return new PageMetadata();
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }
}
