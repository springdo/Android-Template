package com.practiceapps.donal.rottentomato.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.practiceapps.donal.rottentomato.R;
import com.practiceapps.donal.rottentomato.bus.BusProvider;
import com.practiceapps.donal.rottentomato.events.DataLoadEvent;
import com.practiceapps.donal.rottentomato.events.DataLoadedEvent;
import com.practiceapps.donal.rottentomato.logging.L;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;


public class ViewPagerFragment extends Fragment {

    private Bus mBus;

    // pass in position to show which fragment to display
    public static ViewPagerFragment getInstance (int position){
        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        // pass some data to the fragment
        Bundle args = new Bundle();
        // shuolld use a key defined in strings or some such
        args.putInt("POSITION", position);
        // set this value in the fragments
        viewPagerFragment.setArguments(args);
        return viewPagerFragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // idea use another swich to manage diff frags based on teh int vaule passed in args
        // idea wrong - this idea needs to go into the adapter which calls the fragment for a
        // given position in the ViewPager
        View layout = inflater.inflate(R.layout.fragment_view_pager, container, false);

        TextView textView = (TextView) layout.findViewById(R.id.view_pager_fragment_textview);
        Bundle bundle  = getArguments();
        if (bundle !=null){
            textView.setText("Dummy Fragment you should not see this :: pos  "+bundle.getInt("POSITION"));
        }

        return layout;
    }


}
