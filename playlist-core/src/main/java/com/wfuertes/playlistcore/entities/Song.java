package com.wfuertes.playlistcore.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Value
@Accessors(fluent = true)
public class Song {
    String id;
    String name;
    String artist;
    int duration;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @JsonCreator
    public Song(@JsonProperty("id") String id,
                @JsonProperty("name") String name,
                @JsonProperty("artist") String artist,
                @JsonProperty("duration") int duration,
                @JsonProperty("createdAt") LocalDateTime createdAt,
                @JsonProperty("updatedAt") LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
