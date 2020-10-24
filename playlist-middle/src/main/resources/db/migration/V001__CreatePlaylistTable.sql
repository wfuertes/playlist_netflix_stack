-- Default Schema: playlist_middle must be created
CREATE TABLE playlists (
    id          VARCHAR(40) NOT NULL,
    name        VARCHAR(128) NOT NULL,
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB charset=UTF8;

CREATE INDEX idx_playlist_name ON playlists (name);
CREATE INDEX idx_playlist_created_at ON playlists (created_at DESC);

CREATE TABLE songs (
    id          VARCHAR(40)  NOT NULL,
    name        VARCHAR(128) NOT NULL,
    artist      VARCHAR(128) NOT NULL,
    duration    INT          NOT NULL,
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB charset=UTF8;

CREATE INDEX idx_song_name ON songs (name);
CREATE INDEX idx_song_artist ON songs (artist);

CREATE TABLE playlist_songs (
    playlist_id          VARCHAR(40)  NOT NULL,
    song_id              VARCHAR(40) NOT NULL,
    PRIMARY KEY (playlist_id, song_id)
) ENGINE=InnoDB charset=UTF8;

-- Stub values for development purposes
INSERT INTO playlists (id, name) VALUES ('b72b3ec0-1643-11eb-aa16-0242ac110003', 'Class Rock'),
                                        ('d6ea9415-1643-11eb-aa16-0242ac110003', 'Jazz of Baby'),
                                        ('eabc4890-1643-11eb-aa16-0242ac110003', 'Class Music');

INSERT INTO songs (id, name, artist, duration) VALUES ('a72b3ec0-1643-11eb-aa16-0242ac18947', 'Learn to Fly', 'Foo Fighters', 235),
                                                      ('c72b3ec0-1643-11eb-aa16-0242ac29837', 'Highway to Hell', 'AC/DC', 217),
                                                      ('d72b3ec0-1643-11eb-aa16-0242ac19236', 'About a Girl', 'Nirvana', 168);

INSERT INTO playlist_songs (playlist_id, song_id) VALUES ('b72b3ec0-1643-11eb-aa16-0242ac110003', 'a72b3ec0-1643-11eb-aa16-0242ac18947'),
                                                         ('b72b3ec0-1643-11eb-aa16-0242ac110003', 'c72b3ec0-1643-11eb-aa16-0242ac29837'),
                                                         ('b72b3ec0-1643-11eb-aa16-0242ac110003', 'd72b3ec0-1643-11eb-aa16-0242ac19236');
