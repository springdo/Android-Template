package com.practiceapps.donal.rottentomato.network;

import com.practiceapps.donal.rottentomato.events.DataLoadEvent;
import com.practiceapps.donal.rottentomato.events.DataLoadedErrorEvent;
import com.practiceapps.donal.rottentomato.events.DataLoadedEvent;
import com.practiceapps.donal.rottentomato.pojo.MoviesInTheatre.MoviesInTheatre;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by donal on 04/03/2015.
 */
public class RottenServRepo {
    // class manages the API interaction.
    // called instead of making rest req from the activity.

    private RottenAPI mRottenAPI;
    private Bus mBus;

    public RottenServRepo (RottenAPI restAPI, Bus bus){
        this.mBus = bus;
        this.mRottenAPI = restAPI;
    }


    // subscribe to the call for data event
    @Subscribe
    public void onLoadDataEvent (DataLoadEvent event){
        mRottenAPI.InTheatres(new Callback<MoviesInTheatre>() {
            @Override
            public void success(MoviesInTheatre moviesInTheatre, Response response) {
                mBus.post(new DataLoadedEvent(moviesInTheatre));
            }

            @Override
            public void failure(RetrofitError error) {
                mBus.post(new DataLoadedErrorEvent(error));
            }
        });
    }

}
