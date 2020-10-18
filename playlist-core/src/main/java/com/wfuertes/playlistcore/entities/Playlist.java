package com.wfuertes.playlistcore.entities;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private Long id;
    private String name;
    private List<String> musics;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public List<String> getMusics() {

        if (musics == null) {
            musics = new ArrayList<>();
        }
        return musics;
    }

    public void setMusics(List<String> musics) {

        this.musics = musics;
    }
}
