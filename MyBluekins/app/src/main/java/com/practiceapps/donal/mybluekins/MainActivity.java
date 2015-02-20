package com.practiceapps.donal.mybluekins;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        navigationDrawerFragment.setUp(R.id.fragment_navigation_drawer, drawerLayout, mToolbar);
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
