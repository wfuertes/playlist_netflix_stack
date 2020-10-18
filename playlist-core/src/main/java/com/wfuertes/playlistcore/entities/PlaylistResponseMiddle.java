package com.wfuertes.playlistcore.entities;

import java.util.ArrayList;
import java.util.List;

public class PlaylistResponseMiddle extends BaseResponse {

    private List<Playlist> playlists;

    public List<Playlist> getPlaylists() {

        if (playlists == null) {
            playlists = new ArrayList<>();
        }
        return playlists;
    }
}
