package com.matera.playlistcore.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author user
 */
public class PlaylistResponseEdge extends BaseResponse {

    private List<Playlist> playLists;

    /**
     * @return the playLists
     */
    public List<Playlist> getPlaylists() {

        if (playLists == null) {
            playLists = new ArrayList<Playlist>();
        }
        return playLists;
    }
}
