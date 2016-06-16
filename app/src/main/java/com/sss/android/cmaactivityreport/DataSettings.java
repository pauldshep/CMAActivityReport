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
public class DataSettings extends DataProperties
{
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

    private final static String TAG              = "DataSettings";
    private final static String PROP_FILE_NAME   = "Setting.properties";


    //**************************************************************************
    /**
     * Default Constructor
     * @param context of the current activity
     */
    public DataSettings(Context context)
    {
        super(context, PROP_FILE_NAME);
        getProperties();
    }   // end public DataSettings


//    //**************************************************************************
//    /**
//     * Get setting properties from property file and set widget values.  Verify
//     * the properties file exists.  If it does not then create it
//     */
//    public void getProperties()
//    {
//        Log.i(TAG, "getProperties()");
//        File prop_file = new File(mDataContext.getFilesDir(), PROP_FILE);
//
//        if(!prop_file.exists())
//        {
//            // here the property file does not exits, create default
//            Log.i(TAG, "getProperties() - property file does not exist");
//            try
//            {
//                prop_file.createNewFile();
//                createDefaultProperties();
//                OutputStream out = new FileOutputStream(prop_file);
//                mProperties.store(out, "");
//            }
//            catch(IOException ioe)
//            {
//                Log.e(TAG, "could not create properties file: " + ioe.toString());
//            }
//        }
//        else
//        {
//            // here the properties file exits, read it
//            Log.i(TAG, "getProperties() - property file found");
//            try
//            {
//                InputStream in = new FileInputStream(prop_file);
//                mProperties.load(in);
//                extractProperties();
//            }
//            catch(FileNotFoundException fnf)
//            {
//                Log.e(TAG, "could not find properties file: " + fnf.toString());
//            }
//            catch(IOException ioe)
//            {
//                Log.e(TAG, "could not read properties file" + ioe.toString());
//            }
//        }
//
//        Log.i(TAG, "settings = " + toString());
//    }   // end private void getProperties()


//    //**************************************************************************
//    /**
//     * Sets specified property file value locally and in the properties file.
//     * @param propKey
//     * @param propValue
//     */
//    public void setProperty(String propKey, String propValue)
//    {
//        Log.i(TAG, "setProperty(key = " + propKey + ", value = " + propValue +")");
//        File prop_file = new File(mDataContext.getFilesDir(), PROP_FILE);
//
//        try
//        {
//            mProperties.setProperty(propKey, propValue);
//            OutputStream out = new FileOutputStream(prop_file);
//            mProperties.store(out, "");
//        }
//        catch(IOException ioe)
//        {
//            Log.e(TAG, "could not save property to file: " + ioe.toString());
//        }
//    }


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
    protected void extractProperties()
    {
        mEmailTo      = mProperties.getProperty(KEY_EMAIL_TO);
        mEmailAddr    = mProperties.getProperty(KEY_EMAIL_ADDR);
        mEmailFrom    = mProperties.getProperty(KEY_EMAIL_FROM);
        mEmailSubject = mProperties.getProperty(KEY_EMAIL_SUBJECT);

        Log.i(TAG, "settings from properties: " + toString());
    }

}   // end public class DataSettings
