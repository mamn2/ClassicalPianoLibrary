package com.auvni.classicalpianolibrary;

import android.app.ActionBar;
import android.content.Intent;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.PlayerApi;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SearchResultsActivity extends AppCompatActivity implements SpotifyRemoteCalls {

    private SpotifyAppRemote mSpotifyAppRemote;
    private ArrayList<TrackInfo> trackInfoArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    SeekBar seekbar;
    SongRecyclerListAdapter songRecyclerListAdapter;
    static MediaPlayer player;
    ImageButton fullScreen;
    String getSongName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            getSongName = extras.getString("key");
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        fullScreen = (ImageButton) findViewById(R.id.fullScreenButton);
        loadSpotify();
        loadAll();
        search(getSongName);

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

    private void search(String song) {
        boolean found = false;
        for (int i = 0; i < trackInfoArrayList.size(); i++) {
            if (trackInfoArrayList.get(i).getSongName().equals(song)) {
                TrackInfo info = trackInfoArrayList.get(i);
                trackInfoArrayList = new ArrayList<>();
                trackInfoArrayList.add(info);
                found = true;
            }
        }
        if (!found) {
            setContentView(R.layout.no_songs);
        }
    }

    private void loadAll() {
        TrackInfo track = new TrackInfo("Girls Like You", "Maroon 5", "https://fullgaana.in/siteuploads/files/sfd10/4966/Maroon%205%20-%20Girls%20Like%20You%20(feat.%20Cardi%20B)(FullGaana.In).mp3");
        trackInfoArrayList.add(track);

        TrackInfo track2 = new TrackInfo("Faded", "Alan Walker", "https://fullgaana.in/siteuploads/files/sfd6/2895/Faded%20(Alan%20Walker)(FullGaana.In).mp3");
        trackInfoArrayList.add(track2);
    }

    private void loadSpotify() {

        AuthenticationRequest.Builder builder =
                new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI);

        builder.setScopes(new String[]{"streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);

        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();
        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {
                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        connected();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {

                    }
                });
    }

    private void connected() {
        PlayerApi player = mSpotifyAppRemote.getPlayerApi();
        player.play("spotify:track:7lEptt4wbM0yJTvSG5EBof");
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (Exception e) {

        }

        player.pause();
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);

    }




}
