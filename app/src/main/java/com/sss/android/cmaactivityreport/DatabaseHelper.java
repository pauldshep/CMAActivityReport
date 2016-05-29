package com.sss.android.cmaactivityreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileNotFoundException;

/**
 * CMA Activity Report Database Helper Class.
 *
 * Created by Paul Shepherd on 5/28/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final int    DATABASE_VERSION    = 1;
    public static final String DATABASE_NAME       = "CMAActivityReport.db";
    public static final String TABLE_NAME_SETTINGS = "AcitivitySettings";

    // settings database column names
    public static final String KEY_ID            = "db_id";
    public static final String KEY_EMAIL_TO      = "email_to";
    public static final String KEY_EMAIL_ADDR    = "email_addr";
    public static final String KEY_EMAIL_FROM    = "email_from";
    public static final String KEY_EMAIL_SUBJECT = "email_subject";

    private SQLiteDatabase databaseActivity;

    private final static String TAG = "DatabaseHelper";

    //**************************************************************************
    /**
     * Class default constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //**************************************************************************
    /**
     * Called the first time the database is created.
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.i(TAG, "onCreate()");

        String CREATE_SETTINGS_TABLE = "CREATE TABLE " + TABLE_NAME_SETTINGS +
                "(" +
                KEY_ID              + " INTEGER PRIMARY KEY, " +
                KEY_EMAIL_TO        + " TEXT, " +
                KEY_EMAIL_ADDR      + " TEXT, " +
                KEY_EMAIL_FROM      + " TEXT, " +
                KEY_EMAIL_SUBJECT   + " TEXT"   +
                ")";

        try
        {
            db.execSQL(CREATE_SETTINGS_TABLE);
        }
        catch(SQLException sqle)
        {
            Log.e(TAG, "could not create database table: " +
                    CREATE_SETTINGS_TABLE);
            return;
        }

        Log.i(TAG, "onCreate()" + CREATE_SETTINGS_TABLE);
    }


    //**************************************************************************
    /**
     * Called if the version number in this class is higher than the version
     * of the database just opened.
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SETTINGS);

        // Creating table again
        onCreate(db);
    }


    //**************************************************************************
    /**
     *
     */
    public void addActivitySettings(DataActivity dataActivity)
    {
        SQLiteDatabase db     = this.getWritableDatabase();
        ContentValues  values = new ContentValues();

        // initialize row values
        values.put(KEY_EMAIL_TO,        dataActivity.settingsEmailTo);
        values.put(KEY_EMAIL_ADDR,      dataActivity.settingsEmailAddress);
        values.put(KEY_EMAIL_FROM,      dataActivity.settingsEmailFrom);
        values.put(KEY_EMAIL_SUBJECT,   dataActivity.settingsEmailSubject);


        // Inserting Row
        db.insert(TABLE_NAME_SETTINGS, null, values);
        db.close(); // Closing database connection
    }   // end public void addActivity(DataActivity dataActivity)


    //**************************************************************************
    /**
     * Read single activity entry
     */
    public DataActivity getActivitySettings(int id)
    {
        SQLiteDatabase db     = this.getReadableDatabase();
        Cursor         cursor = db.query(TABLE_NAME_SETTINGS,
                                new String[] {KEY_ID, KEY_EMAIL_TO, KEY_EMAIL_ADDR, KEY_EMAIL_FROM, KEY_EMAIL_SUBJECT},
                                KEY_ID + "=?",
                                new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
        {
            cursor.moveToFirst();
        }

        DataActivity data_activity = new DataActivity(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));

        // return shop
        return data_activity;
    }


}   // end public class DatabaseHelper extends SQLiteOpenHelper
