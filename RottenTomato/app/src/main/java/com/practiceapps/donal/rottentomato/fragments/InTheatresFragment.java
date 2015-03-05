package com.practiceapps.donal.rottentomato.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.practiceapps.donal.rottentomato.R;
import com.practiceapps.donal.rottentomato.adapters.InTheatresAdapter;
import com.practiceapps.donal.rottentomato.bus.BusProvider;
import com.practiceapps.donal.rottentomato.events.DataLoadEvent;
import com.practiceapps.donal.rottentomato.events.DataLoadedEvent;
import com.practiceapps.donal.rottentomato.logging.L;
import com.practiceapps.donal.rottentomato.pojo.MoviesInTheatre.Movie;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;


public class InTheatresFragment extends Fragment {

    private Bus mBus;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private InTheatresAdapter mInTheatresAdapter;
    private ArrayList <Movie> movieArrayList =  new ArrayList<>();



    // pass in position to show which fragment to display
    public static InTheatresFragment getInstance (int position){
        InTheatresFragment inTheatresFragment = new InTheatresFragment();
        // pass some data to the fragment
        Bundle args = new Bundle();
        // shuolld use a key defined in strings or some such
        args.putInt("POSITION", position);
        // set this value in the fragments
        inTheatresFragment.setArguments(args);
        return inTheatresFragment;
    }

    @Override
    public void onPause() {
        super.onPause();
        mBus.unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mBus.register(this);
        mBus.post(new DataLoadEvent());
    }

    @Subscribe
    public void onDataLoadedEvent(DataLoadedEvent event){
        L.mV(getActivity(), "Movies in Theatre data total data :: " + event.getmMoviesInTheatre().getTotal());
//        mInTheatresAdapter.setMovieArrayList(event.getmMoviesInTheatre().getMovies());
        movieArrayList = new ArrayList<>(event.getmMoviesInTheatre().getMovies());
        mInTheatresAdapter.setMovieArrayList(movieArrayList);
        L.mV(getActivity(), "onDataLoadedEvent movieArrayList :: "+ String.valueOf(movieArrayList));

//        L.tS(getActivity(), event.getmMoviesInTheatre().toString());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_in_theatres, container, false);

        /* ORIGINAL CODE FOR GETTING RESPONSE FROM RETROFIT
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
        */

        mBus = BusProvider.getInstance();

        mRecyclerView = (RecyclerView) layout.findViewById(R.id.in_theatres_recycler);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        // init and set data on the adaper
        mInTheatresAdapter = new InTheatresAdapter(getActivity(), movieArrayList);
        L.mV(getActivity(), "onCreateView data list :: "+String.valueOf(movieArrayList));
        mInTheatresAdapter.setMovieArrayList(movieArrayList);
        mRecyclerView.setAdapter(mInTheatresAdapter);

        return layout;
    }


}
