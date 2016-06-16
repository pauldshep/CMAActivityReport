package com.sss.android.cmaactivityreport;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


/**
 * CMA Activity Report Application entry point.
 */
public class ActivityMain extends AppCompatActivity
{
    private final static String TAG        = "ACtivityMain";
    private final static String EVENT_TYPE = "event_type";
    private final static String EVENT_DATE = "event_date";

    // widget variables
    private TextView mTextViewEventType;
    private TextView mTextViewEventDate;

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

        // lookup widgets
        mTextViewEventType = (TextView)findViewById(R.id.textViewEventType);
        mTextViewEventDate = (TextView)findViewById(R.id.textViewEventDate);

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
        Intent intent;

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
                break;

            case R.id.action_bar_recall:
                Toast.makeText(this, "ActionBar Recall Menu Item",
                        Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_bar_clear:
                Toast.makeText(this, "Action Bar Clear Menu Item",
                        Toast.LENGTH_SHORT).show();
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
        DataSettings data_settings = new DataSettings(this);
        Log.i(TAG, "Report Settings: " + data_settings);

        // get report information and generate report text
        DataReport data_report     = new DataReport();
        String     activity_report = data_report.buildActivityReport();
        Log.i(TAG, "Activity Report: " + activity_report);

        // send the email
        Intent send_email = new Intent(Intent.ACTION_SEND);
        send_email.setType(("message/rfc822"));
        send_email.putExtra(Intent.EXTRA_EMAIL,   new String[] {data_settings.mEmailAddr});
        send_email.putExtra(Intent.EXTRA_SUBJECT, data_settings.mEmailSubject);
        send_email.putExtra(Intent.EXTRA_TEXT,    data_report.toString());

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


    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////// INTERNAL CLASSES //////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Encapsulates this classes dynamic data
     */
    private class DataReport
    {
        public String mEventName;
        public String mEventType;
        public String mEventDate;
        public String mCMAAttend;
        public String mSalvations;
        public String mRededications;
        public String mOtherMinistry;

        // application widgets
        private EditText mEditViewEventName;
        private TextView mTextViewEventType;
        private TextView mTextViewEventDate;
        private EditText mEditTextAttend;
        private EditText mEditTextSalvations;
        private EditText mEditTextRededications;
        private EditText mEditTextOtherMinistery;


        /**
         * Default Constructor.  Get current data from activity widgets
         */
        public DataReport()
        {
            // get the widgets
            mEditViewEventName      = (EditText)findViewById((R.id.editTextEventName));
            mTextViewEventType      = (TextView)findViewById((R.id.textViewEventType));
            mTextViewEventDate      = (TextView)findViewById((R.id.textViewEventDate));
            mEditTextAttend         = (EditText)findViewById((R.id.editTextEventAttend));
            mEditTextSalvations     = (EditText)findViewById((R.id.editTextSalvations));
            mEditTextRededications  = (EditText)findViewById((R.id.editTextRededications));
            mEditTextOtherMinistery = (EditText)findViewById((R.id.editTextOther));

            // get current widget data
            mEventName     = mEditViewEventName.getText().toString();
            mEventType     = mTextViewEventType.getText().toString();
            mEventDate     = mTextViewEventDate.getText().toString();
            mCMAAttend     = mEditTextAttend.getText().toString();
            mSalvations    = mEditTextSalvations.getText().toString();
            mRededications = mEditTextRededications.getText().toString();
            mOtherMinistry = mEditTextOtherMinistery.getText().toString();
        }


        /**
         * Builds activity report text to be emailed
         * @return  activity report text to be emailed
         */
        public String buildActivityReport()
        {
            String activity_report =
                            "\nEvent = "          + mEventName +
                            "\nEvent Type = "     + mEventType +
                            "\nEvent Date = "     + mEventDate +
                            "\nCMA Attendence = " + mCMAAttend +
                            "\nSalvations = "     + mSalvations +
                            "\nRededications = "  + mRededications +
                            "\nOther = "          + mOtherMinistry;

            return activity_report;
        }

    }   // end internal private class DataReport

}   // end public class ActivityMain extends AppCompatActivity



