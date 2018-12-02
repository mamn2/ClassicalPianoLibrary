package com.auvni.classicalpianolibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class SearchResultsActivity extends AppCompatActivity {


    private ArrayList<TrackInfo> trackInfoArrayList = new ArrayList<TrackInfo>();
    RecyclerView recyclerView;
    SeekBar seekbar;
    SongRecyclerListAdapter songRecyclerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        songRecyclerListAdapter = new SongRecyclerListAdapter(this, trackInfoArrayList);
    }
}
