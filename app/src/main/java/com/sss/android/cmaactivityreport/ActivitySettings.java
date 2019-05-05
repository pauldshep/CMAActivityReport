package com.sss.android.cmaactivityreport;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivitySettings extends AppCompatActivity
{
    private final static String TAG = "ActivitySettings";

    private EditText mEditTextEmailTo;
    private EditText mEditTextEmailAddr;
    private EditText mEditTextEmailFrom;
    private EditText mEditTextEmailSubject;

    private Context         mAppContext;
    private DataCMAActivity mDataCMAActivity;


    /**
     * onCreate(): Android life cycle function called when this activity is
     * started.  Initialize and display application settings.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Activity Report Settings");

        // set dialog values
        mEditTextEmailTo      = findViewById(R.id.editText_email_to);
        mEditTextEmailAddr    = findViewById(R.id.editText_email_addr);
        mEditTextEmailFrom    = findViewById(R.id.editText_email_from);
        mEditTextEmailSubject = findViewById(R.id.editText_email_subject);

        // get current settings from dataCMAActivity
        mAppContext           = getApplicationContext();
        mDataCMAActivity      = new DataCMAActivity(mAppContext);

        mEditTextEmailTo.setText(mDataCMAActivity.mEmailTo);
        mEditTextEmailAddr.setText(mDataCMAActivity.mEmailAddr);
        mEditTextEmailFrom.setText(mDataCMAActivity.mEmailFrom);
        mEditTextEmailSubject.setText(mDataCMAActivity.mEmailSubject);

        Log.i(TAG, "onCreate(): " + mDataCMAActivity.toStringEmail());
    }


    /**
     * Creates the action bar menu items
     * @param menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting_actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    /**
     * Handle action bar menu item selection
     * @param item
     * @return always true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch(item.getItemId())
        {
            case R.id.action_bar_settings_reset:
                // reset settings
                break;

            case R.id.action_bar_settings_help:
                // display the help dialog
                break;

            default:
                Log.e("ActivitySettings",
                        "action bar menu item not handled = " + item.getItemId());
                break;
        }

        return true;
    }   // end public boolean onOptionsItemSelected(MenuItem item)


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
        mDataCMAActivity.mEmailInit    = mAppContext.getString(R.string.boolean_true);
        mDataCMAActivity.mEmailTo      = mEditTextEmailTo.getText().toString();
        mDataCMAActivity.mEmailAddr    = mEditTextEmailAddr.getText().toString();
        mDataCMAActivity.mEmailFrom    = mEditTextEmailFrom.getText().toString();
        mDataCMAActivity.mEmailSubject = mEditTextEmailSubject.getText().toString();

        Toast.makeText(this, "Using New Application Settings",
                Toast.LENGTH_SHORT).show();

        mDataCMAActivity.saveEmailProperties();

        Log.i(TAG, "onClickSettingsButtonUse(): " + mDataCMAActivity.toStringEmail());

        // go back to the main activity
        finish();
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
    }




}   // end public class ActivitySettings extends AppCompatActivity


