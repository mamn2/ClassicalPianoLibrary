package com.auvni.classicalpianolibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.SeekBar;

import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;

public class SearchResultsActivity extends AppCompatActivity {


    private ArrayList<SongModel> songModels = new ArrayList<SongModel>();
    RecyclerView recyclerView;
    SeekBar seekbar;
    SongObject songObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        songObject = new SongObject(this, songModels);
    }
}
