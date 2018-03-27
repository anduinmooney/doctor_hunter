package com.example.guest.app_foundation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guest.app_foundation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.viewListButton) Button mViewListButton;
    @BindView(R.id.locationEditText) EditText mLocationEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mViewListButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mViewListButton) {
            String location = mLocationEditText.getText().toString();
            String query = mLocationEditText.getText().toString();
            Toast.makeText(MainActivity.this, ("Showing list of doctors near " + location), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, DoctorActivity.class);
            intent.putExtra("location", location);
            intent.putExtra("query", query);
            startActivity(intent);
        }
    }

}


