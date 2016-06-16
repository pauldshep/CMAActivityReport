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
 * Encapsulates property file functions used to save data.
 * Created by Paul Shepherd on 6/7/2016.
 */
abstract class DataProperties
{
    private final static String TAG = "DataProperties";

    public Context    mDataContext;
    public Properties mProperties;
    public String     mPropFileName;


    /**
     * Default Constructor
     */
    public DataProperties(Context context, String propFileName)
    {
        mDataContext  = context;
        mProperties   = new Properties();
        mPropFileName = propFileName;
    }


    //**************************************************************************
    /**
     * Get report properties from the property file and set widget values.
     * Verify the properties file exists.  If it does not then create it.
     */
    public void getProperties()
    {
        Log.i(TAG, "getProperties()");
        File prop_file = new File(mDataContext.getFilesDir(), mPropFileName);

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
    }   // end


    //**************************************************************************
    /**
     * Sets specified property file value locally and in the properties file.
     * @param propKey properities key
     * @param propValue properities value
     */
    public void setProperty(String propKey, String propValue)
    {
        Log.i(TAG, "setProperty(key = " + propKey + ", value = " + propValue +")");
        File prop_file = new File(mDataContext.getFilesDir(), mPropFileName);

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


    /**
     * Creates default properties
     */
    abstract void createDefaultProperties();


    /**
     * Extracts property values
     */
    abstract void extractProperties();

}   // end public class DataProperties
