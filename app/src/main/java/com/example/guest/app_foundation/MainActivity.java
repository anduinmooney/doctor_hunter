package com.example.guest.app_foundation;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
            Toast.makeText(MainActivity.this, ("Welcome, " + location), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
    }

}


