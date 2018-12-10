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
    private Bitmap coverArt;

    public TrackInfo() {

    }

    public TrackInfo(String songName, String artistName, String url) {
        this.songName = songName;
        this.artistName = artistName;
        this.url = url;
    }

    public TrackInfo(String uri) {

    }

    public TrackInfo(String songName, String artistName, String url, Bitmap coverArt) {
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

    public Bitmap getCoverArt() {
        CallResult<Bitmap> bitmap = imageConnection(url);
        Result<Bitmap>  bitmapResult = bitmap.await(10, TimeUnit.SECONDS);
        if (bitmapResult.isSuccessful()) {
            coverArt = bitmapResult.getData();
        }

        return coverArt;
    }
}
