package com.example.guest.app_foundation.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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
//    @BindView(R.id.locationEditText) EditText mLocationEditText;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;
    @BindView(R.id.savedDoctorsButton) Button mSavedDoctorsButton;

//    private DatabaseReference mSearchedLocationReference;
//    private ValueEventListener mSearchedLocationReferenceListener;

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mSearchedLocationReference = FirebaseDatabase
//                .getInstance()
//                .getReference()
//                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

//        mSearchedLocationReferenceListener = mSearchedLocationReference.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
//                    String location = locationSnapshot.getValue().toString();
//                    Log.d("Locations updated", "location: " + location);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });



        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
        Typeface yorkwhiteletter = Typeface.createFromAsset(getAssets(), "fonts/KGDefyingGravity.ttf");
        mAppNameTextView.setTypeface(yorkwhiteletter);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mViewListButton.setOnClickListener(this);
        mSavedDoctorsButton.setOnClickListener(this);

    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mSearchedLocationReference.removeEventListener(mSearchedLocationReferenceListener);
//    }

    @Override
    public void onClick(View v) {
        if (v == mViewListButton) {
//            String location = mLocationEditText.getText().toString();
//            if(!(location).equals("")) {
//                addToSharedPreferences(location);
//            }
//            addToSharedPreferences(location);
//            saveLocationToFirebase(location);
//            Toast.makeText(MainActivity.this, ("Showing list of doctors near " + location), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, DoctorActivity.class);
//            intent.putExtra("location", location);
            startActivity(intent);
        }
        if (v == mSavedDoctorsButton) {
            Intent intent = new Intent(MainActivity.this, SavedDoctorListActivity.class);
            startActivity(intent);
        }
    }

//    private void addToSharedPreferences(String location) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//    }
//
//    public void saveLocationToFirebase(String location) {
//        mSearchedLocationReference.push().setValue(location);
//    }




}


