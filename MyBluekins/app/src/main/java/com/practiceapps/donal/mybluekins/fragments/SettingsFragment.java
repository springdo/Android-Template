package com.practiceapps.donal.mybluekins.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.practiceapps.donal.mybluekins.*;
import com.practiceapps.donal.mybluekins.logging.L;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private final String PREF_KEY_PORT = "PORT";
    private final String PREF_KEY_DOMAIN = "DOMAIN";
    private final String PREF_KEY_PROTOCOL = "PROTOCOL";
    private final String PREF_KEY_USERNAME = "USERNAME";
    private final String PREF_KEY_PASSPORT = "PASSWORD";


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        addPreferencesFromResource(R.layout.fragment_settings);
        bindPreferenceSummaryToValue(findPreference(PREF_KEY_DOMAIN));
        bindPreferenceSummaryToValue(findPreference(PREF_KEY_PORT));
        bindPreferenceSummaryToValue(findPreference(PREF_KEY_USERNAME));
        bindPreferenceSummaryToValue(findPreference(PREF_KEY_PASSPORT));
        bindPreferenceSummaryToValue(findPreference(PREF_KEY_PROTOCOL));

    }

    private void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(this);

        // Trigger the listener immediately with the preference's
        // current value.
        onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    public void toastFromPreferences(Context context, /*String prefName, */String defaultValue /*used incase none saved*/){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

//        sharedPreferences.getString(prefName, defaultValue);

        String url = sharedPreferences.getString(PREF_KEY_PROTOCOL, defaultValue) +
                sharedPreferences.getString(PREF_KEY_USERNAME, defaultValue) + ":"+
                sharedPreferences.getString(PREF_KEY_PASSPORT, defaultValue) + "@"+
                sharedPreferences.getString(PREF_KEY_DOMAIN, defaultValue) + ":"+
                sharedPreferences.getString(PREF_KEY_PORT, defaultValue) + "/jenkins/";
//        L.tS(context, url);
    };
    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        String stringValue = o.toString();
//        Log.v(LOG_TAG, "onPreferenceChange of  " + stringValue);
        L.mV(getActivity(), stringValue);

        if (preference instanceof ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list (since they have separate labels/values).
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
            // For other preferences, set the summary to the value's simple string representation.
            preference.setSummary(stringValue);
        }

//        BROKEN - keeps showing up the value previous to the one on summary
//        toastFromPreferences(getActivity(), "");
        return true;
    }
}
