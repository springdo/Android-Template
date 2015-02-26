package com.practiceapps.donal.mybluekins.fragments;


import android.app.DownloadManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practiceapps.donal.mybluekins.*;
import com.practiceapps.donal.mybluekins.POJO.Jobs;
import com.practiceapps.donal.mybluekins.logging.L;
import com.practiceapps.donal.mybluekins.network.VolleySingleton;
import com.practiceapps.donal.mybluekins.utils.Utils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuildSummaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuildSummaryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // init these in onCreateView
    private VolleySingleton mVolley;
    private RequestQueue mRequestQ;
    private ArrayList<Jobs> mJobsArrayList =  new ArrayList<Jobs>();

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuildSummaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuildSummaryFragment newInstance(String param1, String param2) {
        BuildSummaryFragment fragment = new BuildSummaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public BuildSummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mVolley = VolleySingleton.getInstance();
        mRequestQ =  mVolley.getRequestQueue();
        L.mV(getActivity(), Utils.getUrl(getActivity()));
        JsonObjectRequest jsonObjectRequest =  new JsonObjectRequest(Request.Method.GET, /*"http://blue-jenkins.mybluemix.net/jenkins/api/json/"*/Utils.getUrl(getActivity())+"api/json/", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    parseJSONResponse(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                L.tL(getActivity(), "SUCCESS with ::"+response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                L.tL(getActivity(), "FAIL with ::"+error.toString());

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", Utils.getUser(getActivity()), Utils.getPassword(getActivity()));
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.NO_WRAP);
                params.put("Authorization", auth);
                return params;
            }
        };
        // DON"T FORGET TO ADD THE REQUEST OT THE QUEUE!!
        mRequestQ.add(jsonObjectRequest);
    }

    private void parseJSONResponse(JSONObject response) throws IOException {
        if(response.length() == 0 || response == null){
            return;
        }
        // Using JACKSON to create POJO from JSON
        // tut http://www.journaldev.com/2324/jackson-json-processing-api-in-java-example-tutorial

        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();
        //read JSON like DOM Parser
        JsonNode rootNode = objectMapper.readTree(response.toString());
        JsonNode jobsArrayNode = rootNode.path("jobs");

//        L.tS(getActivity(), jobsArrayNode.toString());
        L.mV(getActivity(),"JOBS ARRAY NODE :: "+jobsArrayNode.toString());
//          This iterator kept skipptin every second element and then bombed out with a no idex found type error
//        Iterator<JsonNode> jsonNodeIterator = jobsArrayNode.iterator();
//        while (jsonNodeIterator.hasNext()){
//            L.mV(getActivity(), String.valueOf(jsonNodeIterator.next()));
//            Jobs current = objectMapper.readValue(String.valueOf(jsonNodeIterator.next()), Jobs.class);
//            mJobsArrayList.add(current);
//        };

        int i = 0;
        while (i < jobsArrayNode.size()) {
            L.mV(getActivity(), String.valueOf(jobsArrayNode.get(i)));
            Jobs current = objectMapper.readValue(String.valueOf(jobsArrayNode.get(i)), Jobs.class);
            mJobsArrayList.add(current);
            i++;
        }


//        L.tS(getActivity(), String.valueOf(mJobsArrayList.get(2)));
//        L.tL(getActivity(), mJobsArrayList.get(2).getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_build_summary, container, false);

        mRecyclerView = (RecyclerView) layout.findViewById(R.id.build_summary_recycler);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        // Inflate the layout for this ;fragment
        return layout;

        // NOTE for grey keys are aborted, disabled, notbuilt
    }


}
