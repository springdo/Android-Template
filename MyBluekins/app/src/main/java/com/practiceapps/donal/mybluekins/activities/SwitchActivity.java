package com.practiceapps.donal.mybluekins.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.practiceapps.donal.mybluekins.R;
import com.practiceapps.donal.mybluekins.logging.L;


public class SwitchActivity extends ActionBarActivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        // used to customise the toolbar and enable home features
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toastFromPreferences(this, "");
    }


    public static void toastFromPreferences(Context context, /*String prefName, */String defaultValue /*used incase none saved*/){

         final String PREF_KEY_PORT = "PORT";
         final String PREF_KEY_DOMAIN = "DOMAIN";
         final String PREF_KEY_PROTOCOL = "PROTOCOL";
         final String PREF_KEY_USERNAME = "USERNAME";
         final String PREF_KEY_PASSPORT = "PASSWORD";
         final String PREF_FILE_NAME = "MY_PREFS";

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

//        sharedPreferences.getString(prefName, defaultValue);

        String url = sharedPreferences.getString(PREF_KEY_PROTOCOL, defaultValue) +
                sharedPreferences.getString(PREF_KEY_USERNAME, defaultValue) + ":"+
                sharedPreferences.getString(PREF_KEY_PASSPORT, defaultValue) + "@"+
                sharedPreferences.getString(PREF_KEY_DOMAIN, defaultValue) + ":"+
                sharedPreferences.getString(PREF_KEY_PORT, defaultValue) + "/jenkins/";
        L.tL(context, url);
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // special android defined case for up navigation
        if (id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        };

        return super.onOptionsItemSelected(item);
    }
}
