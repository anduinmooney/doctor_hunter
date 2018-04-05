package com.example.guest.app_foundation.util;

import com.example.guest.app_foundation.models.Doctor;

import java.util.ArrayList;

/**
 * Created by Guest on 4/5/18.
 */

public interface OnDoctorSelectedListener {
    public void onDoctorSelected(Integer position, ArrayList<Doctor> doctors);
}
