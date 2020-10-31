package com.wfuertes.playlistcore.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@Accessors(fluent = true)
public class PlaylistView {
    String id;
    String name;

    @JsonCreator
    public PlaylistView(@JsonProperty("id") String id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
}
