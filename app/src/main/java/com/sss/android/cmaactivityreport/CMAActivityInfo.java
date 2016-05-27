package com.sss.android.cmaactivityreport;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Paul Shepherd on 5/25/2016.
 */
public class CMAActivityInfo
{
    public String eventName;
    public String eventType;
    public String eventDate;
    public int    numAttend;
    public int    numSalvations;
    public int    numRededications;
    public int    numOther;

    private AppCompatActivity appCompatActivity;


    /**
     * constructor, sets default values
     */
    CMAActivityInfo(AppCompatActivity appCompatActivity)
    {
        this.appCompatActivity  = appCompatActivity;

        eventName               = "CMA Event Name";
        eventType               = "CMA Event Type";
        eventDate               = "CMA Event Date";
        numAttend               = 0;
        numSalvations           = 0;
        numRededications        = 0;
        numOther                = 0;
    }   // end CMAActivityInfo()


    /**
     * get CMA activity report information from application GUI elements and
     * save them to the associated class variables
     */
    public void getInfo()
    {
        // event name from edit text
        EditText edittext_name  = (EditText)appCompatActivity.findViewById(R.id.editTextName);
        eventName               = edittext_name.getText().toString();

        // event type from edit text
        TextView edittext_type  = (TextView)appCompatActivity.findViewById(R.id.textViewType);
        eventType               = edittext_type.getText().toString();

        // event date from edit text
        TextView edittext_date  = (TextView)appCompatActivity.findViewById(R.id.textViewDate);
        eventType               = edittext_date.getText().toString();

        //----------------------------------------------------------------------
        // these widgets should always return an integer
        //----------------------------------------------------------------------
        // number attending event from edit text
        try
        {
            EditText edittext_attend = (EditText)appCompatActivity.findViewById(R.id.editTextAttend);
            numAttend = Integer.parseInt(edittext_attend.getText().toString());
        }
        catch(NumberFormatException nfe)
        {
            numAttend = 0;
        }

        // number of salvations at the event from edit text
        try
        {
            EditText edittext_salvations = (EditText)appCompatActivity.findViewById(R.id.editTextSalvations);
            numSalvations = Integer.parseInt(edittext_salvations.getText().toString());
        }
        catch(NumberFormatException nfe)
        {
            numSalvations = 0;
        }

        // number of rededications at the event from edit text
        try
        {
            EditText edittext_rededications = (EditText)appCompatActivity.findViewById(R.id.editTextRededications);
            numRededications = Integer.parseInt(edittext_rededications.getText().toString());
        }
        catch(NumberFormatException nfe)
        {
            numRededications = 0;
        }

        // number of other ministry events at the event from edit text
        try
        {
            EditText edittext_other = (EditText)appCompatActivity.findViewById(R.id.editTextOther);
            numOther = Integer.parseInt(edittext_other.getText().toString());
        }
        catch(NumberFormatException nfe)
        {
            numOther = 0;
        }
    }


    /**
     * Local implementation of the toString() method.
     *
     * @return class data description and associated values
     */
    @Override
    public String toString()
    {
        String ret = "CMAActivityInfo" +
                ": name = "           + eventName         +
                ", type = "           + eventType         +
                ", date = "           + eventDate         +
                ", attend = "         + numAttend         +
                ", salvations = "     + numSalvations     +
                ", rededications = "  + numRededications  +
                ", other = "          + numOther;

        return ret;
    }


}   // end class CMAActivityInfo
