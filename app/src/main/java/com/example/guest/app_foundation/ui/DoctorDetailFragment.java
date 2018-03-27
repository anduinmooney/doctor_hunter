package com.example.guest.app_foundation.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.app_foundation.R;
import com.example.guest.app_foundation.models.Doctor;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorDetailFragment extends Fragment implements View.OnClickListener{
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;
    @BindView(R.id.doctorFirstNameTextView) TextView mFirstNameLabel;
    @BindView(R.id.doctorImageView) ImageView mImageLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.genderTextView) TextView mGenderLabel;
    @BindView(R.id.newPatientTextView) TextView mNewPatientLabel;
    @BindView(R.id.addressTextView) TextView mStreetLabel;
    @BindView(R.id.cityTextView) TextView mCityLabel;
    @BindView(R.id.stateTextView) TextView mStateLabel;
    @BindView(R.id.zipTextView) TextView mZipLabel;
    @BindView(R.id.saveDoctorButton) TextView mSaveDoctorButton;


    private Doctor mDoctor;

    public static DoctorDetailFragment newInstance(Doctor doctor) {
        DoctorDetailFragment doctorDetailFragment = new DoctorDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("doctor", Parcels.wrap(doctor));
        doctorDetailFragment.setArguments(args);
        return doctorDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDoctor = Parcels.unwrap(getArguments().getParcelable("doctor"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_detail, container, false);
        ButterKnife.bind(this, view);

        mPhoneLabel.setOnClickListener(this);

        Picasso.with(view.getContext())
                .load(mDoctor.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);

        mFirstNameLabel.setText(mDoctor.getFirstName());
        mPhoneLabel.setText(mDoctor.getPhone());
        mGenderLabel.setText(mDoctor.getGender());
        mNewPatientLabel.setText("Accepting New Patients: " + mDoctor.getNewPatient());
        mStreetLabel.setText(mDoctor.getAddress());
        mCityLabel.setText(", " + mDoctor.getCity());
        mStateLabel.setText(" " + mDoctor.getState());
        mZipLabel.setText(" " + mDoctor.getZip());

        return view;
    }

    @Override
    public void onClick(View v) {

        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mDoctor.getPhone()));
            startActivity(phoneIntent);
        }

    }

}
