package com.wfuertes.playlistcore.entities;

import java.util.ArrayList;
import java.util.List;

public class PlaylistResponseEdge extends BaseResponse {

    private List<Playlist> playLists;

    public List<Playlist> getPlaylists() {

        if (playLists == null) {
            playLists = new ArrayList<>();
        }
        return playLists;
    }
}
