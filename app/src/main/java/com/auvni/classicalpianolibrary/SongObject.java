package com.auvni.classicalpianolibrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class SongObject extends RecyclerView.Adapter<SongObject.SongObjectHolder> {

    ArrayList<SongModel> songModels;
    Context context;

    public SongObject(Context setContext, ArrayList<SongModel> setSongModels) {
        this.context = setContext;
        this.songModels = setSongModels;
    }

    @Override
    public SongObjectHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View myView = LayoutInflater.from(context).inflate(R.layout.song_list_item, viewGroup, false);
        return new SongObjectHolder(myView);
    }

    @Override
    public void onBindViewHolder(SongObjectHolder songObjectHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return songModels.size();
    }


    public class SongObjectHolder extends RecyclerView.ViewHolder {
        TextView songName;
        TextView artistName;
        Button mediaPlayerButton;
        public SongObjectHolder(View v) {
            super(v);
            songName = (TextView) itemView.findViewById(R.id.songNameID);
            artistName = (TextView) itemView.findViewById(R.id.artistNameID);
            mediaPlayerButton = (Button) itemView.findViewById(R.id.openMediaPlayer);
        }
    }


}
