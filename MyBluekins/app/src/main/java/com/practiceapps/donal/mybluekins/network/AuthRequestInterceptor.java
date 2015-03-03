package com.practiceapps.donal.mybluekins.network;

import android.content.Context;
import android.util.Base64;

import com.practiceapps.donal.mybluekins.MyApplication;
import com.practiceapps.donal.mybluekins.utils.Utils;

import retrofit.RequestInterceptor;

/**
 * Created by donal on 03/03/2015.
 */
public class AuthRequestInterceptor implements RequestInterceptor
{
    @Override
    public void intercept(RequestFacade request)
    {
        String creds = String.format("%s:%s", Utils.getUser(MyApplication.getAppContext()), Utils.getPassword(MyApplication.getAppContext()));
        String string = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.NO_WRAP);
        request.addHeader("Authorization", string);
    }
}