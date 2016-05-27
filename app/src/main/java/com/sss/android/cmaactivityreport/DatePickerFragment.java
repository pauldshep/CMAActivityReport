package com.sss.android.cmaactivityreport;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Displays a date picker in a dialog fragment
 *
 * Created by Paul Shepherd on 5/25/2016.
 */
public class DatePickerFragment extends DialogFragment
        implements android.app.DatePickerDialog.OnDateSetListener
{
    // class data: user specified day, month, and year
    public int mDay;
    public int mMonth;
    public int mYear;

    /**
     * Called when the dialog is created
     * @param savedInstanceState
     * @return created instance of Date Picker Dialog
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Bundle date_info = getArguments();
        mDay   = date_info.getInt(UtilDate.UTIL_DATE_DAY);
        mMonth = date_info.getInt(UtilDate.UTIL_DATE_MONTH);
        mYear  = date_info.getInt(UtilDate.UTIL_DATE_YEAR);

        Log.d("DatePickerFragment", toString());

        // Create a new instance of DatePickerFragment and return it
        return new android.app.DatePickerDialog(getActivity(), this, mYear, mMonth, mDay);
    }


    /**
     * This function is called when the date picker OK button is selected. It
     * is not called if CANCEL is selected and the specified date will remain
     * the current date.
     *
     * @param view of the date picker dialog fragment
     * @param year user specified year
     * @param month user specified month
     * @param day user specified day
     */
    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        mDay   = day;
        mMonth = month;
        mYear  = year;

        Log.i("DatePickerFragment", "onDateSet: " + toString());
    }

    /**
     * Implement the class toString method to create a display for the current
     * specified date
     */
    @Override
    public String toString()
    {
        Integer month = mMonth + 1;
        return month + "-" + mDay + "-" + mYear;
    }
}

