package com.example.guest.app_foundation;

/**
 * Created by Anduin on 3/22/2018.
 */

public class Constants {
    public static final String DOCTOR_TOKEN = BuildConfig.DOCTOR_TOKEN;
    public static final String DOCTOR_BASE_URL = ("https://api.betterdoctor.com/2016-03-01/doctors?" + "user_key=" + DOCTOR_TOKEN);
    public static final String DOCTOR_LOCATION_QUERY_PARAMETER = "location";
    public static final String PREFERENCES_LOCATION_KEY = "location";
    public static final String FIREBASE_CHILD_SEARCHED_LOCATION = "searchedLocation";
    public static final String FIREBASE_CHILD_DOCTORS = "doctors";
    public static final String FIREBASE_QUERY_INDEX = "index";
    public static final String EXTRA_KEY_POSITION = "position";
    public static final String EXTRA_KEY_RESTAURANTS = "restaurants";

}
