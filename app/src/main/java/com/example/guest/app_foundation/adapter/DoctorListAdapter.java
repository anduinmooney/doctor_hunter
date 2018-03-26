package com.example.guest.app_foundation.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.app_foundation.R;
import com.example.guest.app_foundation.models.Doctor;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder> {
    private ArrayList<Doctor> mDoctors = new ArrayList<>();
    private Context mContext;

    public DoctorListAdapter(Context context, ArrayList<Doctor> doctors) {
        mContext = context;
        mDoctors = doctors;
    }

    @Override
    public DoctorListAdapter.DoctorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list_layout, parent, false);
        DoctorViewHolder viewHolder = new DoctorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DoctorListAdapter.DoctorViewHolder holder, int position) {
        holder.bindDoctor(mDoctors.get(position));
    }

    @Override
    public int getItemCount() {
        return mDoctors.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.doctorImageView) ImageView mDoctorImageView;
        @BindView(R.id.doctorFirstNameTextView) TextView mFirstNameTextView;
        @BindView(R.id.doctorLastNameTextView) TextView mLastNameTextView;
        @BindView(R.id.addressTextView) TextView mAddressTextView;
        @BindView(R.id.phoneTextView) TextView mPhoneTextView;
        private Context mContext;

        public DoctorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindDoctor(Doctor doctor) {
            mFirstNameTextView.setText(doctor.getFirstName());
            mLastNameTextView.setText(doctor.getLastName());
            mAddressTextView.setText(doctor.getAddress());
            mPhoneTextView.setText(doctor.getPhone());
        }
    }
}
