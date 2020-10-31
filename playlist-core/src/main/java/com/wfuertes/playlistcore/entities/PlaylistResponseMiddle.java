package com.wfuertes.playlistcore.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@Accessors(fluent = true)
public class PlaylistResponseMiddle<T> {

    int status;
    String message;
    T content;

    @JsonCreator
    public PlaylistResponseMiddle(@JsonProperty("status") int status,
                                  @JsonProperty("message") String message,
                                  @JsonProperty("content") T content) {
        this.status = status;
        this.message = message;
        this.content = content;
    }
}
