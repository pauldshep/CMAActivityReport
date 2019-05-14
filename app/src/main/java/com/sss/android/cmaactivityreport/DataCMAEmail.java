package com.sss.android.cmaactivityreport;

import android.content.Context;
import android.util.Log;

/**
 * Encapsulates the main application email data.  This information is saved
 * to a local properties file and is used to generate the CMA Activity Report
 *
 * Created by Paul Shepherd on 5/12/2019.
 */
public class DataCMAEmail extends DataProperties
{
    private final static String TAG                = "DataCMAActivity";
    //private final static String PROP_FILE_NAME     = "Report.properties";
    private final static String PROP_FILE_NAME     = "CMA_Email.properties";

    // property file keys
    private final static String KEY_EMAIL_INIT     = "key_email_init";
    private final static String KEY_EMAIL_TO       = "key_email_to";
    private final static String KEY_EMAIL_ADDR     = "key_email_addr";
    private final static String KEY_EMAIL_FROM     = "key_email_from";
    private final static String KEY_EMAIL_SUBJECT  = "key_email_subject";

    // member variable for the report settings
    public String mEmailInit;
    public String mEmailTo;
    public String mEmailAddr;
    public String mEmailFrom;
    public String mEmailSubject;


    /**
     * Default Constructor
     *
     * @param context of the current activity
     */
    public DataCMAEmail(Context context)
    {
        super(context, PROP_FILE_NAME);
        getProperties();
    }   // end public DataReport(Context context)


    /**
     * Required for classes that extend abstract class DataProperties
     * Extracts the email properties from the properties file
     */
    public void getProperties()
    {
        mEmailInit = mProperties.getProperty(KEY_EMAIL_INIT,
                mDataContext.getString(R.string.settings_email_init_def));
        mEmailTo = mProperties.getProperty(KEY_EMAIL_TO,
                mDataContext.getString(R.string.settings_email_to_def));
        mEmailAddr = mProperties.getProperty(KEY_EMAIL_ADDR,
                mDataContext.getString(R.string.settings_email_addr_def));
        mEmailFrom = mProperties.getProperty(KEY_EMAIL_FROM,
                mDataContext.getString(R.string.settings_email_from_def));
        mEmailSubject = mProperties.getProperty(KEY_EMAIL_SUBJECT,
                mDataContext.getString(R.string.settings_email_subject_def));

        Log.i(TAG, "extractProperties(): " + toString());
    }


    /**
     * Sets property values
     */
    public void setProperties()
    {
        // email settings
        setProperty(KEY_EMAIL_INIT,     mEmailInit);

        // save properties to file
        saveProperties();

        Log.i(TAG, "setProperties(): " + toString());
    }


    /**
     * Saves email parameters to xml persistence file.
     */
    public void saveProperties()
    {
        setProperty(KEY_EMAIL_INIT,    mEmailInit);
        setProperty(KEY_EMAIL_TO,      mEmailTo);
        setProperty(KEY_EMAIL_ADDR,    mEmailAddr);
        setProperty(KEY_EMAIL_FROM,    mEmailFrom);
        setProperty(KEY_EMAIL_SUBJECT, mEmailSubject);

        saveProperties();

        Log.i(TAG, "saveEmailProperties()" + toString());
    }



    /**
     * Implements the toString() function
     */
    @Override
    public String toString()
    {
        String ret_str = ", email init = "   + mEmailInit        +
                         ", email to = "     + mEmailTo          +
                         ", email addr = "   + mEmailAddr        +
                         ", email from = "   + mEmailFrom        +
                         ", email sub = "    + mEmailSubject;

        return ret_str;
    }


    /**
     * Required for classes that extend abstract class DataProperties
     * Creates default properties file and populates settings data with
     * default value.
     */
    public void createDefaultProperties()
    {
        // email defaults
        mEmailInit         = mDataContext.getString(R.string.boolean_false);
        mEmailTo           = mDataContext.getString(R.string.settings_email_to_def);
        mEmailAddr         = mDataContext.getString(R.string.settings_email_addr_def);
        mEmailFrom         = mDataContext.getString(R.string.settings_email_from_def);
        mEmailSubject      = mDataContext.getString(R.string.settings_email_subject_def);

        // create the properties data structure
        setProperties();
    }


    ///////////////////////////////////////////////////////////////////////////
    //////////////////////// PRIVATE MEMBER FUNCTIONS /////////////////////////
    ///////////////////////////////////////////////////////////////////////////


}   // end public class DataCMAEmail
