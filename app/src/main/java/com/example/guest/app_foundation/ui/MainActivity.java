package com.example.guest.app_foundation.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.app_foundation.Constants;
import com.example.guest.app_foundation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.viewListButton) Button mViewListButton;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;
    @BindView(R.id.savedDoctorsButton) Button mSavedDoctorsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
        Typeface yorkwhiteletter = Typeface.createFromAsset(getAssets(), "fonts/KGDefyingGravity.ttf");
        mAppNameTextView.setTypeface(yorkwhiteletter);

        mViewListButton.setOnClickListener(this);
        mSavedDoctorsButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        if (v == mViewListButton) {

            Intent intent = new Intent(MainActivity.this, DoctorActivity.class);
            startActivity(intent);
        }
        if (v == mSavedDoctorsButton) {
            Intent intent = new Intent(MainActivity.this, SavedDoctorListActivity.class);
            startActivity(intent);
        }
    }
}


