package com.matera.playlistcore.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author wbatista
 */
public class PlaylistResponseMiddle extends BaseResponse {

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
