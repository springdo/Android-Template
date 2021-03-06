package com.practiceapps.donal.mybluekins.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.practiceapps.donal.mybluekins.logging.L;
import com.practiceapps.donal.mybluekins.*;

/**
 * Created by donal on 25/02/2015.
 */
public class Utils {

    public static String getUrl (Context context){

        String defaultValue = "";
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

//        sharedPreferences.getString(prefName, defaultValue);
        String url = mSharedPreferences.getString(context.getResources().getString(R.string.PREF_KEY_PROTOCOL), defaultValue) +
                mSharedPreferences.getString(context.getResources().getString(R.string.PREF_KEY_DOMAIN), defaultValue) + ":"+
                mSharedPreferences.getString(context.getResources().getString(R.string.PREF_KEY_PORT), defaultValue) + "/jenkins/";
//        L.tL(context, url);

        return url;
    }
    public static String getUser (Context context){
        String user ;
        String defaultValue = "";
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        user =  sharedPreferences.getString(context.getResources().getString(R.string.PREF_KEY_USERNAME), defaultValue);
        return user;
    }

    public static String getPassword (Context context){
        String password ;
        String defaultValue = "";
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        password =  sharedPreferences.getString(context.getResources().getString(R.string.PREF_KEY_PASSWORD), defaultValue);

        return password;
    }

    public static String getUrlWithPassword(Context context){
        final String PREF_KEY_PORT = "PORT";
        final String PREF_KEY_DOMAIN = "DOMAIN";
        final String PREF_KEY_PROTOCOL = "PROTOCOL";
        final String PREF_KEY_USERNAME = "USERNAME";
        final String PREF_KEY_PASSPORT = "PASSWORD";
        final String defaultValue = "";

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

//        sharedPreferences.getString(prefName, defaultValue);

        String url = sharedPreferences.getString(PREF_KEY_PROTOCOL, defaultValue) +
                sharedPreferences.getString(PREF_KEY_USERNAME, defaultValue) + ":"+
                sharedPreferences.getString(PREF_KEY_PASSPORT, defaultValue) + "@"+
                sharedPreferences.getString(PREF_KEY_DOMAIN, defaultValue) + ":"+
                sharedPreferences.getString(PREF_KEY_PORT, defaultValue) + "/jenkins/";
        return url;
    }
}
