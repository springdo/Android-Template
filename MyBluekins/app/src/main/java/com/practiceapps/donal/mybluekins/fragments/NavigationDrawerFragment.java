package com.practiceapps.donal.mybluekins.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.practiceapps.donal.mybluekins.interfaces.DrawerClickListener;
import com.practiceapps.donal.mybluekins.adapters.*;
import com.practiceapps.donal.mybluekins.*;
import com.practiceapps.donal.mybluekins.helper.*;
import com.practiceapps.donal.mybluekins.activities.*;
import com.practiceapps.donal.mybluekins.logging.L;


import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements DrawerClickListener {

    // Teh recyclerView has to be managed in code (using LayoutManager etc)
    private RecyclerView recyclerView;

    // this object is the core of what this class does
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View mContainerView;

    // used to show drawer by default if first run of app
    private boolean mUserLearnedDrawer;
    // used to check if user is starting for first time or has onResumed from a rotation
    private boolean mFromSavedInstanceState;

    // create a variable for the Recylcer View adapter
    // add a data item for the adapter
    private RecyclerListInformationAdapter recyclerListInformationAdapter;

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
        // Inflate the layout for this fragment normally done in a line
//        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        View myLayoutView = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) myLayoutView.findViewById(R.id.drawerList);
        // instantiate the adapter
        recyclerListInformationAdapter = new RecyclerListInformationAdapter(getActivity(), getData());
        // set the adapter on the recycler view
        recyclerView.setAdapter(recyclerListInformationAdapter);
        // set the layout manager to manage drawing the items
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // this fragment is object that implements the DrawerClickListener
        recyclerListInformationAdapter.setDrawerClickListener(this);

        TextView textViewSettings = (TextView) myLayoutView.findViewById(R.id.drawer_settings).findViewById(R.id.recyclerviewlist_text);
        textViewSettings.setText("Settings");
        TextView textViewSwitch = (TextView) myLayoutView.findViewById(R.id.drawer_switch).findViewById(R.id.recyclerviewlist_text);
        textViewSwitch.setText("Switch Account");
        ImageView switchIcon = (ImageView) myLayoutView.findViewById(R.id.drawer_switch).findViewById(R.id.recyclerviewlist_image);
        switchIcon.setImageResource(R.drawable.ic_switch);

        myLayoutView.findViewById(R.id.drawer_switch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SwitchActivity.class));
            }
        });

        myLayoutView.findViewById(R.id.drawer_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                L.tS(getActivity(), "drawer_settings clicked");
                startActivity(new Intent(getActivity(), SettingsActivity.class));
            }
        });

        return myLayoutView;

    }

    public static List <RecyclerListInformation> getData(){
        List<RecyclerListInformation> recyclerListInformationList = new ArrayList<RecyclerListInformation>();
        // need an array of images and array of titles for the drawer
        // material designs specifies these should be hardcoded!

        String[] titles ={"New Item", "People", "Build History", "Manage Jenkins", "Credentials", "My Views"};
        int[] icons = {R.drawable.ic_newitem, R.drawable.ic_people, R.drawable.ic_clipboard,
                R.drawable.ic_spanner, R.drawable.ic_manage, R.drawable.ic_myviews};

        for (int i = 0; i < titles.length && i < icons.length; i++){
            RecyclerListInformation dataItem =  new RecyclerListInformation();
            dataItem.setIconId(icons[i]);
            dataItem.setTitle(titles[i]);
            recyclerListInformationList.add(dataItem);
        }

        return recyclerListInformationList;
    }


    // not sure why example does not do all this in constructor..... hmmmm
    public void getInstance(int fragment_navigation_drawer, DrawerLayout drawerLayout, final Toolbar toolbar) {

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

//        // no saved instance and no previous show of the drawer
//        if (!mUserLearnedDrawer && !mFromSavedInstanceState){
//            mDrawerLayout.openDrawer(mContainerView);
//        }

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

    @Override
    public void drawerItemClicked(View view, int position) {

//        idea for each item on the drawer have switch stmnt
        // Start intent based on item clicked
        if (position == 1) {
            Toast.makeText(getActivity(), "toastposition 1", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), SubActivity.class));
        } else {
            startActivity(new Intent(getActivity(), SubActivity.class));
        }
    }

    @Override
    public void settingsClicked(View view) {

    }
}
