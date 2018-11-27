package com.auvni.classicalpianolibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AudioPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);
    }

    /**
     * This method is called when a user clicks the play button.
     * @param v
     */
    public void play(View v) {}

    /**
     * This method is called when a user clicks the pause button.
     * @param v
     */
    public void pause(View v) {}

    /**
     * This method is called when a user want to stop the song all together.
     * @param v
     */
    public void stop(View v) {}


}
