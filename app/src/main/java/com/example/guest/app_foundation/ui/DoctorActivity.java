package com.example.guest.app_foundation.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.example.guest.app_foundation.Constants;
import com.example.guest.app_foundation.adapter.DoctorListAdapter;
import com.example.guest.app_foundation.models.Doctor;
import com.example.guest.app_foundation.services.DoctorService;
import com.example.guest.app_foundation.R;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import java.io.IOException;
import java.util.ArrayList;



import butterknife.ButterKnife;
import okhttp3.Response;




/**
 * Created by Anduin on 3/16/2018.
 */

public class DoctorActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.aboutButton) Button mAboutButton;
    @BindView(R.id.contactButton) Button mContactButton;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private DoctorListAdapter mAdapter;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;

    public static final String TAG = DoctorActivity.class.getSimpleName();
    public ArrayList<Doctor> doctors = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/DEFTONE.ttf");
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
//        if (mRecentAddress != null) {
//            getDoctors(mRecentAddress);
//        }

        mAboutButton.setOnClickListener(this);
        mContactButton.setOnClickListener(this);
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        String query = intent.getStringExtra("query");

//        getDoctors(location);
//        getDoctors(query);


    }

    @Override
    public void onClick(View v) {
        if (v == mAboutButton) {
            Intent intent = new Intent(DoctorActivity.this, AboutActivity.class);
            startActivity(intent);
        } else if (v == mContactButton) {
            Intent intent = new Intent(DoctorActivity.this, ContactActivity.class);
            startActivity(intent);
        }

    }

    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }


}
