package com.practiceapps.donal.rottentomato;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.practiceapps.donal.rottentomato.bus.BusProvider;
import com.practiceapps.donal.rottentomato.events.DataLoadedErrorEvent;
import com.practiceapps.donal.rottentomato.logging.L;
import com.practiceapps.donal.rottentomato.network.RottenAPI;
import com.practiceapps.donal.rottentomato.network.RottenServRepo;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by donal on 03/03/2015.
 */
public class MyApplication extends Application {

    private static MyApplication sInstance;
    private Bus mBus = BusProvider.getInstance();
    private RottenServRepo mRottenServRepo;
    private static RottenAPI sRottenAPIServiceInterface;

    @Override
    // gets called as the app starts
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.rottentomatoes.com")
                .setConverter(new GsonConverter(new Gson()))
                .build();
        this.sRottenAPIServiceInterface = restAdapter.create(RottenAPI.class);

        mRottenServRepo = new RottenServRepo(sRottenAPIServiceInterface, mBus);

        mBus.register(mRottenServRepo);
        //listen for "global" events such as the errror hanlder
        mBus.register(this);

    }

    @Subscribe
    public void onDataLoadError(DataLoadedErrorEvent event){
        L.tS(getApplicationContext(), "Error - try again later");
        L.mV(getApplicationContext(), event.getmRetrofitError().getMessage());
    }

    public static MyApplication getInstance(){
        return sInstance;
    }

    public static RottenAPI getRottenAPIServiceInterface() {return sRottenAPIServiceInterface;}

    public static Context getAppContext (){
        return sInstance.getApplicationContext();
    }
}
