package com.example.guest.app_foundation.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.guest.app_foundation.models.Doctor;
import com.example.guest.app_foundation.ui.DoctorDetailFragment;

import java.util.ArrayList;


public class DoctorPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Doctor> mDoctors;

    public DoctorPagerAdapter(FragmentManager fm, ArrayList<Doctor> doctors) {
        super(fm);
        mDoctors = doctors;
    }

    @Override
    public Fragment getItem(int position) {
        return DoctorDetailFragment.newInstance(mDoctors, position);
    }

    @Override
    public int getCount() {
        return mDoctors.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDoctors.get(position).getFirstName();
    }
}
