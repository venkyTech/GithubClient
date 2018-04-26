package com.crashlytics.issuelist.models;

import java.io.Serializable;

/**
 * Created by Venkat-Tesark on 05-02-2018.
 */

public class ErrorResponse implements Serializable {


    /**
     * status : false
     * message : Please check your credentials
     */

    private boolean status;
    private String message = "";

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
