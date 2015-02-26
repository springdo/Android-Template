package com.practiceapps.donal.mybluekins.network;

import android.content.Context;
import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.practiceapps.donal.mybluekins.logging.L;
import com.practiceapps.donal.mybluekins.utils.Utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by donal on 26/02/2015.
 */
public class JSONReq {
    /*
    public static JSONObject (final Context context){
        JsonObjectRequest jsonObjectRequest =  new JsonObjectRequest(Request.Method.GET, Utils.getUrl(getActivity())+"api/json/", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                L.tL(context, "SUCCESS with ::" + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                L.tL(context, "FAIL with ::"+error.toString());

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", Utils.getUser(context), Utils.getPassword(context));
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.NO_WRAP);
                params.put("Authorization", auth);
                return params;
            }
        };
        // DON"T FORGET TO ADD THE REQUEST OT THE QUEUE!!
        mRequestQ.add(jsonObjectRequest);
        return ;
    }
    */
}
