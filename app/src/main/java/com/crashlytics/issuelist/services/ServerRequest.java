package com.crashlytics.issuelist.services;

import android.app.ProgressDialog;
import android.content.Context;

import com.crashlytics.issuelist.common.Logs;
import com.crashlytics.issuelist.common.Utility;
import com.crashlytics.issuelist.models.ErrorResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Venkat-Tesark on 03-08-2017.
 */

public class ServerRequest implements Callback{

    private static ServerRequest serverRequest;
    private RequestID requestID;
    private static Context context;
    private ServerListener serverListener;
    private static ProgressDialog progressDialog;
    private boolean showProgressDialog = true;
    private Map<String,String> headerMap;
    private Map<String,String> paramsMap;
    private String paramsString;
    private Object requestObject;


    private ServerRequest() {

    }

    //Singleton method
    public static ServerRequest with(Context context1) {

        if (serverRequest == null) {

            serverRequest = new ServerRequest();
        }

        context = context1;

        return serverRequest;
    }

    public ServerRequest setProgressDialog(boolean cancelable, String message) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(cancelable);
        progressDialog.setMessage(message);
        return serverRequest;
    }

    public ServerRequest setProgressDialogVisible(boolean visible) {

        showProgressDialog = visible;

        return serverRequest;
    }

    public ServerRequest setProgressDialog(boolean cancelable) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(cancelable);
        progressDialog.setMessage("Please Wait...");
        return serverRequest;
    }

    public ServerRequest setRequestID(RequestID requestID) {

        this.requestID = requestID;
        Logs.e(this, "requestID = " + requestID);
        return serverRequest;
    }

    public ServerRequest setHeader(Map<String, String> header) {

        headerMap = header;
        Logs.e(this,"header = "+headerMap);

        return serverRequest;
    }

    public ServerRequest setParams(Map<String, String> params) {

        paramsMap = params;
        Logs.e(this, "params=" + paramsMap);
        return serverRequest;
    }

    public ServerRequest setParams(String params) {

        paramsString = params;
        Logs.e(this, "params=" + paramsString);
        return serverRequest;
    }

    public ServerRequest setParams(Object params) {

        requestObject = params;
        Logs.e(this, "params=" + params.toString());
        return serverRequest;
    }

    public ServerRequest setCallBack(ServerListener callBack) {

        serverListener = callBack;
        Logs.e(this, "callback=" + serverListener);
        return serverRequest;
    }

    private void showDialog() {

        if (progressDialog != null && !progressDialog.isShowing() && showProgressDialog) {

            progressDialog.show();
        }
    }

    private void dismissDialog() {

        if (progressDialog != null && progressDialog.isShowing()) {

            progressDialog.dismiss();
        }
    }


    public void build() {

        if (Utility.with(context).isInternetConnected()) {

            showDialog();

            Call call = ApiCall.getApiCall().makeAPICall(requestID,paramsMap);

            call.enqueue(this);

        } else {

            Utility.with(context).showToast("Please check your internet connection");
        }

    }


    @Override
    public void onResponse(Call call, Response response) {

        dismissDialog();

        Logs.e(this, "code = " + response.code());

        if (response != null && response.isSuccessful()) {

            System.out.println(response.body());
            Logs.e(this, "response = " +new Gson().toJson(response.body()));

            handleSuccessResponse(response.body());

        } else {

            try {

                String errorData =  response.errorBody().string();

                Logs.e(this, "response = " +errorData);

                handleErrorResponse(errorData);

            } catch (IOException e) {

                e.printStackTrace();
                ErrorResponse errorResponse1 = new ErrorResponse();
                errorResponse1.setMessage("Oops something went wrong please try later!!!");
                serverListener.onError(requestID, errorResponse1);
            }

        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {

        dismissDialog();
        t.printStackTrace();
    }



    private void handleSuccessResponse(Object object) {

        serverListener.onSuccess(requestID, object);
        clearData();

    }

    private void handleErrorResponse(String data) {

        Logs.e(this, "data" + data);
        Gson gson = new Gson();
        //gson.serializeNulls();

        try {

            ErrorResponse errorResponse = gson.fromJson(data, ErrorResponse.class);

            Logs.e(this, "mes" + errorResponse.getMessage());
            serverListener.onError(requestID, errorResponse);
        } catch (JsonSyntaxException e) {

            e.printStackTrace();
        }

        clearData();


    }

    private void clearData() {

        headerMap = null;
        paramsMap = null;
        showProgressDialog = true;
    }







}
