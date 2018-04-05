package com.example.guest.app_foundation.ui;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.guest.app_foundation.Constants;
import com.example.guest.app_foundation.R;
import com.example.guest.app_foundation.adapter.DoctorListAdapter;
import com.example.guest.app_foundation.models.Doctor;
import com.example.guest.app_foundation.services.DoctorService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorListFragment extends Fragment {
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private DoctorListAdapter mAdapter;
    public ArrayList<Doctor> mDoctors = new ArrayList<>();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentLocation;


    public DoctorListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mSharedPreferences.edit();

        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_list, container, false);
        ButterKnife.bind(this, view);

        mRecentLocation = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);

        if (mRecentLocation != null) {
            getDoctors(mRecentLocation);
        }

        return view;
    }

    private void getDoctors(String location) {
        final DoctorService doctorService = new DoctorService();
        doctorService.findDoctors(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response)  {

                mDoctors = doctorService.processResults(response);

                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new DoctorListAdapter(getActivity(), mDoctors);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(getActivity());
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setQueryHint("Search by State Abbreviation...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getDoctors(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }

}
