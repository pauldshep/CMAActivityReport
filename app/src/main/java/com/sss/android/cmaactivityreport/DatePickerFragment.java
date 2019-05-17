package com.sss.android.cmaactivityreport;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Displays a date picker in a dialog fragment.
 * Reference: https://stackoverflow.com/questions/27225815/android-how-to-show-datepicker-in-fragment
 * Reference: https://android--code.blogspot.com/2015/08/android-datepickerdialog-example.html
 *
 * Created by Paul Shepherd on 5/25/2016.
 */
public class DatePickerFragment extends DialogFragment
        implements android.app.DatePickerDialog.OnDateSetListener
{
    private final static String TAG = "DatePickerFragment";

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

        Log.d(TAG, "onCreateDialog(): " + toString());

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

        TextView tv_event_date = getActivity().findViewById(R.id.textViewEventDate);
        tv_event_date.setText(toString());
        Log.i(TAG, "onDateSet(): " + toString());
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

