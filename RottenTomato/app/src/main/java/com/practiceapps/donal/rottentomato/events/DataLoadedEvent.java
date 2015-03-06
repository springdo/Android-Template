package com.practiceapps.donal.rottentomato.events;

import com.practiceapps.donal.rottentomato.pojo.MoviesInTheatre.Movie;
import com.practiceapps.donal.rottentomato.pojo.MyMovies.*;

/**
 * Created by donal on 04/03/2015.
 */
public class DataLoadedEvent {
    private SearchMovies mMoviesInTheatre;

    public DataLoadedEvent (SearchMovies moviesInTheatre){
        this.mMoviesInTheatre = moviesInTheatre;
    }

    public SearchMovies getmMoviesInTheatre() {
        return mMoviesInTheatre;
    }
}
