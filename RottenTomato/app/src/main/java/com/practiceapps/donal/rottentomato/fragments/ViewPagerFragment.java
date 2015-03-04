package com.practiceapps.donal.rottentomato.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.practiceapps.donal.rottentomato.MyApplication;
import com.practiceapps.donal.rottentomato.R;
import com.practiceapps.donal.rottentomato.logging.L;
import com.practiceapps.donal.rottentomato.network.RottenAPIServiceInterface;
import com.practiceapps.donal.rottentomato.pojo.MoviesInTheatre.MoviesInTheatre;

import javax.security.auth.callback.Callback;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;


public class ViewPagerFragment extends Fragment {
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

//        MoviesInTheatre moviesInTheatre = MyApplication.getRottenAPIServiceInterface().InTheatres();

//        RestAdapter restAdapter = new RestAdapter.Builder()
//                .setEndpoint("http://api.rottentomatoes.com")
//                .setConverter(new GsonConverter(new Gson()))
//                .build();
//        RottenAPIServiceInterface rottenAPIServiceInterface = restAdapter.create(RottenAPIServiceInterface.class);
//        rottenAPIServiceInterface.InTheatres(new retrofit.Callback<MoviesInTheatre>() {

        MyApplication.getRottenAPIServiceInterface().InTheatres(new retrofit.Callback<MoviesInTheatre>() {
            @Override
            public void success(MoviesInTheatre moviesInTheatre, Response response) {
                L.mV(getActivity(),  "Movies in Theatre data total data :: "+moviesInTheatre.getTotal());
                L.tS(getActivity(), moviesInTheatre.toString());

            }

            @Override
            public void failure(RetrofitError error) {
                L.tS(getActivity(), String.valueOf(error));

            }
        });

        return layout;
    }


}