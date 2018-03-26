package com.example.guest.app_foundation.services;
import com.example.guest.app_foundation.Constants;
import com.example.guest.app_foundation.models.Doctor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Anduin on 3/22/2018.
 */

public class DoctorService {

    public static void findDoctors(String location, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.DOCTOR_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.DOCTOR_LOCATION_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .header("Authorization", Constants.DOCTOR_TOKEN)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Doctor> processResults(Response response) {
        ArrayList<Doctor> doctors = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject betterDoctorJSON = new JSONObject(jsonData);
            JSONArray dataJSON = betterDoctorJSON.getJSONArray("data");
            for (int i = 0; i < dataJSON.length(); i++) {
                JSONObject doctorJSON = dataJSON.getJSONObject(i);
                String firstName = doctorJSON.getJSONObject("profile").getString("first_name");
                String lastName = doctorJSON.getJSONObject("profile").getString("last_name");
                String phone = doctorJSON.getJSONArray("practices").getJSONObject(0).getJSONArray("phones").getJSONObject(0).getString("number");
                String gender = doctorJSON.getJSONObject("profile").getString("gender");
                String imageUrl = doctorJSON.getJSONObject("profile").getString("image_url");
                String newPatient = doctorJSON.getJSONArray("practices").getJSONObject(0).getString("accepts_new_patients");
                String address = doctorJSON.getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("street");

                Doctor doctor = new Doctor(firstName, lastName, phone, gender,
                        imageUrl, newPatient, address);
                doctors.add(doctor);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return doctors;
    }


}


