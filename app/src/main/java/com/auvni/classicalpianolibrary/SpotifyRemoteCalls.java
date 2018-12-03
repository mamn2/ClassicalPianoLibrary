package com.auvni.classicalpianolibrary;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;


public class SpotifyRemoteCalls {

    private static final String CLIENT_ID = "9ae5066bba254f168f61e7229a7a1e3a";
    private static final String REDIRECT_URI = "com.auvni.classicalpianolibrary://callback";
    private SpotifyAppRemote mSpotifyAppRemote;

    ConnectionParams connectionParams =
            new ConnectionParams.Builder(CLIENT_ID)
                    .setRedirectUri(REDIRECT_URI)
                    .showAuthView(true)
                    .build();



}
