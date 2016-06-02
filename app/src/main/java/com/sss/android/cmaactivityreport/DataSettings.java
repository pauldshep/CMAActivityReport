package com.sss.android.cmaactivityreport;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.EditText;

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
public class DataSettings
{
    // settings data variables
    public String mEmailTo;
    public String mEmailAddr;
    public String mEmailFrom;
    public String mEmailSubject;

    // settings data keys for the properties file
    public final static String mKeyEmailTo      = "EmailTo";
    public final static String mKeyEmailAddr    = "EmailAddr";
    public final static String mKeyEmailFrom    = "EmailFrom";
    public final static String mKeyEmailSubject = "EmailSubject";

    private final String     TAG       = "DataSettings";
    private final String     mPropFile = "Setting.properties";
    private       Properties mProperties;
    private       Context    mDataContext;


    //**************************************************************************
    /**
     * Default Constructor
     * @param context of the current activity
     */
    public DataSettings(Context context)
    {
        mDataContext = context;
        mProperties  = new Properties();
    }   // end public DataSettings


    //**************************************************************************
    /**
     * Get setting properties from property file and set widget values.  Verify
     * the properties file exists.  If it does not then create it
     */
    public void getProperties()
    {
        Log.i(TAG, "getProperties()");
        File prop_file = new File(mDataContext.getFilesDir(), mPropFile);

        if(!prop_file.exists())
        {
            // here the property file does not exits, create default
            Log.i(TAG, "getProperties() - property file does not exist");
            try
            {
                prop_file.createNewFile();
                createDefaultProperties();
                OutputStream out = new FileOutputStream(prop_file);
                mProperties.store(out, "");
            }
            catch(IOException ioe)
            {
                Log.e(TAG, "could not create properties file: " + ioe.toString());
            }
        }
        else
        {
            // here the properties file exits, read it
            Log.i(TAG, "getProperties() - property file found");
            try
            {
                InputStream in = new FileInputStream(prop_file);
                mProperties.load(in);
                extractProperties();
            }
            catch(FileNotFoundException fnf)
            {
                Log.e(TAG, "could not find properties file: " + fnf.toString());
            }
            catch(IOException ioe)
            {
                Log.e(TAG, "could not read properties file" + ioe.toString());
            }
        }

        Log.i(TAG, "settings = " + toString());
    }   // end private void getProperties()


    //**************************************************************************
    /**
     * Sets specified property file value locally and in the properties file.
     * @param propKey
     * @param propValue
     */
    public void setProperty(String propKey, String propValue)
    {
        Log.i(TAG, "setProperty(key = " + propKey + ", value = " + propValue +")");
        File prop_file = new File(mDataContext.getFilesDir(), mPropFile);

        try
        {
            mProperties.setProperty(propKey, propValue);
            OutputStream out = new FileOutputStream(prop_file);
            mProperties.store(out, "");
        }
        catch(IOException ioe)
        {
            Log.e(TAG, "could not save property to file: " + ioe.toString());
        }
    }


    //**************************************************************************
    /**
     * Local implementation of the toString() function
     */
    public String toString()
    {
        String ret_str = "to = "     + mEmailTo   + ", addr = " + mEmailAddr +
                         ", from = " + mEmailFrom + ", sub = "  + mEmailSubject;
        return ret_str;
    }


    ///////////////////////////////////////////////////////////////////////////
    //////////////////////// PRIVATE MEMBER FUNCTIONS /////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Creates default properties file and populates settings data with
     * default value
     */
    private void createDefaultProperties()
    {
        // defalut properties values
        mEmailTo      = "Paul Shepherd";
        mEmailAddr    = "pauldshep@gmail.com";
        mEmailFrom    = "CMA User";
        mEmailSubject = "CMA Activity Report";

        // create the properties data structure
        mProperties.put(mKeyEmailTo,      mEmailTo);
        mProperties.put(mKeyEmailAddr,    mEmailAddr);
        mProperties.put(mKeyEmailFrom,    mEmailFrom);
        mProperties.put(mKeyEmailSubject, mEmailSubject);
    }


    /**
     * Extracts the properties from the properties file
     */
    private void extractProperties()
    {
        mEmailTo      = mProperties.getProperty(mKeyEmailTo);
        mEmailAddr    = mProperties.getProperty(mKeyEmailAddr);
        mEmailFrom    = mProperties.getProperty(mKeyEmailFrom);
        mEmailSubject = mProperties.getProperty(mKeyEmailSubject);

        Log.i(TAG, "settings from properties: " + toString());
    }

}   // end public class DataSettings
