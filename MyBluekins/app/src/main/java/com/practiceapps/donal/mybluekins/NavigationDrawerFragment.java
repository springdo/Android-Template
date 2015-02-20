package com.practiceapps.donal.mybluekins;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    // this object is the core of what this class does
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View mContainerView;

    // used to show drawer by default if first run of app
    private boolean mUserLearnedDrawer;
    // used to check if user is starting for first time or has onResumed from a rotation
    private boolean mFromSavedInstanceState;

    // pref file name
    public static final String PREF_FILE_NAME = "testPref";
    // Key for onCreate
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Boolean wrapper used to cast from String to T / F
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        // ie if coming back from a rotation
        if (savedInstanceState != null){
            mFromSavedInstanceState =  true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }

    // not sure why example does not do all this in constructor..... hmmmm
    public void setUp(int fragment_navigation_drawer, DrawerLayout drawerLayout, final Toolbar toolbar) {

        // used to draw the drawer
        mContainerView =  getActivity().findViewById(fragment_navigation_drawer);

        mDrawerLayout =  drawerLayout;
        // initialise the toggle drawer
        mActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // if user never saw drawer before
                if (!mUserLearnedDrawer){
                    // tehy now have
                    mUserLearnedDrawer = true;
                    // save this in settings  // mUserLearned string cast
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer+"");
                }
                // redraws the activity menu
                getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // redraws the activity menu
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
//                Log.d(getActivity().getLocalClassName(), "Offset is   ::  " + slideOffset);
                // use if block to stop the thing going completely dark
                if (slideOffset < 0.6){
                    toolbar.setAlpha(1-slideOffset);
                }
            }

        };

        // no saved instance and no previous show of the drawer
        if (!mUserLearnedDrawer && !mFromSavedInstanceState){
            mDrawerLayout.openDrawer(mContainerView);
        }

        // DON't FORGET TO SET THE LISTENER FOR THE DRAWER!!
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        // create this new runable to keep the MainActivity in sync with the drawer
        // this is what gives the hamburger menu icon on action bar
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mActionBarDrawerToggle.syncState();
            }
        });

    }

    // read and write methods for shared pref. static invoc as not dependant on other obj's
    public static void saveToPreferences(Context context, String prefName, String prefValue){

        // first get shared pref object. passed the filename and context.MODE_PRIVATE so only my app
        // can edit / access this pref file
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putString(prefName, prefValue);
        editor.apply();
//        editor.commit(); could use this but this is slower as thread waits for commit() to return
    };

    public static String readFromPreferences(Context context, String prefName, String defaultValue /*used incase none saved*/){

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, context.MODE_PRIVATE);

        return sharedPreferences.getString(prefName, defaultValue);
    };
}
