package com.practiceapps.donal.rottentomato.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.practiceapps.donal.rottentomato.*;
import com.practiceapps.donal.rottentomato.fragments.InTheatresFragment;
import com.practiceapps.donal.rottentomato.fragments.ViewPagerFragment;

/**
 * Created by donal on 23/02/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
// use FragmentStatePagerAdapter otherwise onStateChange will not be called when
// swiping across ViewPages and you will loose the data.
    // use FragmentPagerAdapter if there is no dynamic data ie no cost of memory then as no state keeping needed
// public class ViewPagerAdapter extends FragmentPagerAdapter{

    String[] tabs;

    // old cp code . . .. .
    private final int IN_THEATRES = 1;
    private final int BUILD_QUEUE = 0;
    private final int BUILD_NODES = 2;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);

        // this is where the data for the tab's text is set
        tabs = context.getResources().getStringArray(R.array.tabs);

    }

    @Override
    // method called automagically when user moves item on the tab bar
    public Fragment getItem(int position) {
//        ViewPagerFragment fragment = ViewPagerFragment.getInstance(position);

        // create frag variable
        Fragment fragment = null;
        switch (position){
            case IN_THEATRES :
                fragment = InTheatresFragment.getInstance(position);
                break;
            default : fragment = ViewPagerFragment.getInstance(position);
                break;
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabs[position];
    }

    @Override
    public int getCount() {
        return 6;
    }
}
