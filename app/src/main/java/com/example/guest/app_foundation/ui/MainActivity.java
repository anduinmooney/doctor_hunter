package com.example.guest.app_foundation.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.app_foundation.Constants;
import com.example.guest.app_foundation.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.viewListButton) Button mViewListButton;
    @BindView(R.id.locationEditText) EditText mLocationEditText;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;

    private DatabaseReference mSearchedLocationReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
        Typeface yorkwhiteletter = Typeface.createFromAsset(getAssets(), "fonts/KGDefyingGravity.ttf");
        mAppNameTextView.setTypeface(yorkwhiteletter);

        mViewListButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mViewListButton) {
            String location = mLocationEditText.getText().toString();
            saveLocationToFirebase(location);
            Toast.makeText(MainActivity.this, ("Showing list of doctors near " + location), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, DoctorActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);

        }
    }

    public void saveLocationToFirebase(String location) {
        mSearchedLocationReference.push().setValue(location);
    }


}


