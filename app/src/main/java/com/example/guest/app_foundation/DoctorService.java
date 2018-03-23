package com.example.guest.app_foundation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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


}
