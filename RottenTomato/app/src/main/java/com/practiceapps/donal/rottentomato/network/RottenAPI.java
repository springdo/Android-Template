package com.practiceapps.donal.rottentomato.network;

import com.practiceapps.donal.rottentomato.pojo.MyMovies.SearchMovies;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by donal on 03/03/2015.
 */
public interface RottenAPI {

    // consider moving the api key to an interceptor
    @GET("/api/public/v1.0/lists/movies/in_theaters.json?apikey=nrmsmrr2nvfwp26w93sp4swe")
    void InTheatres(Callback<SearchMovies> cb);

//    http://api.rottentomatoes.com/api/public/v1.0/movies/770672122.json?apikey=[your_api_key]
    @GET("/api/public/v1.0/lists/movies/{id}.json?apikey=nrmsmrr2nvfwp26w93sp4swe")
    void MovieDetails(Callback<SearchMovies> cb);


    @GET("/api/public/v1.0/movies.json?apikey=nrmsmrr2nvfwp26w93sp4swe&page_limit=30")
    void SearchMovies(@Query("q") String searchTerm, Callback<SearchMovies> cb);

}
