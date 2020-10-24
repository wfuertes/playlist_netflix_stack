DROP TABLE playlist_middle.playlist_songs;
DROP TABLE playlist_middle.songs;
DROP TABLE playlist_middle.playlists;

DELETE FROM playlist_middle.schema_history WHERE installed_rank = 1;
