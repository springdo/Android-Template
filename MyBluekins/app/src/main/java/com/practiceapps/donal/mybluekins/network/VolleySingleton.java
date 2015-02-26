package com.practiceapps.donal.mybluekins.network;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import com.practiceapps.donal.mybluekins.*;

/**
 * Created by donal on 23/02/2015.
 */
public class VolleySingleton {

    private static VolleySingleton volleySingletonInstance = null;
    private static RequestQueue mRequestQueue;

    // private constructor cuz we don't want anyone creating new instances of this singleton
    private VolleySingleton (){
        // in constructor init the request que
        // don't pass it the activity context but the application one
        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());

    };

    // create static method to get the instance or construct if not present
    public static VolleySingleton getInstance (){

        if (volleySingletonInstance == null){
            volleySingletonInstance = new VolleySingleton();
        }

        return volleySingletonInstance;

    }

    public static RequestQueue getRequestQueue(){
        return mRequestQueue;
    }



}
