package com.sss.android.cmaactivityreport;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import org.w3c.dom.Text;


/**
 * CMA Activity Report Application entry point.
 */
public class ActivityMain extends AppCompatActivity
{
    private final static String TAG        = "ActivityMain";
    private final static String EVENT_TYPE = "event_type";
    private final static String EVENT_DATE = "event_date";

    // widget variables
    private TextView mTextViewEventType;
    private TextView mTextViewEventDate;
    private TextView mTextViewComment;

    private Boolean  mIsInitialized;


    //**************************************************************************
    /**
     * Called when this application view is created.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "onCreate()");

        mIsInitialized = false;

        // lookup widgets
        mTextViewEventType = findViewById(R.id.textViewEventType);
        mTextViewEventDate = findViewById(R.id.textViewEventDate);
        mTextViewComment   = findViewById(R.id.textViewComment);

        // configure the action bar and handle its messages
        ActionBar action_bar = getSupportActionBar();
        action_bar.setTitle(R.string.actionbar_title);

        // configure the event type display
        Intent this_intent      = getIntent();
        int event_type          = this_intent.getIntExtra(ActivityEventTypes.EVENT_TYPE, ActivityEventTypes.DEFAULT_EVENT_TYPE);
        mTextViewEventType.setText(ActivityEventTypes.CMAActivityTypes[event_type]);

        // configure the date display
        UtilDate util_date      = new UtilDate();
        mTextViewEventDate.setText(util_date.toString());


        ////////////////////////////////////////////////////////////////////////
        /////////////////////////// MESSAGE HANDLERS ///////////////////////////
        ////////////////////////////////////////////////////////////////////////

        //----------------------------------------------------------------------
        // Event Name message handler
        //----------------------------------------------------------------------
        final TextView text_view_event_name = (TextView)findViewById(R.id.editTextEventName);
        text_view_event_name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String event_name = text_view_event_name.getText().toString();
                String def_name   = getString(R.string.edittext_def_event_name);
                if(event_name.equals(def_name))
                {
                    text_view_event_name.setText("");
                }
            }
        });

        text_view_event_name.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean b)
            {
                if(mIsInitialized == true)
                {
                    String event_name = text_view_event_name.getText().toString();
                    String def_name = getString(R.string.edittext_def_event_name);
                    if(event_name.equals(def_name))
                    {
                        text_view_event_name.setText("");
                    }
                }
                else
                {
                    // I have to do this because by default event name is
                    // initially selected
                    mIsInitialized = true;
                }
            }
        });

        //----------------------------------------------------------------------
        // Event Type message handler associated with the title and type field
        //----------------------------------------------------------------------
        TextView text_view_type_title = (TextView)findViewById(R.id.textViewTypeTitle);
        text_view_type_title.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Log.i("ActivityMain", "textview type selected");
                Intent intent = new Intent(getApplicationContext(), ActivityEventTypes.class);
                startActivityForResult(intent, ActivityEventTypes.REQUEST_CODE);
            }
        });


        mTextViewEventType.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Log.i("ActivityMain", "textview type selected");
                Intent intent = new Intent(getApplicationContext(), ActivityEventTypes.class);
                startActivityForResult(intent, ActivityEventTypes.REQUEST_CODE);
            }
        });


        //----------------------------------------------------------------------
        // Event Date TextView
        //----------------------------------------------------------------------
        TextView text_view_date_title = (TextView)findViewById(R.id.textViewDateTitle);
        text_view_date_title.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.i("ActivityMain", "Date onClick() start");

                DatePickerFragment date_picker = new DatePickerFragment()
                {
                    /**
                     * Override onDateSet().  This function is called when the
                     * date picker OK button is selected. It is not called if
                     * CANCEL is selected and the specified date will remain
                     * the current date.
                     *
                     * @param view of the date picker dialog fragment
                     * @param year user specified year
                     * @param month user specified month
                     * @param day user specified day
                     */
                    public void onDateSet(DatePicker view, int year, int month, int day)
                    {
                        int    loc_month = month + 1;
                        String date_str  =  loc_month + "-" + day + "-" + year;
                        Log.i("ActivityMain", "specified date = " + date_str);
                        TextView date_text_view = (TextView)findViewById(R.id.textViewEventDate);
                        date_text_view.setText(date_str);
                    }
                };

                UtilDate util_date = new UtilDate();
                date_picker.setArguments(util_date.buildBundle());
                date_picker.show(getFragmentManager(), "datePicker");

                String date_fr_picker   = date_picker.toString();
                Log.d("ActivityMain", "Date onClick() end " + date_fr_picker);
            }
        });

        mTextViewEventDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.i("ActivityMain", "Date onClick() start");

                DatePickerFragment date_picker = new DatePickerFragment()
                {
                    /**
                     * Override onDateSet().  This function is called when the
                     * date picker OK button is selected. It is not called if
                     * CANCEL is selected and the specified date will remain
                     * the current date.
                     *
                     * @param view of the date picker dialog fragment
                     * @param year user specified year
                     * @param month user specified month
                     * @param day user specified day
                     */
                    public void onDateSet(DatePicker view, int year, int month, int day)
                    {
                        int loc_month = month + 1;
                        String date_str =  loc_month + "-" + day + "-" + year;
                        Log.i("ActivityMain", "specified date = " + date_str);
                        TextView date_text_view = (TextView)findViewById(R.id.textViewEventDate);
                        date_text_view.setText(date_str);
                    }
                };

                UtilDate util_date = new UtilDate();
                date_picker.setArguments(util_date.buildBundle());
                date_picker.show(getFragmentManager(), "datePicker");

                String date_fr_picker = date_picker.toString();
                Log.d("ActivityMain", "Date onClick() end " + date_fr_picker);
            }   // end public void onClick(View v)
        });


        //----------------------------------------------------------------------
        // Send Button event listener
        //----------------------------------------------------------------------
        Button button_send = (Button)findViewById(R.id.button_send);
        button_send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.i("cmarpt", "send button selected");
                emailActivityReport();
                CMAActivityInfo cma_activity = new CMAActivityInfo();
                cma_activity.mDataCMAActivity.setProperties();
            }
        });


        //----------------------------------------------------------------------
        // Help Buttons
        //----------------------------------------------------------------------
        ImageButton ib_other_help = findViewById(R.id.imageButtonOtherHelp);
        ib_other_help.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                helpDialog("Other Ministry",
                    "Those you personally prayed with or helped in a matter other than salvation or rededication");
            }
        });

        ImageButton ib_salvation    = findViewById(R.id.imageButtonSlavationHelp);
        ib_salvation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                helpDialog("Salvation",
                        "Those you personally prayed with to accept Christ as Lord and Savior");
            }
        });

        ImageButton ib_rededication = findViewById(R.id.imageButtonRededicationHelp);
        ib_rededication.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                helpDialog("Rededication",
                        "Those you personally prayed with for rededication or recommitment to Christ");
            }
        });

        //----------------------------------------------------------------------
        // Comment message handler
        //----------------------------------------------------------------------
        final TextView text_view_comment = (TextView)findViewById(R.id.editTextComment);

        text_view_comment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String comment_str = text_view_comment.getText().toString();
                String def_comment = getString(R.string.edittext_def_comment);
                if(comment_str.equals(def_comment))
                {
                    text_view_comment.setText("");
                }
            }
        });

        text_view_comment.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean b)
            {
                String comment_str = text_view_comment.getText().toString();
                String def_comment = getString(R.string.edittext_def_comment);
                if(comment_str.equals(def_comment))
                {
                    text_view_comment.setText("");
                }
            }
        });
    }   // end protected void onCreate(Bundle savedInstanceState)


    //**************************************************************************
    /**
     * Creates the action bar menu items
     * @param menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    //**************************************************************************
    /**
     * Handle action bar menu item selection
     * @param item
     * @return always true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent          intent;
        CMAActivityInfo cma_activity;

        switch(item.getItemId())
        {
            case R.id.action_bar_settings:
                // display settings activity
                intent = new Intent(getApplicationContext(), ActivitySettings.class);
                startActivity(intent);
                break;

            case R.id.action_bar_save:
                Toast.makeText(this, "Action Bar Save Menu Item",
                        Toast.LENGTH_SHORT).show();
                cma_activity = new CMAActivityInfo();
                cma_activity.mDataCMAActivity.setProperties();
                break;

            case R.id.action_bar_recall:
                Toast.makeText(this, "ActionBar Recall Menu Item",
                        Toast.LENGTH_SHORT).show();
                cma_activity = new CMAActivityInfo();
                cma_activity.mDataCMAActivity.getProperties();
                cma_activity.mDataCMAActivity.extractProperties();
                cma_activity.setCMAActivityData();
                break;

            case R.id.action_bar_clear:
                Toast.makeText(this, "Action Bar Clear Menu Item",
                        Toast.LENGTH_SHORT).show();
                cma_activity = new CMAActivityInfo();
                cma_activity.mDataCMAActivity.createDefaultProperties();
                cma_activity.setCMAActivityData();
                break;

            case R.id.action_bar_about:
                // display the About activity
                intent = new Intent(getApplicationContext(), ActivityAbout.class);
                startActivity(intent);
                break;

            default:
                Log.e("ActivityMain",
                      "action bar menu item not handled = " + item.getItemId());
                break;
        }

        return true;
    }   // end public boolean onOptionsItemSelected(MenuItem item)


    /**
     * Handle results genererated by other activities
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i(TAG, "onActivityResult(requestCode = " + requestCode +
                   ", resultCode = " + resultCode + ", data = " + data);

        if(requestCode == ActivityEventTypes.REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                int event_type = data.getIntExtra(ActivityEventTypes.EVENT_TYPE, 0);
                mTextViewEventType.setText(ActivityEventTypes.CMAActivityTypes[event_type]);
            }
        }
    }


    ////////////////////////////////////////////////////////////////////////////
    /////////////////////////// LIFE CYCLE FUNCTIONS ///////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onStart()
    {
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.i(TAG, "onRestart()");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i(TAG, "onPause()");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.i(TAG, "onStop()");
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        Log.i(TAG, "onSaveInstanceState()");
        super.onSaveInstanceState(outState);

        // save event type
        String event_type = mTextViewEventType.getText().toString();
        outState.putString(EVENT_TYPE, event_type);
        Log.i(TAG, "onSaveInstanceState(): saving event type = " + event_type);

        // save event date
        String event_date = mTextViewEventDate.getText().toString();
        outState.putString(EVENT_DATE, event_date);
        Log.i(TAG, "onSaveInstanceState(): saving event date = " + event_date);
    }

    @Override
    public void onRestoreInstanceState(Bundle outState)
    {
        Log.i(TAG, "onRestoreInstanceState()");
        super.onRestoreInstanceState(outState);

        // restore event type
        String event_type = outState.getString(EVENT_TYPE, "Secular");
        mTextViewEventType.setText(event_type);
        Log.i(TAG, "onRestoreInstanceState(): restoring event type = " + event_type);

        // restore event date
        String event_date = outState.getString(EVENT_TYPE, "01/01/01");
        mTextViewEventDate.setText(event_date);
        Log.i(TAG, "onRestoreInstanceState(): restoring event date = " + event_date);
    }




    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////// PRIVATE MEMBER FUNCTIONS /////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Builds CMA activity report and EMails it to the location specified in
     * the settings dialog.
     */
    public void emailActivityReport()
    {
        Log.i(TAG, "emailActivityReport()");

        // get email settings
        DataActivitySettings data_settings = new DataActivitySettings(this);
        Log.i(TAG, "Report Settings: " + data_settings);

        // get report information and generate report text
        CMAActivityInfo activity_info = new CMAActivityInfo();
        String     activity_report = activity_info.buildActivityReport();
        Log.i(TAG, "Activity Report: " + activity_report);

        // send the email
        Intent send_email = new Intent(Intent.ACTION_SEND);
        send_email.setType(("message/rfc822"));
        send_email.putExtra(Intent.EXTRA_EMAIL,   new String[] {data_settings.mEmailAddr});
        send_email.putExtra(Intent.EXTRA_SUBJECT, data_settings.mEmailSubject);
        send_email.putExtra(Intent.EXTRA_TEXT,    activity_info.buildActivityReport());

        try
        {
            startActivity(Intent.createChooser(send_email,
                    "Send CMA Report Email"));
        }
        catch(android.content.ActivityNotFoundException ex)
        {
            Toast.makeText(this, "WARNING: there are no email clients installed.",
                    Toast.LENGTH_SHORT).show();
        }
    }   // end public void emailActivityReport()


    /**
     * Build and display help dialog
     */
    private void helpDialog(String helpTitle, String helpMsg)
    {
        AlertDialog alertDialog =
                new AlertDialog.Builder(ActivityMain.this).create();
        alertDialog.setTitle(helpTitle);
        alertDialog.setMessage(helpMsg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }   // end private void helpDialog(String helpTitle, String helpMsg)


    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////// INTERNAL CLASSES //////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Internal Class that encapsulates CMA activity report dynamic data
     */
    private class CMAActivityInfo
    {
        // class to hold, save, and recall activity data
        DataCMAActivity mDataCMAActivity;

        // application widgets
        private EditText mEditViewEventName;
        private TextView mTextViewEventType;
        private TextView mTextViewEventDate;
        private EditText mEditTextAttend;
        private EditText mEditTextSalvations;
        private EditText mEditTextRededications;
        private EditText mEditTextOtherMinistery;
        private EditText mEditTextComments;


        /**
         * Default Constructor.  Get current data from activity widgets
         */
        public CMAActivityInfo()
        {
            // create class instance to hold the data
            Context context  = getApplicationContext();
            mDataCMAActivity = new DataCMAActivity(context);

            // get the widgets
            mEditViewEventName      = (EditText)findViewById((R.id.editTextEventName));
            mTextViewEventType      = (TextView)findViewById((R.id.textViewEventType));
            mTextViewEventDate      = (TextView)findViewById((R.id.textViewEventDate));
            mEditTextAttend         = (EditText)findViewById((R.id.editTextEventAttend));
            mEditTextSalvations     = (EditText)findViewById((R.id.editTextSalvations));
            mEditTextRededications  = (EditText)findViewById((R.id.editTextRededications));
            mEditTextOtherMinistery = (EditText)findViewById((R.id.editTextOther));
            mEditTextComments       = (EditText)findViewById((R.id.editTextComment));

            // get current widget data
            getCMAActivityData();
        }


        /***********************************************************************
        /**
         *  Get CMA activity information from the widgets and saves their
         *  values to program storage
         */
        public void getCMAActivityData()
        {
            mDataCMAActivity.mEventName     = mEditViewEventName.getText().toString();
            mDataCMAActivity.mEventType     = mTextViewEventType.getText().toString();
            mDataCMAActivity.mEventDate     = mTextViewEventDate.getText().toString();
            mDataCMAActivity.mCMAAttendence = mEditTextAttend.getText().toString();
            mDataCMAActivity.mSalvations    = mEditTextSalvations.getText().toString();
            mDataCMAActivity.mRededications = mEditTextRededications.getText().toString();
            mDataCMAActivity.mOtherMinistry = mEditTextOtherMinistery.getText().toString();
            mDataCMAActivity.mComments      = mEditTextComments.getText().toString();

            Log.i(TAG, "getCMAActivityData(): " + mDataCMAActivity.toString());
        }


        /***********************************************************************
        /**
         * Sets CMA Activity Data from local storage
         */
        public void setCMAActivityData()
        {
            mEditViewEventName.setText(mDataCMAActivity.mEventName);
            mTextViewEventType.setText(mDataCMAActivity.mEventType);
            mTextViewEventDate.setText(mDataCMAActivity.mEventDate);
            mEditTextAttend.setText(mDataCMAActivity.mCMAAttendence);
            mEditTextSalvations.setText(mDataCMAActivity.mSalvations);
            mEditTextRededications.setText(mDataCMAActivity.mRededications);
            mEditTextOtherMinistery.setText(mDataCMAActivity.mOtherMinistry);
            mEditTextComments.setText(mDataCMAActivity.mComments);

            Log.i(TAG, "setCMAActivityData(): " + mDataCMAActivity.toString());
        }


        /***********************************************************************
        /**
         * Builds activity report text to be emailed
         * @return  activity report text to be emailed
         */
        public String buildActivityReport()
        {
            String activity_report =
                    "Event = "            + mDataCMAActivity.mEventName     +
                    ", Event Type = "     + mDataCMAActivity.mEventType     +
                    ", Event Date = "     + mDataCMAActivity.mEventDate     +
                    ", CMA Attendance = " + mDataCMAActivity.mCMAAttendence +
                    ", Salvations = "     + mDataCMAActivity.mSalvations    +
                    ", Rededications = "  + mDataCMAActivity.mRededications +
                    ", Other = "          + mDataCMAActivity.mOtherMinistry +
                    ", Comment: "         + mDataCMAActivity.mComments;

            return activity_report;
        }

    }   // end internal private class DataReport

}   // end public class ActivityMain extends AppCompatActivity



