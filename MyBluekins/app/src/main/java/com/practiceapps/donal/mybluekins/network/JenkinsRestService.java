package com.practiceapps.donal.mybluekins.network;


import com.practiceapps.donal.mybluekins.POJO.Builds;

import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

/**
 * Created by donal on 03/03/2015.
 */
public interface JenkinsRestService {
//    @Header("Basic Auth: ") moved to AuthRequestInterceptor.class

    @GET("job/{jobName}/api/json")
    Builds recentBuilds(@Path("jobName") String jobName);
}
