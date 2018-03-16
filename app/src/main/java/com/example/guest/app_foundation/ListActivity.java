package com.example.guest.app_foundation;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

/**
 * Created by Anduin on 3/16/2018.
 */

public class ListActivity extends AppCompatActivity {
    GridView gridView;
    String[] mockData = new String[] {"Daft Punk", "Meteor", "Massive Attack", "Deon Custom", "Mr FijiWiji", "Rogue", "Caravan Palace", "Madeon", "DotEXE", "BreakBot", "Com Truise"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.baseGridView);
        gridView.setAdapter(new SpotifyAdapter(this, artists));
    }


}
