package com.matera.playlistcore.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author user
 */
public class PlaylistResponseMiddle {

    private List<Playlist> playlists;

    /**
     * @return the playLists
     */
    public List<Playlist> getPlaylists() {

        if (playlists == null) {
            playlists = new ArrayList<Playlist>();
        }
        return playlists;
    }
}
