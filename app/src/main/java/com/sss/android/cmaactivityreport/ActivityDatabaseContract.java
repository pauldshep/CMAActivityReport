package com.sss.android.cmaactivityreport;

import android.provider.BaseColumns;

/**
 * Defines a Schema and Contract for the CMA Activity report database
 *
 * Created by Paul Shepherd on 5/25/2016.
 */
public final class ActivityDatabaseContract
{
    /**
     * Empty constructor to prevent anyone from instantiating the contract
     * class
     */
    public ActivityDatabaseContract()
    {

    }

    /**
     * Inner class that defines the table contents
     */
    public static abstract class ActivityEntry implements BaseColumns
    {
        public static final String TABLE_NAME                       = "activity_entry_name";
        public static final String COLUMN_NAME_EVENT_NAME           = "event_name";
        public static final String COLUMN_NAME_EVENT_TYPE           = "event_type";
        public static final String COLUMN_NAME_EVENT_DATE           = "event_date";
        public static final String COLUMN_NAME_TOTALS_ATTENDACE     = "totals_attendance";
        public static final String COLUMN_NAME_TOTALS_SALVATIONS    = "totals_salvations";
        public static final String COLUMN_NAME_TOTALS_REDEDICATIONS = "totals_rededications";
        public static final String COLUMN_NAME_TOTALS_OTHER         = "totals_other";
    }
}   // end class ActivityDatabaseContract
