package com.example.guest.app_foundation.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.guest.app_foundation.Constants;
import com.example.guest.app_foundation.R;
import com.example.guest.app_foundation.adapter.FirebaseDoctorListAdapter;
import com.example.guest.app_foundation.adapter.FirebaseDoctorViewHolder;
import com.example.guest.app_foundation.models.Doctor;
import com.example.guest.app_foundation.util.OnStartDragListener;
import com.example.guest.app_foundation.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedDoctorListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mDoctorReference;
    private FirebaseDoctorListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_doctor);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_DOCTORS)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

        mDoctorReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_DOCTORS)
                .child(uid);

        mFirebaseAdapter = new FirebaseDoctorListAdapter(Doctor.class,
                R.layout.doctor_list_layout_drag, FirebaseDoctorViewHolder.class,
                query, this, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
