package com.example.guest.app_foundation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Anduin on 3/16/2018.
 */

public class SpotifyAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mArtists;

    @Override
    public int getCount() {
        return mArtists.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = inflater.inflate(R.layout.artist_grid_item, null);


            TextView artistView = (TextView) gridView
                    .findViewById(R.id.grid_item_artist);


            artistView.setText(mArtists[position]);
        } else {
            gridView = (View) convertView;
        }
        return gridView;
    }

    public SpotifyAdapter (Context context, String[] artists){
        this.mContext = context;
        this.mArtists = artists;
    }


}
