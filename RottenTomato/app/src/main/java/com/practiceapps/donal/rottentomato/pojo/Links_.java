
package com.practiceapps.donal.rottentomato.pojo;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Links_ {

    @Expose
    private String self;
    @Expose
    private String next;
    @Expose
    private String alternate;

    /**
     * 
     * @return
     *     The self
     */
    public String getSelf() {
        return self;
    }

    /**
     * 
     * @param self
     *     The self
     */
    public void setSelf(String self) {
        this.self = self;
    }

    public Links_ withSelf(String self) {
        this.self = self;
        return this;
    }

    /**
     * 
     * @return
     *     The next
     */
    public String getNext() {
        return next;
    }

    /**
     * 
     * @param next
     *     The next
     */
    public void setNext(String next) {
        this.next = next;
    }

    public Links_ withNext(String next) {
        this.next = next;
        return this;
    }

    /**
     * 
     * @return
     *     The alternate
     */
    public String getAlternate() {
        return alternate;
    }

    /**
     * 
     * @param alternate
     *     The alternate
     */
    public void setAlternate(String alternate) {
        this.alternate = alternate;
    }

    public Links_ withAlternate(String alternate) {
        this.alternate = alternate;
        return this;
    }

}
