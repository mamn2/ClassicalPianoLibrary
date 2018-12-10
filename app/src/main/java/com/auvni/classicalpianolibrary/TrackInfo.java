package com.auvni.classicalpianolibrary;

import android.widget.ImageView;

public class TrackInfo {
    private String songName;
    private String artistName;
    private String url;
    private ImageView coverArt;

    public TrackInfo() {

    }

    public TrackInfo(String songName, String artistName, String url) {
        this.songName = songName;
        this.artistName = artistName;
        this.url = url;
    }

    public TrackInfo(String uri) {

    }

    public TrackInfo(String songName, String artistName, String url, ImageView coverArt) {
        this.songName = songName;
        this.artistName = artistName;
        this.url = url;
        this.coverArt = coverArt;
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

    public ImageView getCoverArt() {
        return coverArt;
    }
}
