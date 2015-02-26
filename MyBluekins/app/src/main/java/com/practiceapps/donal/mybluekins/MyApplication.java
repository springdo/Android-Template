package com.practiceapps.donal.mybluekins;

import android.app.Application;
import android.content.Context;

/**
 * Created by donal on 23/02/2015.
 */
public class MyApplication extends Application{

    private static MyApplication sInstance;

    @Override
    // gets called as the app starts
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MyApplication getInstance(){
        return sInstance;
    }

    public static Context getAppContext (){
        return sInstance.getApplicationContext();
    }
}
