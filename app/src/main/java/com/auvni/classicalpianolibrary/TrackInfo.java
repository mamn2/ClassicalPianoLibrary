package com.auvni.classicalpianolibrary;

public class TrackInfo {
    public String songName;
    public String artistName;
    public String url;

    public TrackInfo() {

    }

    public TrackInfo(String songName, String artistName, String url) {
        this.songName = songName;
        this.artistName = artistName;
        this.url = url;
    }
}
