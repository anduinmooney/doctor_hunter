package com.example.guest.app_foundation;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;

import butterknife.BindView;

/**
 * Created by Anduin on 3/16/2018.
 */

public class ListActivity extends AppCompatActivity {
    @BindView(R.id.aboutButton) Button mAboutButton;
    @BindView(R.id.contactButton) Button mContactButton;
    GridView gridView;
    String[] artists = new String[] {"Daft Punk", "Meteor", "Massive Attack", "Deon Custom", "Mr FijiWiji", "Rogue", "Caravan Palace", "Madeon", "DotEXE", "Break Bot", "Com Truise", "Occams Laser"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/DEFTONE.ttf");
        gridView = (GridView) findViewById(R.id.baseGridView);
        gridView.setAdapter(new SpotifyAdapter(this, artists, typeface));

        mAboutButton.setOnClickListener(this);
        mContactButton.setOnClickListener(this);
    }


}
