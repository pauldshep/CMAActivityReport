package com.sss.android.cmaactivityreport;

import android.content.Context;
import android.util.Log;
import java.util.Properties;

/**
 * Encapsulates the main application report data.  This information is saved
 * to a local properties file and is used to generate the CMA Activity Report
 *
 * Created by Paul Shepherd on 6/7/2016.
 */
public class DataCMAActivity extends DataProperties
{
    private final static String TAG                = "DataCMAActivity";
    private final static String PROP_FILE_NAME     = "Report.properties";

    // property file keys
    private final static String KEY_EVENT_NAME     = "key_event_name";
    private final static String KEY_EVENT_TYPE     = "key_event_type";
    private final static String KEY_EVENT_DATE     = "key_event_date";
    private final static String KEY_ATTENDENCE     = "key_attendence";
    private final static String KEY_SALVATIONS     = "key_salvations";
    private final static String KEY_REDEDICATIONS  = "key_rededications";
    private final static String KEY_OTHER_MINISTRY = "key_other_ministry";


    // member variables for the report parameters
    public String mEventName;
    public String mEventType;
    public String mEventDate;
    public String mCMAAttendence;
    public String mSalvations;
    public String mRededications;
    public String mOtherMinistry;


    //**************************************************************************
    /**
     * Default Constructor
     * @param context of the current activity
     */
    public DataCMAActivity(Context context)
    {
        super(context, PROP_FILE_NAME);
    }   // end public DataReport(Context context)


    /***************************************************************************
    /**
     * Required for classes that extend abstract class DataProperties
     * Creates default properties file and populates settings data with
     * default value.
     */
    public void createDefaultProperties()
    {
        // defalut properties values
        mEventName         = "CMA Event";

        int event_type     = ActivityEventTypes.EVENT_TYPE_SECULAR;
        mEventType         = ActivityEventTypes.CMAActivityTypes[event_type];

        UtilDate util_date = new UtilDate();
        mEventDate         = util_date.toString();

        mCMAAttendence     = new Integer(0).toString();
        mSalvations        = new Integer(0).toString();
        mRededications     = new Integer(0).toString();
        mOtherMinistry     = new Integer(0).toString();

        // create the properties data structure
        setProperties();
    }


    //**************************************************************************
    /**
     * Sets property values
     */
    public void setProperties()
    {
        setProperty(KEY_EVENT_NAME,     mEventName);
        setProperty(KEY_EVENT_TYPE,     mEventType);
        setProperty(KEY_EVENT_DATE,     mEventDate);
        setProperty(KEY_ATTENDENCE,     mCMAAttendence);
        setProperty(KEY_SALVATIONS,     mSalvations);
        setProperty(KEY_REDEDICATIONS,  mRededications);
        setProperty(KEY_OTHER_MINISTRY, mOtherMinistry);

        Log.i(TAG, "setProperties(): " + toString());
    }


    //**************************************************************************
    /**
     * Required for classes that extend abstract class DataProperties
     * Extracts the properties from the properties file
     */
    public void extractProperties()
    {
        mEventName     = mProperties.getProperty(KEY_EVENT_NAME);
        mEventType     = mProperties.getProperty(KEY_EVENT_TYPE);
        mEventDate     = mProperties.getProperty(KEY_EVENT_DATE);
        mCMAAttendence = mProperties.getProperty(KEY_ATTENDENCE);
        mSalvations    = mProperties.getProperty(KEY_SALVATIONS);
        mRededications = mProperties.getProperty(KEY_REDEDICATIONS);
        mOtherMinistry = mProperties.getProperty(KEY_OTHER_MINISTRY);

        Log.i(TAG, "extractProperties(): " + toString());
    }


    /**
     * Implements the toString() function
     */
    @Override
    public String toString()
    {
        String ret_str =
                "name = "           + mEventName        +
                ", type = "         + mEventType        +
                ", date = "         + mEventDate        +
                ", attend = "       + mCMAAttendence    +
                ", salvations = "   + mSalvations       +
                ", reded = "        + mRededications    +
                ", other = "        + mOtherMinistry;

        return ret_str;
    }

}   // end public class DataCMAActivity
