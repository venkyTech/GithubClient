package com.crashlytics.issuelist.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Venkat-Tesark on 03-08-2017.
 */

public class Utility {

    private static Utility utility;
    private static Context context;
    private Utility() {

    }

    public static Utility with(Context contex) {

        if (utility == null) {

            utility = new Utility();
        }

        context = contex;

        return utility;

    }

    public static Utility with() {

        if (utility == null) {

            utility = new Utility();
        }


        return utility;

    }

    public void showToast(String message) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public boolean isInternetConnected() {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isConnectedOrConnecting();

    }

    public boolean isValidEmail(String email) {

        if (email == null) {

            return false;
        } else {

            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public String priceFormat(String price) {

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        if (price == null) {

            return "0.00";
        } else {

            try {

                return String.format("%.2f", Double.parseDouble(price));

            } catch (NumberFormatException e) {

                e.printStackTrace();

                return "0.00";
            }


        }
    }
    public String priceFormat(double price) {

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        if (price == 0) {

            return "0.00";

        } else {

            try {

                return String.format("%.2f", price);

            } catch (NumberFormatException e) {

                e.printStackTrace();

                return "0.00";
            }


        }
    }

    public String getFormatedAmount(String amount){

        double amt = Double.parseDouble(amount);
        return NumberFormat.getNumberInstance(Locale.getDefault()).format((int)amt);
    }
    public String getFormatedAmount(double amount){

        try {
            return NumberFormat.getNumberInstance(Locale.getDefault()).format((int)amount);

        } catch (NumberFormatException e) {

            return "0";
        }


    }

    public String dateFormat(String date) {

        DateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        DateFormat newDateFormat = new SimpleDateFormat("MM-dd-yyyy");




        try {
            Date date1 = oldDateFormat.parse(date);

            return newDateFormat.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();

            return "";
        }
    }

   /* public String changeDateFormat(String date) {

        DateTimeFormatter formatter = DateTimeFormat.forPattern("")

        try {
            Date date1 = oldDateFormat.parse(date);

            return newDateFormat.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();

            return "";
        }
    }*/


    public static String getTodayDate() {

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        return formattedDate;

    }

    public static String getMonth() {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        return formattedDate;

    }

    public static String getLastDateOfMonth(String month) {


        try {

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            Calendar c = Calendar.getInstance();
            Date date = df.parse(month);
            c.setTime(date);
            c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));

            System.out.println("Current time => " + c.getTime());


            String formattedDate = df.format(c.getTime());

            return formattedDate;
        } catch (ParseException e) {
            e.printStackTrace();

            return "";
        }


    }

    public static String getYear() {

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        return formattedDate;

    }

