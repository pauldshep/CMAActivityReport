package com.sss.android.cmaactivityreport;

import android.content.Context;
import android.content.res.Resources;
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
    private final static String PROP_FILE_NAME     = "CMA_Report.properties";

    // property file keys
    private final static String KEY_EVENT_NAME     = "key_event_name";
    private final static String KEY_EVENT_TYPE     = "key_event_type";
    private final static String KEY_EVENT_DATE     = "key_event_date";
    private final static String KEY_ATTENDENCE     = "key_attendence";
    private final static String KEY_SALVATIONS     = "key_salvations";
    private final static String KEY_REDEDICATIONS  = "key_rededications";
    private final static String KEY_OTHER_MINISTRY = "key_other_ministry";
    private final static String KEY_COMMENTS       = "key_comments";

    // member variables for the report parameters
    public String  mEventName;
    public String  mEventType;
    public String  mEventDate;
    public String  mCMAAttendence;
    public String  mSalvations;
    public String  mRededications;
    public String  mOtherMinistry;
    public String  mComments;


    /**
     * Default Constructor
     *
     * @param context of the current activity
     */
    public DataCMAActivity(Context context)
    {
        super(context, PROP_FILE_NAME);
        getProperties();
    }   // end public DataReport(Context context)


    /**
     * Required for classes that extend abstract class DataProperties.
     * Extracts the properties from the properties file.  If a property is not
     * in the data file then a default value is used.
     */
    public void getProperties()
    {
        // event data
        mEventName     = mProperties.getProperty(KEY_EVENT_NAME,
                         mDataContext.getString(R.string.edittext_def_event_name));
        mEventType     = mProperties.getProperty(KEY_EVENT_TYPE,
                         mDataContext.getString(R.string.edittext_def_type));
        mEventDate     = mProperties.getProperty(KEY_EVENT_DATE,
                         mDataContext.getString(R.string.edittext_def_date));
        mCMAAttendence = mProperties.getProperty(KEY_ATTENDENCE,
                         mDataContext.getString(R.string.edittext_def_attend));
        mSalvations    = mProperties.getProperty(KEY_SALVATIONS,
                         mDataContext.getString(R.string.edittext_def_salvations));
        mRededications = mProperties.getProperty(KEY_REDEDICATIONS,
                         mDataContext.getString(R.string.edittext_def_rededications));
        mOtherMinistry = mProperties.getProperty(KEY_OTHER_MINISTRY,
                         mDataContext.getString(R.string.edittext_def_other));
        mComments      = mProperties.getProperty(KEY_COMMENTS,
                         mDataContext.getString(R.string.edittext_def_comment));

        Log.i(TAG, "getProperties(): " + toString());
    }


    /**
     * Sets property values
     */
    public void setProperties()
    {
        // event data
        setProperty(KEY_EVENT_NAME,     mEventName);
        setProperty(KEY_EVENT_TYPE,     mEventType);
        setProperty(KEY_EVENT_DATE,     mEventDate);
        setProperty(KEY_ATTENDENCE,     mCMAAttendence);
        setProperty(KEY_SALVATIONS,     mSalvations);
        setProperty(KEY_REDEDICATIONS,  mRededications);
        setProperty(KEY_OTHER_MINISTRY, mOtherMinistry);
        setProperty(KEY_COMMENTS,       mComments);

        // save properties to file
        savePropertiesToFile();

        Log.i(TAG, "setProperties(): " + toString());
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
                ", other = "        + mOtherMinistry    +
                ", comments = "     + mComments;

        return ret_str;
    }


    /**
     * Required for classes that extend abstract class DataProperties
     * Creates default properties file and populates settings data with
     * default value.
     */
    public void createDefaultProperties()
    {
        Log.i(TAG, "createDefaultProperties()");

        // default properties values
        mEventName         = "CMA Event";

        int event_type     = ActivityEventTypes.EVENT_TYPE_SECULAR;
        mEventType         = ActivityEventTypes.CMAActivityTypes[event_type];

        UtilDate util_date = new UtilDate();
        mEventDate         = util_date.toString();

        mCMAAttendence     = Integer.toString(0);
        mSalvations        = Integer.toString(0);
        mRededications     = Integer.toString(0);
        mOtherMinistry     = Integer.toString(0);

        mComments          = "No Event Comments";

        // create the properties data structure
        setProperties();
    }


    ///////////////////////////////////////////////////////////////////////////
    //////////////////////// PRIVATE MEMBER FUNCTIONS /////////////////////////
    ///////////////////////////////////////////////////////////////////////////


}   // end public class DataCMAActivity
