package com.example.guest.app_foundation.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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
import com.example.guest.app_foundation.util.OnDoctorSelectedListener;

import org.parceler.Parcels;

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

public class DoctorActivity extends AppCompatActivity implements OnDoctorSelectedListener {

    private Integer mPosition;
    ArrayList<Doctor> mDoctors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        if (savedInstanceState != null) {

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mPosition = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION);
                mDoctors = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_DOCTORS));

                if (mPosition != null && mDoctors != null) {
                    Intent intent = new Intent(this, DoctorDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);
                    intent.putExtra(Constants.EXTRA_KEY_DOCTORS, Parcels.wrap(mDoctors));
                    startActivity(intent);
                }

            }

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mPosition != null && mDoctors != null) {
            outState.putInt(Constants.EXTRA_KEY_POSITION, mPosition);
            outState.putParcelable(Constants.EXTRA_KEY_DOCTORS, Parcels.wrap(mDoctors));
        }

    }

    @Override
    public void onDoctorSelected(Integer position, ArrayList<Doctor> doctors) {
        mPosition = position;
        mDoctors = doctors;
    }
}




