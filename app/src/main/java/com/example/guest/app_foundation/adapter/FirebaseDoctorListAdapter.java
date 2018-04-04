package com.example.guest.app_foundation.adapter;

import android.content.Context;

import com.example.guest.app_foundation.models.Doctor;
import com.example.guest.app_foundation.util.ItemTouchHelperAdapter;
import com.example.guest.app_foundation.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by Guest on 4/4/18.
 */

public class FirebaseDoctorListAdapter extends FirebaseRecyclerAdapter<Doctor, FirebaseDoctorViewHolder>  implements ItemTouchHelperAdapter {

    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseDoctorListAdapter(Class<Doctor> modelClass, int modelLayout,
                                         Class<FirebaseDoctorViewHolder> viewHolderClass,
                                         Query ref, OnStartDragListener onStartDragListener, Context context) {

        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

    @Override
    protected void populateViewHolder(FirebaseDoctorViewHolder viewHolder, Doctor model, int position) {
        viewHolder.bindDoctor(model);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}
