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
import android.widget.Toast;

public class ActivitySettings extends AppCompatActivity
{
    private Context        mContext;
    private SQLiteDatabase mDatabase;

    private final static String TAG = "ActivitySettings";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Activity Report Settings");
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

        // create database entry
        createDatabaseEntry();
        getDatabaseEntry();

        // go back to the main activity
        Intent intent = new Intent(getApplicationContext(), ActivityMain.class);
        startActivity(intent);
    }


    //**************************************************************************
    /**
     * Message handler for the settings activity ABORT button
     * @param view
     */
    public void onClickSettingsButtonAbort(View view)
    {
        Toast.makeText(this, "Aborting Application Setting Edit",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), ActivityMain.class);
        startActivity(intent);
    }


    /**
     * Create settings database entry
     */
    private void createDatabaseEntry()
    {
        Log.i(TAG, "createDatabaseEntry()");

        // get data from the dialog
        DataSettings data_settings = new DataSettings();
        ContentValues values       = data_settings.buildContentValues();

        // create database entry
        mContext  = getApplicationContext().getApplicationContext();
        mDatabase = new DatabaseHelper(mContext).getWritableDatabase();
        mDatabase.insert(DatabaseHelper.TABLE_NAME_SETTINGS, null, values);

        Log.i(TAG, "createDatabaseEntry(): " + data_settings.toString());
    }


    /**
     * Get settings database entry
     */
    private void getDatabaseEntry()
    {
        Log.i(TAG, "getDatabaseEntry()");

        String   whereClause = null;
        String[] whereArgs   = null;

        Cursor cursor = mDatabase.query(
                DatabaseHelper.TABLE_NAME_SETTINGS,     // db table to query
                null,               // columns - null selects all columns
                whereClause,        // selection - null selects all rows
                whereArgs,          // selection arguments
                null,               // group by
                null,               // having
                null);              // order by

        cursor.moveToFirst();

        String email_to      = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_EMAIL_TO));
        String email_addr    = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_EMAIL_ADDR));
        String email_from    = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_EMAIL_FROM));
        String email_subject = cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_EMAIL_SUBJECT));

        Log.i(TAG, "getDatabaseEntry(): to = " + email_to + ", addr = " + email_addr +
                ", from = " + email_from + ", sub = " + email_subject);

        cursor.close();
    }


    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////// PRIVATE INTERNAL CLASSES /////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    private class DataSettings
    {
        public int    mSettingsEmailId;
        public String mSettingsEmailTo;
        public String mSettingsEmailAddress;
        public String mSettingsEmailFrom;
        public String mSettingsEmailSubject;

        /**
         * Default Constructor
         */
        public DataSettings()
        {
            // get data from the dialog elements
            EditText email_to       = (EditText)findViewById(R.id.editText_email_to);
            EditText email_addr     = (EditText)findViewById(R.id.editText_email_addr);
            EditText email_from     = (EditText)findViewById(R.id.editText_email_from);
            EditText email_subject  = (EditText)findViewById(R.id.editText_email_subject);

            mSettingsEmailId        = DatabaseHelper.DATABASE_VERSION;
            mSettingsEmailTo        = email_to.getText().toString();
            mSettingsEmailAddress   = email_addr.getText().toString();
            mSettingsEmailFrom      = email_from.getText().toString();
            mSettingsEmailSubject   = email_subject.getText().toString();
        }


        /**
         * Builds a ContentValues class to send to the database
         */
        public ContentValues buildContentValues()
        {
            ContentValues values = new ContentValues();

            values.put(DatabaseHelper.KEY_ID,            mSettingsEmailId);
            values.put(DatabaseHelper.KEY_EMAIL_TO,      mSettingsEmailTo);
            values.put(DatabaseHelper.KEY_EMAIL_ADDR,    mSettingsEmailAddress);
            values.put(DatabaseHelper.KEY_EMAIL_FROM,    mSettingsEmailFrom);
            values.put(DatabaseHelper.KEY_EMAIL_SUBJECT, mSettingsEmailSubject);

            return values;
        }


        /**
         * Implements a local toString()
         */
        @Override
        public String toString()
        {
          String ret_str =  "id = "         + mSettingsEmailId      +
                            ", to = "       + mSettingsEmailTo      +
                            ", addr = "     + mSettingsEmailAddress +
                            ", from = "     + mSettingsEmailFrom    +
                            ", subject = "  + mSettingsEmailSubject;

            return ret_str;
        }
    }   // end private class DataSettings
}   // end public class ActivitySettings extends AppCompatActivity
