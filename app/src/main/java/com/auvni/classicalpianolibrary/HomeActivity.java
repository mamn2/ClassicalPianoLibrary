package com.auvni.classicalpianolibrary;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;

public class HomeActivity extends AppCompatActivity {

    String url = "https://www.searchgurbani.com/audio/sggs/1.mp3";
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);
    }

    public void setThisURL(String setUrl) {
        this.url = setUrl;
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
