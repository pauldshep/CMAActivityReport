package com.sss.android.cmaactivityreport;

import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;

/**
 * Encapsulates CMA activity report date functions
 *
 * Created by Paul Shepherd on 5/26/2016.
 */
public class UtilDate
{
    // class data
    public int mDay;
    public int mMonth;
    public int mYear;

    // class constants
    public final static String UTIL_DATE_DAY   = "util_date_day";
    public final static String UTIL_DATE_MONTH = "util_date_month";
    public final static String UTIL_DATE_YEAR  = "util_date_year";


    /**
     * Default constructor initializes date data to current date
     */
    public UtilDate()
    {
        final Calendar c = Calendar.getInstance();
        mDay             = c.get(Calendar.DAY_OF_MONTH);
        mMonth           = c.get(Calendar.MONTH);
        mYear            = c.get(Calendar.YEAR);

        Log.d("UtilDate", toString());
    }

    /**
     * Initialize date data with specified values
     */
    public UtilDate(int day, int month, int year)
    {
        setDate(day, month, year);
    }


    /**
     * initialize date parameters to specified
     *
     * @param day
     * @param month
     * @param year
     */
    public void setDate(int day, int month, int year)
    {
        mDay   = day;
        mMonth = month;
        mYear  = year;
    }


    /**
     * Set date with data from Bundle
     *
     * @param dateInfo
     */
    public void setDate(Bundle dateInfo)
    {
        mDay   = dateInfo.getInt(UTIL_DATE_DAY);
        mMonth = dateInfo.getInt(UTIL_DATE_MONTH);
        mYear  = dateInfo.getInt(UTIL_DATE_YEAR);
    }


    /**
     * Build and return a Bundle containing date information
     *
     * @return Bundle with date information
     */
    public Bundle buildBundle()
    {
        Bundle loc_bundle = new Bundle();

        loc_bundle.putInt(UTIL_DATE_DAY,   mDay);
        loc_bundle.putInt(UTIL_DATE_MONTH, mMonth);
        loc_bundle.putInt(UTIL_DATE_YEAR,  mYear);

        return loc_bundle;
    }



    /**
     * implements the toString function for this class
     */
    public String toString()
    {
        int month = mMonth + 1;
        return month + "-" + mDay + "-" + mYear;
    }
}   // end class UtilDate
