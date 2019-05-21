package com.sss.android.cmaactivityreport;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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


/**
 * CMA Activity Report Application entry point.
 */
public class ActivityMain extends AppCompatActivity
{
    private final static String TAG        = "ActivityMain";
    private final static String EVENT_TYPE = "event_type";
    private final static String EVENT_DATE = "event_date";

    // widget variables
    private EditText mEditTextEventName;    // event name
    private TextView mTextViewEventType;    // event type
    private TextView mTextViewDateTitle;    // title string for event date
    private TextView mTextViewEventDate;    // event date
    private TextView mTextViewComment;      // general comments
    private EditText mEditTextAttend;       // event cma attendance
    private EditText mEditTextSalvations;   // number of salvations
    private EditText mEditTextRededicate;   // number of rededications
    private EditText mEditTextOther;

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
        mEditTextEventName  = findViewById(R.id.editTextEventName);
        mTextViewEventType  = findViewById(R.id.textViewEventType);
        mTextViewDateTitle  = findViewById(R.id.textViewDateTitle);
        mTextViewEventDate  = findViewById(R.id.textViewEventDate);
        mTextViewComment    = findViewById(R.id.textViewComment);
        mEditTextAttend     = findViewById(R.id.editTextEventAttend);
        mEditTextSalvations = findViewById(R.id.editTextSalvations);
        mEditTextRededicate = findViewById(R.id.editTextRededications);
        mEditTextOther      = findViewById(R.id.editTextOther);



        // configure the action bar and handle its messages
        ActionBar action_bar = getSupportActionBar();
        action_bar.setTitle(R.string.actionbar_title);



        // set report parameters to their default values
        setReportParametersToDefault();


        ////////////////////////////////////////////////////////////////////////
        /////////////////////////// MESSAGE HANDLERS ///////////////////////////
        ////////////////////////////////////////////////////////////////////////

