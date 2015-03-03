
package com.practiceapps.donal.rottentomato.pojo;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class ReleaseDates {

    @Expose
    private String theater;

    /**
     * 
     * @return
     *     The theater
     */
    public String getTheater() {
        return theater;
    }

    /**
     * 
     * @param theater
     *     The theater
     */
    public void setTheater(String theater) {
        this.theater = theater;
    }

    public ReleaseDates withTheater(String theater) {
        this.theater = theater;
        return this;
    }

}
