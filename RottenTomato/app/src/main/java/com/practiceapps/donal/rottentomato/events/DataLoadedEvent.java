package com.practiceapps.donal.rottentomato.events;

import com.practiceapps.donal.rottentomato.pojo.MoviesInTheatre.Movie;
import com.practiceapps.donal.rottentomato.pojo.MoviesInTheatre.MoviesInTheatre;

/**
 * Created by donal on 04/03/2015.
 */
public class DataLoadedEvent {
    private MoviesInTheatre mMoviesInTheatre;

    public DataLoadedEvent (MoviesInTheatre moviesInTheatre){
        this.mMoviesInTheatre = moviesInTheatre;
    }

    public MoviesInTheatre getmMoviesInTheatre() {
        return mMoviesInTheatre;
    }
}
