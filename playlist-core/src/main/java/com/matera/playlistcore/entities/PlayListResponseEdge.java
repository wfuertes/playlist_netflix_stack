package com.matera.playlistcore.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author user
 */
public class PlayListResponseEdge {

    private List<PlayList> playLists;

    /**
     * @return the playLists
     */
    public List<PlayList> getPlayLists() {

        if (playLists == null) {
            playLists = new ArrayList<PlayList>();
        }
        return playLists;
    }

    /**
     * @param playLists the playLists to set
     */
    public void setPlayLists(List<PlayList> playLists) {

        this.playLists = playLists;
    }

}
