package com.example.guest.app_foundation.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.guest.app_foundation.models.Doctor;
import com.example.guest.app_foundation.services.DoctorService;
import com.example.guest.app_foundation.R;

import okhttp3.Call;
import okhttp3.Callback;
import java.io.IOException;
import java.util.ArrayList;
import static com.example.guest.app_foundation.R.id.listView;



import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Response;




/**
 * Created by Anduin on 3/16/2018.
 */

public class DoctorActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.aboutButton) Button mAboutButton;
    @BindView(R.id.contactButton) Button mContactButton;
    @BindView(listView) ListView mListView;

    public static final String TAG = DoctorActivity.class.getSimpleName();
    public ArrayList<Doctor> doctors = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/DEFTONE.ttf");
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



                    doctors = doctorService.processResults(response);
                    DoctorActivity.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            String[] doctorNames = new String[doctors.size()];
                            for (int i = 0; i < doctorNames.length; i++) {
                                doctorNames[i] = doctors.get(i).getFirstName();
                            }
                            ArrayAdapter adapter = new ArrayAdapter(DoctorActivity.this,
                                    android.R.layout.simple_list_item_1, doctorNames);
                            mListView.setAdapter(adapter);

                            for (Doctor doctor : doctors) {
                                Log.d(TAG, "First Name: " + doctor.getFirstName());
                                Log.d(TAG, "Last Name: " + doctor.getLastName());
                                Log.d(TAG, "Phone: " + doctor.getPhone());
                                Log.d(TAG, "Accepts New Patients: " + doctor.getNewPatient());
                                Log.d(TAG, "Address: " + doctor.getAddress());
                                Log.d(TAG, "Image Url: " + doctor.getImageUrl());
                                Log.d(TAG, "Gender: " + doctor.getGender());
                            }
                        }


                        });

                
            }


        });
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


}
