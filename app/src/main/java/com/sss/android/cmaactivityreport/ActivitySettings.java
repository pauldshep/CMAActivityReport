package com.sss.android.cmaactivityreport;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivitySettings extends AppCompatActivity
{
    EditText mEditTextEmailTo;
    EditText mEditTextEmailAddr;
    EditText mEditTextEmailFrom;
    EditText mEditTextEmailSubject;

    private final static String TAG = "ActivitySettings";

    /**
     * Called when this activity is started
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Activity Report Settings");

        // get setting from property file
        DataSettings data_settings = new DataSettings(this);
        data_settings.getProperties();
        Log.i(TAG, "settings: " + data_settings.toString());

        // set dialog values
        mEditTextEmailTo      = (EditText)findViewById(R.id.editText_email_to);
        mEditTextEmailAddr    = (EditText)findViewById(R.id.editText_email_addr);
        mEditTextEmailFrom    = (EditText)findViewById(R.id.editText_email_from);
        mEditTextEmailSubject = (EditText)findViewById(R.id.editText_email_subject);

        mEditTextEmailTo.setText(data_settings.mEmailTo);
        mEditTextEmailAddr.setText(data_settings.mEmailAddr);
        mEditTextEmailFrom.setText(data_settings.mEmailFrom);
        mEditTextEmailSubject.setText(data_settings.mEmailSubject);
    }


    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////// MESSAGE HANDLERS //////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    //**************************************************************************
    /**
     * Message handler for the settings activity USE button
     * @param view
     */
    public void onClickSettingsButtonUse(View view)
    {
        Toast.makeText(this, "Using New Application Settings",
                Toast.LENGTH_SHORT).show();

        // get current settings properties
        Context      context       = view.getContext();
        DataSettings data_settings = new DataSettings(context);

        data_settings.setProperty(DataSettings.mKeyEmailTo,      mEditTextEmailTo.getText().toString());
        data_settings.setProperty(DataSettings.mKeyEmailAddr,    mEditTextEmailAddr.getText().toString());
        data_settings.setProperty(DataSettings.mKeyEmailFrom,    mEditTextEmailFrom.getText().toString());
        data_settings.setProperty(DataSettings.mKeyEmailSubject, mEditTextEmailSubject.getText().toString());

        // go back to the main activity
        finish();

        //Intent intent = new Intent(getApplicationContext(), ActivityMain.class);
        //startActivity(intent);
    }


    //**************************************************************************
    /**
     * Message handler for the settings activity ABORT button.  This goes back
     * to the main activity without making any changes to the settings.
     * @param view
     */
    public void onClickSettingsButtonAbort(View view)
    {
        Toast.makeText(this, "Aborting Application Setting Edit",
                Toast.LENGTH_SHORT).show();

        // go back to the previous activity
        finish();

        //Intent intent = new Intent(getApplicationContext(), ActivityMain.class);
        //startActivity(intent);
    }




}   // end public class ActivitySettings extends AppCompatActivity


