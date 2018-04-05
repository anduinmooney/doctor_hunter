package com.example.guest.app_foundation.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guest.app_foundation.Constants;
import com.example.guest.app_foundation.R;
import com.example.guest.app_foundation.adapter.FirebaseDoctorListAdapter;
import com.example.guest.app_foundation.adapter.FirebaseDoctorViewHolder;
import com.example.guest.app_foundation.models.Doctor;
import com.example.guest.app_foundation.util.OnStartDragListener;
import com.example.guest.app_foundation.util.SimpleItemTouchHelperCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SavedDoctorListFragment extends Fragment implements OnStartDragListener {

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private FirebaseDoctorListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;


    public SavedDoctorListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_doctor_list, container, false);
        ButterKnife.bind(this, view);
        setUpFirebaseAdapter();
        return view;
    }

    private void setUpFirebaseAdapter() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_DOCTORS)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);


        mFirebaseAdapter = new FirebaseDoctorListAdapter(Doctor.class,
                R.layout.doctor_list_layout_drag, FirebaseDoctorViewHolder.class,
                query, this, getActivity());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

}
