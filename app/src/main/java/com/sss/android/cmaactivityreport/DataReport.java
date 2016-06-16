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
public class DataReport extends DataProperties
{
    private final static String TAG            =  "DataReport";
    private final static String PROP_FILE_NAME = "Report.properties";

    // property keys
    private final static String KEY_EVENT_NAME     = "key_event_name";
    private final static String KEY_EVENT_TYPE     = "key_event_type";
    private final static String KEY_EVENT_DAY      = "key_event_day";
    private final static String KEY_EVENT_MONTH    = "key_event_month";
    private final static String KEY_EVENT_YEAR     = "key_event_year";
    private final static String KEY_ATTENDENCE     = "key_attendence";
    private final static String KEY_SALVATIONS     = "key_salvations";
    private final static String KEY_REDEDICATIONS  = "key_rededications";
    private final static String KEY_OTHER_MINISTRY = "key_other_ministry";


    // member variables for the report parameters
    private String  mEventName;
    private Integer mEventType;
    private Integer mEventDay;
    private Integer mEventMonth;
    private Integer mEventYear;
    private Integer mAttendence;
    private Integer mSalvations;
    private Integer mRededications;
    private Integer mOtherMinistry;

    /**
     * Default Constructor
     * @param context of the current activity
     */
    public DataReport(Context context)
    {
        super(context, PROP_FILE_NAME);
    }   // end public DataReport(Context context)


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
        mEventName         = "CMA Event";
        mEventType         = ActivityEventTypes.EVENT_TYPE_SECULAR;

        UtilDate util_date = new UtilDate();
        mEventDay          = util_date.mDay;
        mEventMonth        = util_date.mMonth;
        mEventYear         = util_date.mYear;

        mAttendence        = 0;
        mSalvations        = 0;
        mRededications     = 0;
        mOtherMinistry     = 0;

        // create the properties data structure
        mProperties.put(KEY_EVENT_NAME,     mEventName);
        mProperties.put(KEY_EVENT_TYPE,     mEventType);
        mProperties.put(KEY_EVENT_DAY,      mEventDay.toString());
        mProperties.put(KEY_EVENT_MONTH,    mEventMonth.toString());
        mProperties.put(KEY_EVENT_YEAR,     mEventYear.toString());
        mProperties.put(KEY_ATTENDENCE,     mAttendence.toString());
        mProperties.put(KEY_SALVATIONS,     mSalvations.toString());
        mProperties.put(KEY_REDEDICATIONS,  mRededications.toString());
        mProperties.put(KEY_OTHER_MINISTRY, mOtherMinistry.toString());
    }


    /**
     * Extracts the properties from the properties file
     */
    protected void extractProperties()
    {
        mEventName     = mProperties.getProperty(KEY_EVENT_NAME);
        mEventType     = Integer.parseInt(mProperties.getProperty(KEY_EVENT_TYPE));
        mEventDay      = Integer.parseInt(mProperties.getProperty(KEY_EVENT_DAY));
        mEventMonth    = Integer.parseInt(mProperties.getProperty(KEY_EVENT_MONTH));
        mEventYear     = Integer.parseInt(mProperties.getProperty(KEY_EVENT_YEAR));
        mAttendence    = Integer.parseInt(mProperties.getProperty(KEY_ATTENDENCE));
        mSalvations    = Integer.parseInt(mProperties.getProperty(KEY_SALVATIONS));
        mRededications = Integer.parseInt(mProperties.getProperty(KEY_REDEDICATIONS));
        mOtherMinistry = Integer.parseInt(mProperties.getProperty(KEY_OTHER_MINISTRY));

        Log.i(TAG, "settings from properties: " + toString());
    }
}   // end public class DataReport
