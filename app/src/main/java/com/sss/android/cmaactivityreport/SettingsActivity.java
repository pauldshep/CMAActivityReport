package com.sss.android.cmaactivityreport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////// MESSAGE HANDLERS //////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Message handler for the settings activity USE button
     * @param view
     */
    public void onClickSettingsButtonUse(View view)
    {
        Toast.makeText(this, "Using New Application Settings",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


    /**
     * Message handler for the settings activity ABORT button
     * @param view
     */
    public void onClickSettingsButtonAbort(View view)
    {
        Toast.makeText(this, "Aborting Application Setting Edit",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}   // end public class SettingsActivity extends AppCompatActivity