        //----------------------------------------------------------------------
        // Event Name message handler
        //----------------------------------------------------------------------
        mEditTextEventName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String event_name = mEditTextEventName.getText().toString();
                String def_name   = getString(R.string.edittext_def_event_name);
                if(event_name.equals(def_name))
                {
                    mEditTextEventName.setText("");
                }
            }
        });

        mEditTextEventName.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean hasFocus)
            {
                if((mIsInitialized == true) && (hasFocus == true))
                {
                    String event_name = mEditTextEventName.getText().toString();
                    String def_name   = getString(R.string.edittext_def_event_name);
                    if(event_name.equals(def_name))
                    {
                        mEditTextEventName.setText("");
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
        mTextViewDateTitle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.i(TAG, "mTextViewDateTitle.setOnClickListener()");
                displayDatePicker();
            }   // end mTextViewDateTitle.setOnClickListener
        });

        mTextViewEventDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.i(TAG, "mTextViewEventDate.setOnClickListener()");
                displayDatePicker();
            }   // end mTextViewEventDate.setOnClickListener
        });


        //----------------------------------------------------------------------
        // Totals message handlers
        // Attendence
        //----------------------------------------------------------------------
        mEditTextAttend.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean b)
            {
                String attend_str = mEditTextAttend.getText().toString();
                String attend_def = getString(R.string.edittext_def_attend);
                if(attend_str.equals(attend_def))
                {
                    mEditTextAttend.setText("");
                }
            }
        });

        Button btn_inc_attend = findViewById(R.id.btnIncAttend);
        btn_inc_attend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                incDecEditText(mEditTextAttend, 1);
            }
        });

        Button btn_dec_attend = findViewById(R.id.btnDecAttend);
        btn_dec_attend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                incDecEditText(mEditTextAttend, -1);
            }
        });


        //----------------------------------------------------------------------
        // Salvations

        mEditTextSalvations.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean b)
            {
                String salvations_str = mEditTextSalvations.getText().toString();
                String salvations_def = getString(R.string.edittext_def_salvations);
                if(salvations_str.equals(salvations_def))
                {
                    mEditTextSalvations.setText("");
                }
            }
        });

        Button btn_inc_salvations = findViewById(R.id.btnIncSalvations);
        btn_inc_salvations.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                incDecEditText(mEditTextSalvations, 1);
            }
        });

        Button btn_dec_salvations = findViewById(R.id.btnDecSalvations);
        btn_dec_salvations.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                incDecEditText(mEditTextSalvations, -1);
            }
        });


        //----------------------------------------------------------------------
        // Rededicate

        mEditTextRededicate.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean b)
            {
                String rededication_str = mEditTextRededicate.getText().toString();
                String rededication_def = getString(R.string.edittext_def_rededications);
                if(rededication_str.equals(rededication_def))
                {
                    mEditTextRededicate.setText("");
                }
            }
        });

        Button btn_inc_rededications = findViewById(R.id.btnIncRededications);
        btn_inc_rededications.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                incDecEditText(mEditTextRededicate, 1);
            }
        });

        Button btn_dec_rededications = findViewById(R.id.btnDecRededications);
        btn_dec_rededications.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                incDecEditText(mEditTextRededicate, -1);
            }
        });

        //----------------------------------------------------------------------
        // Other

        mEditTextOther.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean b)
            {
                String other_str = mEditTextOther.getText().toString();
                String other_def = getString(R.string.edittext_def_other);
                if(other_str.equals(other_def))
                {
                    mEditTextOther.setText("");
                }
            }
        });

        Button btn_inc_other = findViewById(R.id.btnIncOther);
        btn_inc_other.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                incDecEditText(mEditTextOther, 1);
            }
        });

        Button btn_dec_other = findViewById(R.id.btnDecOther);
        btn_dec_other.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                incDecEditText(mEditTextOther, -1);
            }
        });



        //----------------------------------------------------------------------
        // Help Buttons
        //----------------------------------------------------------------------

        ImageButton ib_attend = findViewById(R.id.imageButtonAttendHelp);
        ib_attend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                helpDialog("Attendance", getString(R.string.help_attendance));
            }
        });

        ImageButton ib_salvation = findViewById(R.id.imageButtonSalvationHelp);
        ib_salvation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                helpDialog("Salvation", getString(R.string.help_salvation));
            }
        });

        ImageButton ib_rededication = findViewById(R.id.imageButtonRededicationHelp);
        ib_rededication.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                helpDialog("Rededication", getString(R.string.help_rededication));
            }
        });

        ImageButton ib_other_help   = findViewById(R.id.imageButtonOtherHelp);
        ib_other_help.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                helpDialog("Other Ministry", getString(R.string.help_other));
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


        //----------------------------------------------------------------------
        // Send Button event listener
        //----------------------------------------------------------------------
        Button button_send = findViewById(R.id.button_send);
        button_send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.i(TAG, "send button selected");

                // email the activity report
                emailActivityReport();

                // save the activity information
                CMAActivityInfo cma_activity = new CMAActivityInfo();
                cma_activity.mDataCMAActivity.setProperties();
            }
        });
    }   // end protected void onCreate(Bundle savedInstanceState)


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
                cma_activity.setCMAActivityData();
                break;

            case R.id.action_bar_clear:
                // sets display to default
                Toast.makeText(this, "Action Bar Clear Menu Item",
                        Toast.LENGTH_SHORT).show();
                setReportParametersToDefault();
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
     * Handle results generated by other activities
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
        String event_date = outState.getString(EVENT_DATE, "01/01/01");
        mTextViewEventDate.setText(event_date);
        Log.i(TAG, "onRestoreInstanceState(): restoring event date = " + event_date);
    }


    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////// PRIVATE MEMBER FUNCTIONS /////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Increments or decrements the numeric value in an EditText and updates
     * the EditText display.
     *
     * @param editText
     * @param inc_dec
     */
    private void incDecEditText(EditText editText, int inc_dec)
    {
        int int_val;

        try
        {
            int_val = Integer.parseInt(editText.getText().toString());
            int_val += inc_dec;

            if(int_val < 0)
            {
                int_val = 0;
            }
        }
        catch(NumberFormatException nfe)
        {
            int_val = 0;
        }

        editText.setText(Integer.toString(int_val));
    }   // end private void incDecEditText(EditText editText, int inc_dec)

    /**
     * Displays the Date Picker
     */
    private void displayDatePicker()
    {
        Log.i(TAG, "displayDatePicker()");
        DatePickerFragment date_picker = new DatePickerFragment();
        UtilDate util_date = new UtilDate();
        date_picker.setArguments(util_date.buildBundle());
        date_picker.show(getFragmentManager(), "DatePicker");
    }

    /**
     * Sets activity report parameters display to their default values
     */
    private void setReportParametersToDefault()
    {
        Log.i(TAG, "setReportParametersToDefault()");

        // event name
        mEditTextEventName.setText(getString(R.string.edittext_def_event_name));

        // event type
        mTextViewEventType.setText(getDefaultEventType());

        // event date
        UtilDate util_date = new UtilDate();
        mTextViewEventDate.setText(util_date.toString());

        // attendance
        mEditTextAttend.setText(getString(R.string.edittext_def_attend));

        // salvations
        mEditTextSalvations.setText(getString(R.string.edittext_def_salvations));

        // rededications
        mEditTextRededicate.setText(getString(R.string.edittext_def_rededications));

        // other ministry
        mEditTextOther.setText(getString(R.string.edittext_def_other));

        // comments
        mTextViewComment.setText(getString(R.string.edittext_def_comment));
    }


    /**
     * Get the default event type from the EventTypes activity
     *
     * @return default event type
     */
    private String getDefaultEventType()
    {
        Intent this_intent = getIntent();
        int event_type     = this_intent.getIntExtra(
                ActivityEventTypes.EVENT_TYPE, ActivityEventTypes.DEFAULT_EVENT_TYPE);

        return ActivityEventTypes.CMAActivityTypes[event_type];
    }


    /**
     * Verifies email settings have been specified
     *
     * @param dataCMAEmail encapsulates current email settings
     * @return true if local email settings have been specified
     */
    private Boolean checkEmailSettings(DataCMAEmail dataCMAEmail)
    {
        Log.i(TAG, "checkEmailSettings()");
        Boolean settings_initialized = true;
        String  error_msg            = "NONE";

        if(dataCMAEmail.mEmailInit.equals(getString(R.string.boolean_false)))
        {
            // Email Initialized: general setting
            error_msg = "ERROR - email settings not initialized";
            settings_initialized = false;
        }

        if(dataCMAEmail.mEmailTo.equals(getString(R.string.settings_email_to_def)))
        {
            // Email To: send email to the chapter secretary
            error_msg = "ERROR - chapter secretary not specified";
            settings_initialized = false;
        }

        if(dataCMAEmail.mEmailAddr.equals(getString(R.string.settings_email_addr_def)))
        {
            // EMail Address: do not send this email to me please
            error_msg = "ERROR - chapter secretary email address not specified";
            settings_initialized = false;
        }

        if(dataCMAEmail.mEmailFrom.equals(getString(R.string.settings_email_from_def)))
        {
            // Email From
            error_msg = "ERROR - email from not specified";
            settings_initialized = false;
        }


//        if(dataCMAEmail.mEmailSubject.equals(getString(R.string.settings_email_subject_def)))
//        {
//            // Email Subject: OK to use default CMA Activity Report
//            error_msg = "ERROR - email subject not specified";
//            settings_initialized = false;
//        }

        // display an error message
        if(settings_initialized == false)
        {
            Log.i(TAG, "checkEmailSettings(): " +
                    "WARNING - email settings not initialized: " + error_msg);
            Toast.makeText(this, error_msg, Toast.LENGTH_LONG).show();
        }

        return settings_initialized;
    }


    /**
     * Builds CMA activity report and EMails it to the location specified in
     * the settings dialog.  Note that the Intent Extras are already defined
     * for send mail.
     */
    private void emailActivityReport()
    {
        // get email settings and verify they have been set
        DataCMAEmail data_email = new DataCMAEmail(this);
        Log.i(TAG, "emailActivityReport(): email settings: " + data_email.toString());

        if(checkEmailSettings(data_email) == false)
        {
            // display settings activity
            Intent settings_intent = new Intent(getApplicationContext(),
                    ActivitySettings.class);
            startActivity(settings_intent);
        }
        else
        {

            // get report information and generate report text from widgets
            CMAActivityInfo activity_info = new CMAActivityInfo();
            String activity_report = activity_info.buildActivityReport();
            Log.i(TAG, "emailActivityReport(): report = " + activity_report);

            // send the email
            Intent send_email = new Intent(Intent.ACTION_SEND);
            send_email.setType(("message/rfc822"));
            send_email.putExtra(Intent.EXTRA_EMAIL, new String[]{data_email.mEmailAddr});
            send_email.putExtra(Intent.EXTRA_SUBJECT, data_email.mEmailSubject);
            send_email.putExtra(Intent.EXTRA_TEXT, activity_report);

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
        // classes to hold, save, and recall activity data
        DataCMAActivity mDataCMAActivity;
        DataCMAEmail    mDataCMAEmail;

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
            mDataCMAEmail    = new DataCMAEmail(context);

            // get/initialize the widget identifiers
            mEditViewEventName      = findViewById((R.id.editTextEventName));
            mTextViewEventType      = findViewById((R.id.textViewEventType));
            mTextViewEventDate      = findViewById((R.id.textViewEventDate));
            mEditTextAttend         = findViewById((R.id.editTextEventAttend));
            mEditTextSalvations     = findViewById((R.id.editTextSalvations));
            mEditTextRededications  = findViewById((R.id.editTextRededications));
            mEditTextOtherMinistery = findViewById((R.id.editTextOther));
            mEditTextComments       = findViewById((R.id.editTextComment));

            // get current widget data
            getCMAActivityData();
        }


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


        /**
         * Clears CMA Activity data by setting everything in the display to its
         * default value.  These values are not written to storage
         */
        public void clearCMAActivityData()
        {
            Context app_context = getApplicationContext();

            mEditViewEventName.setText(getString(R.string.edittext_def_event_name));
            mTextViewEventType.setText(getString(R.string.event_type));
            mTextViewEventDate.setText(getString(R.string.event_date));
            mEditTextAttend.setText(getString(R.string.edittext_def_attend));
            mEditTextSalvations.setText(getString(R.string.edittext_def_salvations));
            mEditTextRededications.setText(getString(R.string.edittext_def_rededications));
            mEditTextOtherMinistery.setText(getString(R.string.event_name));
            mEditTextComments.setText(getString(R.string.event_name));

            Log.i(TAG, "clearCMAActivityData(): ");
        }


        /***********************************************************************
        /**
         * Builds activity report text to be emailed
         * @return  activity report text to be emailed
         */
        public String buildActivityReport()
        {
            String activity_report =
                    "From = "               + mDataCMAEmail.mEmailFrom        +
                    ", \nEvent = "          + mDataCMAActivity.mEventName     +
                    ", \nEvent Type = "     + mDataCMAActivity.mEventType     +
                    ", \nEvent Date = "     + mDataCMAActivity.mEventDate     +
                    ", \nCMA Attendance = " + mDataCMAActivity.mCMAAttendence +
                    ", \nSalvations = "     + mDataCMAActivity.mSalvations    +
                    ", \nRededications = "  + mDataCMAActivity.mRededications +
                    ", \nOther = "          + mDataCMAActivity.mOtherMinistry +
                    ", \nComment: "         + mDataCMAActivity.mComments;

            return activity_report;
        }

    }   // end internal private class DataReport

}   // end public class ActivityMain extends AppCompatActivity