    public static String getPreviousYear(String inputDate){
        //inputDate = "15-12-2015"; // for example
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            Date date = format.parse(inputDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            c.add(Calendar.YEAR, -1);
            inputDate = format.format(c.getTime());
            Log.d("asd", "selected date : "+inputDate);

            System.out.println(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            inputDate ="";
        }
        return inputDate;
    }

    public static String getNextYear(String inputDate){

        //inputDate = "15-12-2015"; // for example
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            Date date = format.parse(inputDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            c.add(Calendar.YEAR, +1);
            inputDate = format.format(c.getTime());
            Log.d("asd", "selected date : "+inputDate);

            System.out.println(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            inputDate ="";
        }
        return inputDate;
    }

    public static String getPreviousMonth(String inputDate){
        //inputDate = "15-12-2015"; // for example
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            Date date = format.parse(inputDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            c.add(Calendar.MONTH, -1);
            inputDate = format.format(c.getTime());
            Log.d("asd", "selected date : "+inputDate);

            System.out.println(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            inputDate ="";
        }
        return inputDate;
    }

    public static String getNextMonth(String inputDate){
        //inputDate = "15-12-2015"; // for example
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            Date date = format.parse(inputDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            c.add(Calendar.MONTH, +1);
            inputDate = format.format(c.getTime());
            Log.d("asd", "selected date : "+inputDate);

            System.out.println(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            inputDate ="";
        }
        return inputDate;
    }
    public static String getPreviousDate(String inputDate){
        //inputDate = "15-12-2015"; // for example
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            Date date = format.parse(inputDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            c.add(Calendar.DATE, -1);
            inputDate = format.format(c.getTime());
            Log.d("asd", "selected date : "+inputDate);

            System.out.println(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            inputDate ="";
        }
        return inputDate;
    }

    public static String getNextDate(String inputDate){
        //inputDate = "15-12-2015"; // for example
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            Date date = format.parse(inputDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            c.add(Calendar.DATE, +1);
            inputDate = format.format(c.getTime());
            Log.d("asd", "selected date : "+inputDate);

            System.out.println(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            inputDate ="";
        }
        return inputDate;
    }

    public static String getWeekDate(){

        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

        String inputDate = null;

        try {
            Calendar c = Calendar.getInstance();

            String todayDate = format.format(c.getTime());

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());


            String firstDayOfWeekDate = format.format(calendar.getTime());


            return firstDayOfWeekDate + " To " + todayDate;


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            inputDate ="";
        }
        return inputDate;
    }

    public static String getPreviousWeekDate(String currentWeekDate){

        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

        String weekDate = currentWeekDate.substring(0, 11);

        Log.e("date", "SubStringDate = " + weekDate);

        String inputDate = null;

        try {

            Date date = format.parse(weekDate);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -1);

            String todayDate =  format.format(calendar.getTime());

            calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());

            String firstDayOfWeekDate = format.format(calendar.getTime());


            return firstDayOfWeekDate + " To " + todayDate;


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            inputDate ="";
        }
        return inputDate;
    }

    public static String getNextWeekDate(String currentWeekDate){

        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

        String[] dateArray = currentWeekDate.split("To");
        Log.e("length", "size=" + dateArray.length + " " + dateArray);

        String weekDate = dateArray[1].trim();

        Log.e("date", "SubStringDate = " + weekDate);

        String inputDate = null;

        try {

            Date date = format.parse(weekDate);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, +1);

            String startDate =  format.format(calendar.getTime());

            calendar.add(Calendar.DATE, +6);


            String endDate =  format.format(calendar.getTime());


            return startDate + " To " + endDate;


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            inputDate ="";
        }
        return inputDate;
    }

    public static boolean compareStartNEndDate(String startDate, String endDate) {
        //inputDate = "15-12-2015"; // for example
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date fromDate = format.parse(startDate);
            Calendar startCalender = Calendar.getInstance();
            startCalender.setTime(fromDate);

            Date toDate = format.parse(endDate);
            Calendar endCalender = Calendar.getInstance();
            endCalender.setTime(toDate);

            if (startCalender.getTimeInMillis() <= endCalender.getTimeInMillis()) {

                return true;
            } else {

                return false;
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return false;
    }

    public static boolean nextDateVisibility(String date) {

        //inputDate = "15-12-2015"; // for example
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

        try {

            Calendar calender = Calendar.getInstance();
            Date today = calender.getTime();

            Date selectedate = format.parse(date);
            Calendar dateCalender = Calendar.getInstance();
            dateCalender.setTime(selectedate);

            if (calender.getTimeInMillis() <= dateCalender.getTimeInMillis()) {

                return false;
            } else {

                return true;
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return false;
    }


    public static String ChangeDateFormat(String date) {
        //inputDate = "15-12-2015"; // for example

        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date oldDate = format.parse(date);
            return newDateFormat.format(oldDate);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return "";
    }

    public static String ChangeDateFormat_DD_MM_YYYY_To_DD_MM_YYYY(String date) {
        //inputDate = "15-12-2015"; // for example

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date oldDate = format.parse(date);
            return newDateFormat.format(oldDate);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return "";
    }



}
