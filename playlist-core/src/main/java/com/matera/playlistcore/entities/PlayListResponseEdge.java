package com.matera.playlistcore.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author user
 */
public class PlayListResponseEdge {

    private List<Playlist> playLists;

    /**
     * @return the playLists
     */
    public List<Playlist> getPlayLists() {

        if (playLists == null) {
            playLists = new ArrayList<Playlist>();
        }
        return playLists;
    }

    /**
     * @param playLists the playLists to set
     */
    public void setPlayLists(List<Playlist> playLists) {

        this.playLists = playLists;
    }

}
