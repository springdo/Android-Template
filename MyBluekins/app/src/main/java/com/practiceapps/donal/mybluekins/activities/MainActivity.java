package com.practiceapps.donal.mybluekins.activities;

import android.app.DownloadManager;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.practiceapps.donal.mybluekins.*;
import com.practiceapps.donal.mybluekins.adapters.ViewPagerAdapter;
import com.practiceapps.donal.mybluekins.fragments.*;
import com.practiceapps.donal.mybluekins.logging.L;
import com.practiceapps.donal.mybluekins.network.*;
import com.practiceapps.donal.mybluekins.tabs.SlidingTabLayout;

public class MainActivity extends ActionBarActivity {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private SlidingTabLayout mSlidingTabLayout;

    private final String PREF_KEY_DOMAIN = "DOMAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        activity_main_appbar is the one where the drawer comes out BELOW the app_bar
//        setContentView(R.layout.activity_main_appbar);
        setContentView(R.layout.activity_main);

        // using custom toolbar so gotta set it up
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        // used to show icon for home on the action bar ie hamburger
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //set up for drawer nav
//        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        NavigationDrawerFragment navigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // passing the toolbar to this so onConfigurationChanged and onOptionsItemSelected can be
        // custom handled by the NavigationDrawerFragment class
        navigationDrawerFragment.getInstance(R.id.fragment_navigation_drawer, drawerLayout, mToolbar);

        mViewPager = (ViewPager) findViewById(R.id.sliding_tabs_pager);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), getApplicationContext()));
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        // setCustomTabColorizer provides interface so override it inline
        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
//                return 0;
                return getResources().getColor(R.color.colorAccent);
            }
        });
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.primaryColor));
//        mSlidingTabLayout.setCustomTabView(R.layout.tab_text, R.id.tab_textview);
        // once init / customisatino is done call setViewPager
        mSlidingTabLayout.setViewPager(mViewPager);


        // ADDING VOLLEY HERE - think the tuts do it in a fragment but cba with another fragment
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://github.com/springdo/MFP-DevWorks-CI", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(getApplicationContext(), "RESPONSE IS :: "+response.toString(), Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ERROR IS :: "+error.toString(), Toast.LENGTH_LONG).show();

            }
        });
        // Don't forget to add to teh queue!
        requestQueue.add(stringRequest);

        // push settings activity unless corrected
        String domainPref = PreferenceManager.getDefaultSharedPreferences(this).getString(PREF_KEY_DOMAIN, " ");
//        L.tS(this, domainPref);
        if (domainPref.contains("mydomain.com") || domainPref.contains(" ") ){
            startActivity(new Intent(this, SettingsActivity.class));
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Toast.makeText(this, "Settings pressed", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.navigate){
            Intent intent = new Intent(this, SubActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
