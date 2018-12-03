package com.auvni.classicalpianolibrary;



public class TrackInfo {
    private String songName;
    private String artistName;
    private String url;

    public TrackInfo() {

    }

    public TrackInfo(String songName, String artistName, String url) {
        this.songName = songName;
        this.artistName = artistName;
        this.url = url;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getUrl() {
        return url;
    }
}
