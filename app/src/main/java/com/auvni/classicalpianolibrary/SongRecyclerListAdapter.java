package com.auvni.classicalpianolibrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongRecyclerListAdapter extends RecyclerView.Adapter<SongRecyclerListAdapter.TrackHolder> {

    private ArrayList<TrackInfo> trackInfoArrayList;
    Context context;

    private ItemClickListener itemClickListener;

    public interface ItemClickListener {
        void onItemClick(Button b, View v, TrackInfo inf, int pos);
    }

    public void setItemClickListener(ItemClickListener setItemClickListener) {
        this.itemClickListener = setItemClickListener;

    }

    public SongRecyclerListAdapter(Context setContext, ArrayList<TrackInfo> setTrackInfos) {
        this.context = setContext;
        this.trackInfoArrayList = setTrackInfos;
    }

    @Override
    public TrackHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.song_list_item, viewGroup, false);
        return new TrackHolder(v);
    }

    @Override
    public void onBindViewHolder(final TrackHolder trackHolder, final int i) {
        final TrackInfo trackerInfo = trackInfoArrayList.get(i);
        trackHolder.songName.setText(trackerInfo.songName);
        trackHolder.artistName.setText(trackerInfo.artistName);
        trackHolder.mediaPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(trackHolder.mediaPlayerButton, v, trackerInfo, i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return trackInfoArrayList.size();
    }


    class TrackHolder extends RecyclerView.ViewHolder {
        TextView songName;
        TextView artistName;
        Button mediaPlayerButton;
        ImageView coverArt;

        TrackHolder(View v) {
            super(v);
            songName = v.findViewById(R.id.songNameID);
            artistName = v.findViewById(R.id.artistNameID);
            mediaPlayerButton = v.findViewById(R.id.openMediaPlayer);
            coverArt = v.findViewById(R.id.coverArt);
        }
    }


}
