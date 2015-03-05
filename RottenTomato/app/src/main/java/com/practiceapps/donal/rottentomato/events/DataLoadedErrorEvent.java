package com.practiceapps.donal.rottentomato.events;

import retrofit.RetrofitError;

/**
 * Created by donal on 04/03/2015.
 */
public class DataLoadedErrorEvent {
    private RetrofitError mRetrofitError;

    public DataLoadedErrorEvent(RetrofitError retrofitError){
        mRetrofitError = retrofitError;
    }

    public RetrofitError getmRetrofitError() {
        return mRetrofitError;
    }
}
