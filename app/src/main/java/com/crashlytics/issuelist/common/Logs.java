package com.crashlytics.issuelist.common;

import android.util.Log;

/**
 * Created by Venkat-Tesark on 02-02-2017.
 */

public class Logs {


    public static void d(Object object, String data){
        if (Constants.SHOW_LOG && data != null) {

            Log.d(object.getClass()+"", data);
        }

    }

    public static void v(Object object, String data){
        if (Constants.SHOW_LOG && data != null) {

            Log.v(object.getClass()+"", data);
        }

    }

    public static void i(Object object, String data){
        if (Constants.SHOW_LOG && data != null) {

            Log.i(object.getClass()+"", data);
        }

    }

    public static void e(Object object, String data){
        if (Constants.SHOW_LOG && data != null) {

            Log.e(object.getClass()+"", data);
        }

    }
}
