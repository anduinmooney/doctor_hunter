package com.example.guest.app_foundation.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.guest.app_foundation.Constants;
import com.example.guest.app_foundation.R;
import com.example.guest.app_foundation.adapter.FirebaseDoctorViewHolder;
import com.example.guest.app_foundation.models.Doctor;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedDoctorListActivity extends AppCompatActivity {
    private DatabaseReference mDoctorReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_doctor);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mDoctorReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_DOCTORS)
                .child(uid);
        
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Doctor, FirebaseDoctorViewHolder>
                (Doctor.class, R.layout.doctor_list_layout, FirebaseDoctorViewHolder.class,
                        mDoctorReference) {

            @Override
            protected void populateViewHolder(FirebaseDoctorViewHolder viewHolder,
                                              Doctor model, int position) {
                viewHolder.bindDoctor(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
