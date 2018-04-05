package com.example.guest.app_foundation.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guest.app_foundation.R;
import com.example.guest.app_foundation.adapter.FirebaseDoctorListAdapter;
import com.example.guest.app_foundation.util.OnStartDragListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedDoctorListFragment extends Fragment implements OnStartDragListener {

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private FirebaseDoctorListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;


    public SavedDoctorListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_doctor_list, container, false);
        ButterKnife.bind(this, view);
        setUpFirebaseAdapter();
        return view;
    }

}
