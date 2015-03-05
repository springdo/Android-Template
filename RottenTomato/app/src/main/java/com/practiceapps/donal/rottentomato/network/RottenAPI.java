package com.practiceapps.donal.rottentomato.network;

import com.practiceapps.donal.rottentomato.pojo.MoviesInTheatre.MoviesInTheatre;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by donal on 03/03/2015.
 */
public interface RottenAPI {

    // consider moving the api key to an interceptor
    @GET("/api/public/v1.0/lists/movies/in_theaters.json?apikey=nrmsmrr2nvfwp26w93sp4swe")
    void InTheatres(Callback<MoviesInTheatre> cb);

    @GET("/api/public/v1.0/movies.json?apikey=nrmsmrr2nvfwp26w93sp4swe&page_limit=30&q={searchTerm}")
    void SearchMovies(Callback<SearchMovies> cb);

}
