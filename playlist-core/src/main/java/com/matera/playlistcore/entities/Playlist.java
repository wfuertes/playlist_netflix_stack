package com.matera.playlistcore.entities;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private Long id;
    private String name;
    private List<String> musics;

    /**
     * @return the id
     */
    public Long getId() {

        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {

        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {

        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * @return the musics
     */
    public List<String> getMusics() {

        if (musics == null) {
            musics = new ArrayList<String>();
        }
        return musics;
    }

    /**
     * @param musics the musics to set
     */
    public void setMusics(List<String> musics) {

        this.musics = musics;
    }

}
