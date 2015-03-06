package com.practiceapps.donal.rottentomato.events;

/**
 * Created by donal on 05/03/2015.
 */
public class SearchDataEvent {
    private String mSearchTerm;

    public SearchDataEvent (String searchTerm){
        mSearchTerm = searchTerm;
    }

    public String getmSearchTerm() {
        return mSearchTerm;
    }

    public void setmSearchTerm(String mSearchTerm) {
        this.mSearchTerm = mSearchTerm;
    }
}
