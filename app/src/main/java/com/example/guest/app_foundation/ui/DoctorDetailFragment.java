package com.example.guest.app_foundation.ui;


import android.os.Bundle;
import android.app.Fragment;
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
public class DoctorDetailFragment extends Fragment {
    @BindView(R.id.doctorFirstNameTextView) TextView mFirstNameLabel;
    @BindView(R.id.doctorLastNameTextView) TextView mLastNameLabel;
    @BindView(R.id.doctorImageView) ImageView mImageLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.genderTextView) TextView mGenderLabel;
    @BindView(R.id.newPatientTextView) TextView mNewPatientLabel;
    @BindView(R.id.addressTextView) TextView mStreetLabel;
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

    public DoctorDetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mDoctor.getImageUrl()).into(mImageLabel);

        mFirstNameLabel.setText(mDoctor.getFirstName());
        mLastNameLabel.setText(mDoctor.getLastName());
        mPhoneLabel.setText(mDoctor.getPhone());
        mGenderLabel.setText(mDoctor.getGender());
        mNewPatientLabel.setText("Accepting New Patients: " + mDoctor.getNewPatient());
        mStreetLabel.setText(mDoctor.getAddress());

        return view;
    }

}
