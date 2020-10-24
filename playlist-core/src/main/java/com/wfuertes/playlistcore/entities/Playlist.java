package com.wfuertes.playlistcore.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.List;

@Value
@Accessors(fluent = true)
public class Playlist {

    String id;
    String name;
    List<String> musics;

    @JsonCreator
    public Playlist(@JsonProperty("id") String id,
                    @JsonProperty("name") String name,
                    @JsonProperty("musics") List<String> musics) {
        this.id = id;
        this.name = name;
        this.musics = musics;
    }
}
