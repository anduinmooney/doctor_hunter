package com.example.guest.app_foundation.adapter;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.app_foundation.Constants;
import com.example.guest.app_foundation.R;
import com.example.guest.app_foundation.models.Doctor;
import com.example.guest.app_foundation.ui.DoctorDetailActivity;
import com.example.guest.app_foundation.util.ItemTouchHelperViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by Guest on 3/29/18.
 */

public class FirebaseDoctorViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;
    public ImageView mDoctorImageView;

    public FirebaseDoctorViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();

    }

    public void bindDoctor(Doctor doctor) {
        mDoctorImageView = (ImageView) mView.findViewById(R.id.doctorImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.doctorFirstNameTextView);
        TextView phoneTextView = (TextView) mView.findViewById(R.id.phoneTextView);
        TextView addressTextView = (TextView) mView.findViewById(R.id.addressTextView);
        TextView lastNameTextView = (TextView) mView.findViewById(R.id.doctorLastNameTextView);

        Picasso.with(mContext)
                .load(doctor.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mDoctorImageView);

        nameTextView.setText(doctor.getFirstName());
        phoneTextView.setText(doctor.getPhone());
        addressTextView.setText(doctor.getAddress());
        lastNameTextView.setText(doctor.getLastName());
    }

    @Override
    public void onItemSelected() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_on);
        set.setTarget(itemView);
        set.start();

    }

    @Override
    public void onItemClear() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_off);
        set.setTarget(itemView);
        set.start();

    }
}
