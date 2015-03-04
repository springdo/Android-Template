package com.practiceapps.donal.rottentomato;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.practiceapps.donal.rottentomato.network.RottenAPIServiceInterface;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by donal on 03/03/2015.
 */
public class MyApplication extends Application {

    private static MyApplication sInstance;
    private static RottenAPIServiceInterface sRottenAPIServiceInterface;

    @Override
    // gets called as the app starts
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.rottentomatoes.com")
                .setConverter(new GsonConverter(new Gson()))
                .build();
        this.sRottenAPIServiceInterface = restAdapter.create(RottenAPIServiceInterface.class);
    }

    public static MyApplication getInstance(){
        return sInstance;
    }

    public static RottenAPIServiceInterface getRottenAPIServiceInterface() {return sRottenAPIServiceInterface;}

    public static Context getAppContext (){
        return sInstance.getApplicationContext();
    }
}
