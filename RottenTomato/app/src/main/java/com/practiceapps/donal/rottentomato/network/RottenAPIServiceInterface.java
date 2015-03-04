package com.practiceapps.donal.rottentomato.network;

import com.practiceapps.donal.rottentomato.pojo.MoviesInTheatre.MoviesInTheatre;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by donal on 03/03/2015.
 */
public interface RottenAPIServiceInterface {

    // consider moving the api key to an interceptor
    @GET("/api/public/v1.0/lists/movies/in_theaters.json?apikey=ntvm94xwxjwp3fkj8mnaer8u")
    void InTheatres(Callback<MoviesInTheatre> cb);


}
