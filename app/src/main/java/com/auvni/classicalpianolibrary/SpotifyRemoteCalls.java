package com.auvni.classicalpianolibrary;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;


public interface SpotifyRemoteCalls {

    public static final String CLIENT_ID = "9ae5066bba254f168f61e7229a7a1e3a";
    public static final String REDIRECT_URI = "http://localhost:8888/callback/";

    public static final int REQUEST_CODE = 1337;


}
