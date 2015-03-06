package com.practiceapps.donal.rottentomato.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.practiceapps.donal.rottentomato.R;
import com.practiceapps.donal.rottentomato.adapters.InTheatresAdapter;
import com.practiceapps.donal.rottentomato.bus.BusProvider;
import com.practiceapps.donal.rottentomato.events.DataLoadEvent;
import com.practiceapps.donal.rottentomato.events.SearchDataLoadedEvent;
import com.practiceapps.donal.rottentomato.logging.L;
import com.practiceapps.donal.rottentomato.pojo.MyMovies.*;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private Bus mBus = BusProvider.getInstance();
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private InTheatresAdapter mInTheatresAdapter;
    private ArrayList<Movie> mMovieArrayList =  new ArrayList<>();


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SearchFragment() {
        // Required empty public constructor
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
    }

    @Subscribe
    public void onSearchDataLoadedEvent(SearchDataLoadedEvent event){
        L.mV(getActivity(), "Search Movies data total data :: " + event.getmSearchMovies().getTotal());
        L.tS(getActivity(), "First Movie :: "+event.getmSearchMovies().getMovies().get(0).getTitle());
        L.mV(getActivity(), "Movies from search data total data :: " + event.getmSearchMovies().getTotal());
//        mInTheatresAdapter.setMovieArrayList(event.getmMoviesInTheatre().getMovies());
        mMovieArrayList = new ArrayList<>(event.getmSearchMovies().getMovies());
        mInTheatresAdapter.setMovieArrayList(mMovieArrayList);
        L.mV(getActivity(), "onSearchDataLoadedEvent movieArrayList :: "+ String.valueOf(mMovieArrayList));
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_search, container, false);

        mRecyclerView = (RecyclerView) layout.findViewById(R.id.search_recycler);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        // init and set data on the adaper
        mInTheatresAdapter = new InTheatresAdapter(getActivity(), mMovieArrayList);
        L.mV(getActivity(), "onCreateView data list :: "+String.valueOf(mMovieArrayList));
        mInTheatresAdapter.setMovieArrayList(mMovieArrayList);
        mRecyclerView.setAdapter(mInTheatresAdapter);
        return layout;
    }


}
