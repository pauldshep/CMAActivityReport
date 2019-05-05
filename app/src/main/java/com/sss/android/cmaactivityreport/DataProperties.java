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
    private final static String TAG            = "DataProperties";
    private final static String PROP_FILE_NAME = "CMA_Report.properties";

    public Context    mDataContext;
    public Properties mProperties;

    private File mPropFile;


    /**
     * Default Constructor
     */
    public DataProperties(Context context)
    {
        mDataContext  = context;
        mProperties   = new Properties();
        mPropFile     = getPropertyFile();

        getPropertiesFromFile();
    }


    /**
     * Get report properties from the property file and set widget values.
     * Verify the properties file exists.  If it does not then create it.
     */
    public void getPropertiesFromFile()
    {
        Log.i(TAG, "getProperties(): file = " + PROP_FILE_NAME);

        try
        {
            // read properties from file
            InputStream in = new FileInputStream(mPropFile);
            mProperties.load(in);
            getProperties();
        }
        catch(FileNotFoundException fnf)
        {
            Log.e(TAG, "could not find properties file: " + fnf.toString());
        }
        catch(IOException ioe)
        {
            Log.e(TAG, "could not read properties file" + ioe.toString());
        }

        Log.i(TAG, "getProperties(): settings = " + toString());
    }   // end


    /**
     * Sets specified property file value locally.  This value must be written
     * to the properties file with saveProperties
     *
     * @param propKey property key
     * @param propValue property value
     */
    public void setProperty(String propKey, String propValue)
    {
        Log.i(TAG, "setProperty(key = " + propKey + ", value = " + propValue +")");
        mProperties.setProperty(propKey, propValue);
     }


    /**
     * Saves properties to property file
     */
    public void saveProperties()
    {
        Log.i(TAG, "saveProperties(): saving properties to file: " +
                mPropFile.getName());
        try
        {
            OutputStream out_str = new FileOutputStream(mPropFile);
            mProperties.store(out_str, "");
        }
        catch(IOException ioe)
        {
            Log.e(TAG, "ERROR - could not save properties to file: " +
                    ioe.toString());
        }
    }


    /**
     * Creates default properties
     */
    abstract void createDefaultProperties();


    /**
     * Extracts property values
     */
    abstract void getProperties();


    ////////////////////////////////////////////////////////////////////////////
    /////////////////////////// PRIVATE MEMBER FUNCTIONS ///////////////////////
    ////////////////////////////////////////////////////////////////////////////
    private File getPropertyFile()
    {
        Log.i(TAG, "getPropertyFile(): file = " + PROP_FILE_NAME);

        File prop_file = new File(mDataContext.getFilesDir(), PROP_FILE_NAME);

        if(!prop_file.exists())
        {
            // here the property file does not exist, create it and initialize
            Log.i(TAG, "getPropertyFile(): property file does not exist, creating");

            try
            {
                prop_file.createNewFile();
                createDefaultProperties();
                OutputStream out = new FileOutputStream(mPropFile);
                mProperties.store(out, "");
            }
            catch(IOException ioe)
            {
                Log.e(TAG, "ERROR - could not create property file: " + PROP_FILE_NAME);
            }
        }

        return prop_file;
    }

}   // end public class DataProperties
