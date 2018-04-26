package com.crashlytics.issuelist.services;

/**
 * Created by Venkat-Tesark on 03-08-2017.
 */

public interface ServerListener {

    public void onSuccess(RequestID requestID, Object result);

    public void onError(RequestID requestID, Object result);

}
