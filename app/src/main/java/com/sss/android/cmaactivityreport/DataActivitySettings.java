package com.sss.android.cmaactivityreport;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Encapsulates settings information that is saved in a properties file in the
 * Internal Storage area
 *
 * Created by Paul Shepherd on 5/31/2016.
 */
public class DataActivitySettings extends DataProperties
{
    private final static String TAG              = "DataActivitySettings";

    // settings data variables
    public String mEmailTo;
    public String mEmailAddr;
    public String mEmailFrom;
    public String mEmailSubject;

    // settings data keys for the properties file
    public final static String KEY_EMAIL_TO      = "EmailTo";
    public final static String KEY_EMAIL_ADDR    = "EmailAddr";
    public final static String KEY_EMAIL_FROM    = "EmailFrom";
    public final static String KEY_EMAIL_SUBJECT = "EmailSubject";


    //**************************************************************************
    /**
     * Default Constructor
     * @param context of the current activity
     */
    public DataActivitySettings(Context context)
    {
        super(context);
        getProperties();
    }   // end public DataActivitySettings


    //**************************************************************************
    /**
     * Local implementation of the toString() function
     */
    public String toString()
    {
        String ret_str = "to = "     + mEmailTo   +
                         ", addr = " + mEmailAddr +
                         ", from = " + mEmailFrom +
                         ", sub = "  + mEmailSubject;
        return ret_str;
    }


    ///////////////////////////////////////////////////////////////////////////
    //////////////////////// PRIVATE MEMBER FUNCTIONS /////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Creates default properties file and populates settings data with
     * default value
     */
    protected void createDefaultProperties()
    {
        // defalut properties values
        mEmailTo      = "Paul Shepherd";
        mEmailAddr    = "pauldshep@gmail.com";
        mEmailFrom    = "CMA User";
        mEmailSubject = "CMA Activity Report";

        // create the properties data structure
        mProperties.put(KEY_EMAIL_TO,      mEmailTo);
        mProperties.put(KEY_EMAIL_ADDR,    mEmailAddr);
        mProperties.put(KEY_EMAIL_FROM,    mEmailFrom);
        mProperties.put(KEY_EMAIL_SUBJECT, mEmailSubject);
    }


    /**
     * Extracts the properties from the properties file
     */
    protected void getProperties()
    {
        mEmailTo      = mProperties.getProperty(KEY_EMAIL_TO);
        mEmailAddr    = mProperties.getProperty(KEY_EMAIL_ADDR);
        mEmailFrom    = mProperties.getProperty(KEY_EMAIL_FROM);
        mEmailSubject = mProperties.getProperty(KEY_EMAIL_SUBJECT);

        Log.i(TAG, "settings from properties: " + toString());
    }

}   // end public class DataActivitySettings
