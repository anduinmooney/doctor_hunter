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
import android.widget.Toast;

import com.example.guest.app_foundation.Constants;
import com.example.guest.app_foundation.R;
import com.example.guest.app_foundation.models.Doctor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

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
    @BindView(R.id.bioTextView) TextView mBioLabel;
    @BindView(R.id.specialtyTextView) TextView mSpecialtyLabel;
    @BindView(R.id.saveDoctorButton) TextView mSaveDoctorButton;

    private ArrayList<Doctor> mDoctors;
    private int mPosition;


    private Doctor mDoctor;

    public static DoctorDetailFragment newInstance(ArrayList<Doctor> doctors, Integer position) {
        DoctorDetailFragment doctorDetailFragment = new DoctorDetailFragment();
        Bundle args = new Bundle();

        args.putParcelable(Constants.EXTRA_KEY_DOCTORS, Parcels.wrap(doctors));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);

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
        mBioLabel.setText(mDoctor.getBio());
        mSpecialtyLabel.setText(mDoctor.getSpecialty());

        mSaveDoctorButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mDoctor.getPhone()));
            startActivity(phoneIntent);
        }

        if (v == mSaveDoctorButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference doctorRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_DOCTORS)
                    .child(uid);
            DatabaseReference pushRef = doctorRef.push();
            String pushId = pushRef.getKey();
            mDoctor.setPushId(pushId);
            pushRef.setValue(mDoctor);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }

    }

}
