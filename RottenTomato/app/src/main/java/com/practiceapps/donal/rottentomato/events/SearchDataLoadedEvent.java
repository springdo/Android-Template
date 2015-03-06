package com.practiceapps.donal.rottentomato.events;

import com.practiceapps.donal.rottentomato.pojo.MyMovies.SearchMovies;

import java.util.ArrayList;

/**
 * Created by donal on 05/03/2015.
 */
public class SearchDataLoadedEvent {

    // no need for this type as obj are same so for adapter re-use keep same
//    private SearchMovies mSearchMovies;
    private SearchMovies mSearchMovies;

    public SearchDataLoadedEvent (SearchMovies movies){
        mSearchMovies = movies;

    }

    public SearchMovies getmSearchMovies() {
        return mSearchMovies;
    }

    public void setmSearchMovies(SearchMovies mSearchMovies) {
        this.mSearchMovies = mSearchMovies;
    }
}
