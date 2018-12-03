package com.auvni.classicalpianolibrary;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.net.URI;

public class AudioPlayerActivity extends AppCompatActivity {

    String url;
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setThisURL(String setUrl) {
        this.url = setUrl;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is called when a user clicks the play button.
     * @param v xml file button
     */
    public void play(View v) {
        if (player == null) {
            try {
                player = new MediaPlayer();
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                player.setDataSource(url);
                player.prepare();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Input does not exist", Toast.LENGTH_SHORT).show();
            }
        }
        player.start();
    }

    /**
     * This method is called when a user clicks the pause button.
     * @param v xml file button
     */
    public void pause(View v) {
        if (player != null) {
            player.pause();
        }
    }

    /**
     * This method is called when a user want to stop the song all together.
     * @param v xml file button
     */
    public void stop(View v) {
        stopPlayer();
    }

    public void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
            Toast.makeText(this, "MediaPlayer has been released", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        stopPlayer();
    }
}
