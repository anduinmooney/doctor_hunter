package com.example.guest.app_foundation;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ListAdapter;

/**
 * Created by Anduin on 3/16/2018.
 */

public class ListActivity extends AppCompatActivity {
    GridView gridView;
    String[] artists = new String[] {"Daft Punk", "Meteor", "Massive Attack", "Deon Custom", "Mr FijiWiji", "Rogue", "Caravan Palace", "Madeon", "DotEXE", "Break Bot", "Com Truise"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/DEFTONE.ttf");
        gridView = (GridView) findViewById(R.id.baseGridView);
        gridView.setAdapter(new SpotifyAdapter(this, artists, typeface));
    }


}
