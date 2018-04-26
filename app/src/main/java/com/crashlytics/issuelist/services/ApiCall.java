package com.crashlytics.issuelist.services;

import com.crashlytics.issuelist.common.Constants;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Venkat-Tesark on 25-04-2018.
 */
public class ApiCall {

    private static ApiCall apiCall;

    private ApiCall() {

    }

    public static synchronized ApiCall getApiCall() {

        if (apiCall == null) {

            apiCall = new ApiCall();
        }

        return apiCall;
    }

    private ApiInterface getRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        return apiInterface;
    }


    public Call makeAPICall(RequestID requestID, Map<String, String> hashMap) {

        Call call = null;

        switch (requestID) {

            case REQ_GET_ISSUES:
                call = getRetrofit().getIssueList(hashMap);
                break;

            case REQ_GET_ISSUE_COMMENTS:
                call = getRetrofit().getIssueCommentsList(hashMap.get("issueNo"));
                break;


        }

        return call;


    }
}
