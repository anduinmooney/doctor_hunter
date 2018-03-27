package com.example.guest.app_foundation;

/**
 * Created by Anduin on 3/22/2018.
 */

public class Constants {
    public static final String DOCTOR_TOKEN = BuildConfig.DOCTOR_TOKEN;
    public static final String DOCTOR_BASE_URL = ("https://api.betterdoctor.com/2016-03-01/doctors?" + "user_key=" + DOCTOR_TOKEN);
    public static final String DOCTOR_LOCATION_QUERY_PARAMETER = "location";
    public static final String DOCTOR_QUERY_QUERY_PARAMETER = "query";

}
