
package com.practiceapps.donal.rottentomato.pojo.SearchMovies;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class AbridgedCast {

    @Expose
    private String name;
    @Expose
    private String id;
    @Expose
    private List<String> characters = new ArrayList<String>();

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    public AbridgedCast withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    public AbridgedCast withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The characters
     */
    public List<String> getCharacters() {
        return characters;
    }

    /**
     * 
     * @param characters
     *     The characters
     */
    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    public AbridgedCast withCharacters(List<String> characters) {
        this.characters = characters;
        return this;
    }

}
