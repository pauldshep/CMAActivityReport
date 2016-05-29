package com.sss.android.cmaactivityreport;

/**
 * Encapsulates CMA Activity Report Data
 *
 * Created by Paul Shepherd on 5/28/2016.
 */
public class DataActivity
{
    // Settings Activity Data
    public int    settingsEmailId;
    public String settingsEmailTo;
    public String settingsEmailAddress;
    public String settingsEmailFrom;
    public String settingsEmailSubject;


    /**
     * Constructor for Settings data
     */
    public DataActivity(int id, String emailTo,   String emailAddress,
                                String emailFrom, String emailSubject)
    {
        settingsEmailId         = id;
        settingsEmailTo         = emailTo;
        settingsEmailAddress    = emailAddress;
        settingsEmailFrom       = emailFrom;
        settingsEmailSubject    = emailSubject;
    }

    /**
     * Implements toString for this class
     */
    @Override
    public String toString()
    {
        String ret_str =
                        "id = "         + settingsEmailId       +
                        ", to = "       + settingsEmailTo       +
                        ", addr = "     + settingsEmailAddress  +
                        ", from = "     + settingsEmailFrom     +
                        ", subject = "  + settingsEmailSubject;

        return ret_str;
    }
}   // end public class DataActivity
