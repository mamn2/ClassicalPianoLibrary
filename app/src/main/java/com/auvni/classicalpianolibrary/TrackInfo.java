package com.auvni.classicalpianolibrary;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.telecom.Call;
import android.widget.ImageView;

import com.spotify.protocol.client.CallResult;
import com.spotify.protocol.client.Result;

import java.util.concurrent.TimeUnit;

public class TrackInfo extends SearchResultsActivity {
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

    public TrackInfo(String songName, String artistName, String url, ImageView coverArt) {
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
