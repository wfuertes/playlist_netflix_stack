package com.wfuertes.playlistcore.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.List;

@Value
@Accessors(fluent = true)
public class PlaylistResponseEdge {

    int status;
    String message;
    List<Playlist> playlists;

    @JsonCreator
    public PlaylistResponseEdge(@JsonProperty("status") int status,
                                @JsonProperty("message") String message,
                                @JsonProperty("playlists") List<Playlist> playlists) {
        this.status = status;
        this.message = message;
        this.playlists = playlists;
    }
}
