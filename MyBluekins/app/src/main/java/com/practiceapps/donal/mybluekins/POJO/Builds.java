package com.practiceapps.donal.mybluekins.POJO;

/**
 * Created by donal on 03/03/2015.
 */

public class Builds
{
    private String number;

    private String url;

    public String getNumber ()
    {
        return number;
    }

    public void setNumber (String number)
    {
        this.number = number;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [number = "+number+", url = "+url+"]";
    }
}
