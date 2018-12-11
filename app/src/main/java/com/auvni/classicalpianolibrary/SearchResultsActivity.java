package com.auvni.classicalpianolibrary;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.client.CallResult;
import com.spotify.protocol.client.Result;
import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.ImageUri;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SearchResultsActivity extends AppCompatActivity implements SpotifyRemoteCalls {

    protected static SpotifyAppRemote mSpotifyAppRemote;
    private ArrayList<TrackInfo> trackInfoArrayList = new ArrayList<>();
    private ArrayList<TrackInfo> newTrackInfoArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    Subscription<PlayerState> mPlayerStateSubscription;
    SongRecyclerListAdapter songRecyclerListAdapter;
    static MediaPlayer player;
    ImageButton fullScreen;
    String getSongName;
    protected static TrackInfo trackInfoNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            getSongName = extras.getString("key");
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        fullScreen = (ImageButton) findViewById(R.id.fullScreenButton);
        newTrackInfoArrayList = new ArrayList<>();
        trackInfoArrayList = new ArrayList<>();
        trackInfoArrayList = LoadTracks.loadTracks();
        search(getSongName);

        songRecyclerListAdapter = new SongRecyclerListAdapter(this, newTrackInfoArrayList);
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

                trackInfoNum = inf;
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
                    playUri(inf.getUrl());
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
        newTrackInfoArrayList = new ArrayList<>();
        for (int i = 0; i < trackInfoArrayList.size(); i++) {
            try {
                if (trackInfoArrayList.get(i).getSongName().contains(song)) {
                    TrackInfo info = trackInfoArrayList.get(i);
                    newTrackInfoArrayList.add(info);
                    found = true;
                } else if (trackInfoArrayList.get(i).getArtistName().contains(song)) {
                    TrackInfo info = trackInfoArrayList.get(i);
                    newTrackInfoArrayList.add(info);
                    found = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!found) {
            setContentView(R.layout.no_songs);
        }
    }

    protected CallResult<Bitmap> imageConnection(String URI) {
        connect(true);
        ImageUri imageGetter = new ImageUri(URI);
        return mSpotifyAppRemote.getImagesApi().getImage(imageGetter);
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
                        //Toast.makeText(SearchResultsActivity.this, "Not Connected", Toast.LENGTH_SHORT).show();
                        connect(true);
                    }
                });


    }

    protected void subscribeToPlayerState() {

        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState();


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
