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
import com.spotify.protocol.client.CallResult;
import com.spotify.protocol.client.Result;
import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SearchResultsActivity extends AppCompatActivity implements SpotifyRemoteCalls {

    protected static SpotifyAppRemote mSpotifyAppRemote;
    private ArrayList<TrackInfo> trackInfoArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    SeekBar seekbar;
    Subscription<PlayerState> mPlayerStateSubscription;
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
        //loadSpotify();
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
        try {
            connect(true);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        songRecyclerListAdapter.setItemClickListener(new SongRecyclerListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(final Button b, View v, TrackInfo inf, int pos) {


                if (checkPlayerPaused()) {
                    pausePlay();
                    subscribeToPlayerState();
                    fullScreen.setVisibility(View.INVISIBLE);
                } else if (b.getText().toString().equals("Stop")) {
                    pausePlay();
                    b.setText("Play");
                    subscribeToPlayerState();
                    fullScreen.setVisibility(View.INVISIBLE);
                } else {
                    onPlayClicked();
                    subscribeToPlayerState();
                    b.setText("Stop");
                    fullScreen.setVisibility(View.VISIBLE);
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
        TrackInfo track = new TrackInfo("Polonaise in B-flat Minor, Op. 21", "Alexander Scriabin, Halida Dinova", "spotify:track:5UIV0Vy9Ui1IrK1jT5mMwl");
        trackInfoArrayList.add(track);

        TrackInfo track2 = new TrackInfo("Piano Concerto No. 2 in C minor, Op. 18 No. 1", "Sergei Rachmaninov", "https://fullgaana.in/siteuploads/files/sfd6/2895/Faded%20(Alan%20Walker)(FullGaana.In).mp3");
        trackInfoArrayList.add(track2);
    }

    private void connect(boolean showAuthView) {

        SpotifyAppRemote.disconnect(mSpotifyAppRemote);

        SpotifyAppRemote.connect(
                getApplication(),
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(showAuthView)
                        .build(),
                new Connector.ConnectionListener() {
                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        Toast.makeText(SearchResultsActivity.this, "Not Connected", Toast.LENGTH_SHORT).show();
                        connect(true);
                    }
                });


    }

    protected void subscribeToPlayerState() {

        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState();


    }

    public void onPlayClicked() {
        playUri("spotify:track:5UIV0Vy9Ui1IrK1jT5mMwl");
    }

    private void playUri(String uri) {
        mSpotifyAppRemote.getPlayerApi()
                .play(uri);
    }

    protected void pausePlay() {

        if (checkPlayerPaused()) {
            mSpotifyAppRemote.getPlayerApi().resume();
        } else {
            mSpotifyAppRemote.getPlayerApi().pause();
        }

    }

    protected boolean checkPlayerPaused() {
        CallResult<PlayerState> playerStateCall = mSpotifyAppRemote.getPlayerApi().getPlayerState();
        Result<PlayerState> playerStateResult = playerStateCall.await(10, TimeUnit.MICROSECONDS);
        if (playerStateResult.isSuccessful()) {
            if (playerStateResult.getData().isPaused) {
                return true;
            }
        }

        return false;
    }
}
