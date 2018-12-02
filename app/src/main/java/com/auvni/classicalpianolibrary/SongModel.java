package com.auvni.classicalpianolibrary;

public class SongModel {
    String songName;
    String artistName;
    String url;

    public SongModel() {

    }

    public SongModel(String songName, String artistName, String url) {
        this.songName = songName;
        this.artistName = artistName;
        this.url = url;
    }
}
