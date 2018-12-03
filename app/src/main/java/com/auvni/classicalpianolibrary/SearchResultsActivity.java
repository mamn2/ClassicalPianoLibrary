package com.auvni.classicalpianolibrary;

import android.app.ActionBar;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

import java.io.IOException;
import java.util.ArrayList;

public class SearchResultsActivity extends AppCompatActivity {


    private ArrayList<TrackInfo> trackInfoArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    SeekBar seekbar;
    SongRecyclerListAdapter songRecyclerListAdapter;
    static MediaPlayer player;
    ImageButton fullScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        fullScreen = (ImageButton) findViewById(R.id.fullScreenButton);

        loadTracks();

        songRecyclerListAdapter = new SongRecyclerListAdapter(this, trackInfoArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(songRecyclerListAdapter);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        songRecyclerListAdapter.setItemClickListener(new SongRecyclerListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(final Button b, View v, TrackInfo inf, int pos) {

                if (b.getText().toString().equals("Stop")) {
                    if (player != null) {
                        player.stop();
                        player.reset();
                        player.release();
                        fullScreen.setVisibility(View.INVISIBLE);
                    }
                    b.setText("Play");
                    player = null;
                } else if (player != null) {
                    player.stop();
                    player.reset();
                    player.release();
                    player = null;
                    setUpMediaPlayer(inf.getUrl(), b);
                } else {
                    setUpMediaPlayer(inf.getUrl(), b);
                }

            }
        });

        fullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchResultsActivity.this, AudioPlayerActivity.class));
            }
        });

    }



    public void setUpMediaPlayer(String url, final Button b) {
        try {
            player = new MediaPlayer();
            player.setDataSource(url);
            player.prepareAsync();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    b.setText("Stop");
                }
            });
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                    b.setText("Play");
                    fullScreen.setVisibility(View.INVISIBLE);
                }
            });
            fullScreen.setVisibility(View.VISIBLE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static MediaPlayer getPlayer() {
        return player;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            if (player != null) {
                player.stop();
                player.reset();
                player.release();
            }
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadTracks() {
        TrackInfo track = new TrackInfo("Girls Like You", "Maroon 5", "https://fullgaana.in/siteuploads/files/sfd10/4966/Maroon%205%20-%20Girls%20Like%20You%20(feat.%20Cardi%20B)(FullGaana.In).mp3");
        trackInfoArrayList.add(track);

        TrackInfo track2 = new TrackInfo("Faded", "Alan Walker", "https://fullgaana.in/siteuploads/files/sfd6/2895/Faded%20(Alan%20Walker)(FullGaana.In).mp3");
        trackInfoArrayList.add(track2);
    }


}
