
package com.practiceapps.donal.rottentomato.pojo.SearchMovies;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class AlternateIds {

    @Expose
    private String imdb;

    /**
     * 
     * @return
     *     The imdb
     */
    public String getImdb() {
        return imdb;
    }

    /**
     * 
     * @param imdb
     *     The imdb
     */
    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public AlternateIds withImdb(String imdb) {
        this.imdb = imdb;
        return this;
    }

}
