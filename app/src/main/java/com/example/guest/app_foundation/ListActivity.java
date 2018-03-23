package com.example.guest.app_foundation;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.Callback;
import java.io.IOException;


import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Response;

/**
 * Created by Anduin on 3/16/2018.
 */

public class ListActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.aboutButton) Button mAboutButton;
    @BindView(R.id.contactButton) Button mContactButton;
    GridView gridView;
    String[] artists = new String[] {"Daft Punk", "Meteor", "Massive Attack", "Deon Custom", "Mr FijiWiji", "Rogue", "Caravan Palace", "Madeon", "DotEXE", "Break Bot", "Com Truise", "Occams Laser"};
    public static final String TAG = ListActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/DEFTONE.ttf");
        gridView = (GridView) findViewById(R.id.baseGridView);
        gridView.setAdapter(new SpotifyAdapter(this, artists, typeface));
        ButterKnife.bind(this);

        mAboutButton.setOnClickListener(this);
        mContactButton.setOnClickListener(this);
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        getDoctors(location);
    }

    private void getDoctors(String location) {
        final DoctorService doctorService = new DoctorService();
        doctorService.findDoctors(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        });
    }

    @Override
    public void onClick(View v) {
        if (v == mAboutButton) {
            Intent intent = new Intent(ListActivity.this, AboutActivity.class);
            startActivity(intent);
        } else if (v == mContactButton) {
            Intent intent = new Intent(ListActivity.this, ContactActivity.class);
            startActivity(intent);
        }

    }


}
