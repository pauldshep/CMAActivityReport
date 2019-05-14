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
    //private final static String PROP_FILE_NAME     = "Report.properties";
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

//    private final static String KEY_EMAIL_INIT     = "key_email_init";
//    private final static String KEY_EMAIL_TO       = "key_email_to";
//    private final static String KEY_EMAIL_ADDR     = "key_email_addr";
//    private final static String KEY_EMAIL_FROM     = "key_email_from";
//    private final static String KEY_EMAIL_SUBJECT  = "key_email_subject";


    // member variables for the report parameters
    public String  mEventName;
    public String  mEventType;
    public String  mEventDate;
    public String  mCMAAttendence;
    public String  mSalvations;
    public String  mRededications;
    public String  mOtherMinistry;
    public String  mComments;

//    // member variable for the report settings
//    public String mEmailInit;
//    public String mEmailTo;
//    public String mEmailAddr;
//    public String mEmailFrom;
//    public String mEmailSubject;


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
     * Required for classes that extend abstract class DataProperties
     * Extracts the properties from the properties file
     */
    public void getProperties()
    {
        // event data
        mEventName     = mProperties.getProperty(KEY_EVENT_NAME);
        mEventType     = mProperties.getProperty(KEY_EVENT_TYPE);
        mEventDate     = mProperties.getProperty(KEY_EVENT_DATE);
        mCMAAttendence = mProperties.getProperty(KEY_ATTENDENCE);
        mSalvations    = mProperties.getProperty(KEY_SALVATIONS);
        mRededications = mProperties.getProperty(KEY_REDEDICATIONS);
        mOtherMinistry = mProperties.getProperty(KEY_OTHER_MINISTRY);
        mComments      = mProperties.getProperty(KEY_COMMENTS);

        //getEmailProperties();

        Log.i(TAG, "extractProperties(): " + toString());
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

        // email settings
        //setProperty(KEY_EMAIL_INIT,     mEmailInit);

        // save properties to file
        saveProperties();

        Log.i(TAG, "setProperties(): " + toString());
    }


//    /**
//     * Saves email parameters xml persistence file.
//     */
//    public void saveEmailProperties()
//    {
//        setProperty(KEY_EMAIL_INIT,    mEmailInit);
//        setProperty(KEY_EMAIL_TO,      mEmailTo);
//        setProperty(KEY_EMAIL_ADDR,    mEmailAddr);
//        setProperty(KEY_EMAIL_FROM,    mEmailFrom);
//        setProperty(KEY_EMAIL_SUBJECT, mEmailSubject);
//
//        saveProperties();
//
//        Log.i(TAG, "saveEmailProperties()" + toStringEmail());
//    }


//    /**
//     * Extract email settings
//     */
//    public void getEmailProperties()
//    {
//        mEmailInit = mProperties.getProperty(KEY_EMAIL_INIT,
//                mDataContext.getString(R.string.settings_email_init_def));
//        mEmailTo = mProperties.getProperty(KEY_EMAIL_TO,
//                mDataContext.getString(R.string.settings_email_to_def));
//        mEmailAddr = mProperties.getProperty(KEY_EMAIL_ADDR,
//                mDataContext.getString(R.string.settings_email_addr_def));
//        mEmailFrom = mProperties.getProperty(KEY_EMAIL_FROM,
//                mDataContext.getString(R.string.settings_email_from_def));
//        mEmailSubject = mProperties.getProperty(KEY_EMAIL_SUBJECT,
//                mDataContext.getString(R.string.settings_email_subject_def));
//    }


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


//    /**
//     * Implements the toString() function for just the email settings
//     */
//    public String toStringEmail()
//    {
//        String ret_str = ", email init = "   + mEmailInit        +
//                         ", email to = "     + mEmailTo          +
//                         ", email addr = "   + mEmailAddr        +
//                         ", email from = "   + mEmailFrom        +
//                         ", email sub = "    + mEmailSubject;
//
//        return ret_str;
//    }


    /**
     * Required for classes that extend abstract class DataProperties
     * Creates default properties file and populates settings data with
     * default value.
     */
    public void createDefaultProperties()
    {
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

        // email defaults
        //setEmailToDefault();

        // create the properties data structure
        setProperties();
    }

//    /**
//     * Sets email properties to default
//     */
//    public void setEmailToDefault()
//    {
//        mEmailInit         = mDataContext.getString(R.string.boolean_false);
//        mEmailTo           = mDataContext.getString(R.string.settings_email_to_def);
//        mEmailAddr         = mDataContext.getString(R.string.settings_email_addr_def);
//        mEmailFrom         = mDataContext.getString(R.string.settings_email_from_def);
//        mEmailSubject      = mDataContext.getString(R.string.settings_email_subject_def);
//    }


    ///////////////////////////////////////////////////////////////////////////
    //////////////////////// PRIVATE MEMBER FUNCTIONS /////////////////////////
    ///////////////////////////////////////////////////////////////////////////


}   // end public class DataCMAActivity
