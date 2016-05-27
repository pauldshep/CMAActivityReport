package com.sss.android.cmaactivityreport;

import android.content.ClipData;
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
import android.widget.TextView;
import android.widget.Toast;


/**
 * CMA Activity Report Application entry point.
 */
public class MainActivity extends AppCompatActivity
{

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

        Log.i("MainActivity", "::onCreate()");

        // configure the action bar and handle its messages
        ActionBar action_bar = getSupportActionBar();
        action_bar.setTitle(R.string.actionbar_title);

        // configure the event type display
        TextView text_view_type = (TextView)findViewById(R.id.textViewType);
        Intent this_intent      = getIntent();
        int event_type          = this_intent.getIntExtra(EventActivity.EVENT_TYPE, EventActivity.DEFAULT_EVENT_TYPE);
        text_view_type.setText(EventActivity.CMAActivityTypes[event_type]);

        // configure the date display
        TextView text_view_date = (TextView)findViewById(R.id.textViewDate);
        UtilDate util_date      = new UtilDate();
        text_view_date.setText(util_date.toString());


        ////////////////////////////////////////////////////////////////////////
        /////////////////////////// MESSAGE HANDLERS ///////////////////////////
        ////////////////////////////////////////////////////////////////////////

        //----------------------------------------------------------------------
        // Event Type TextView
        //----------------------------------------------------------------------
        TextView text_view_type_title = (TextView)findViewById(R.id.textViewTypeTitle);
        text_view_type_title.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Log.i("MainActivity", "textview type selected");
                Intent intent = new Intent(getApplicationContext(), EventActivity.class);
                startActivity(intent);
            }
        });


        text_view_type.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Log.i("MainActivity", "textview type selected");
                Intent intent = new Intent(getApplicationContext(), EventActivity.class);
                startActivity(intent);
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
                Log.i("MainActivity", "Date onClick() start");

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
                        Log.i("MainActivity", "specified date = " + date_str);
                        TextView date_text_view = (TextView)findViewById(R.id.textViewDate);
                        date_text_view.setText(date_str);
                    }
                };

                UtilDate util_date = new UtilDate();
                date_picker.setArguments(util_date.buildBundle());
                date_picker.show(getFragmentManager(), "datePicker");

                TextView date_text_view = (TextView)findViewById((R.id.textViewDate));
                String date_fr_picker   = date_picker.toString();
                Log.d("MainActivity", "Date onClick() end " + date_fr_picker);
            }
        });

        text_view_date.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.i("MainActivity", "Date onClick() start");

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
                        Log.i("MainActivity", "specified date = " + date_str);
                        TextView date_text_view = (TextView)findViewById(R.id.textViewDate);
                        date_text_view.setText(date_str);
                    }
                };

                UtilDate util_date = new UtilDate();
                date_picker.setArguments(util_date.buildBundle());
                date_picker.show(getFragmentManager(), "datePicker");

                TextView date_text_view = (TextView)findViewById((R.id.textViewDate));
                String date_fr_picker   = date_picker.toString();
                Log.d("MainActivity", "Date onClick() end " + date_fr_picker);
            }   // end public void onClick(View v)
        });


        //----------------------------------------------------------------------
        // Send Button
        //----------------------------------------------------------------------
        Button button_send = (Button)findViewById(R.id.button_send);
        button_send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
              Log.i("cmarpt", "send button selected");

                getActivityReportInfo();
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
        switch(item.getItemId())
        {
            case R.id.action_bar_settings:
                // display settings activity
                Toast.makeText(this, "Action Bar Settings Menu Item",
                        Toast.LENGTH_SHORT).show();
                Log.i("MainActivity", "textview type selected");
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
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
                Toast.makeText(this, "Action Bar About Menu Item",
                        Toast.LENGTH_SHORT).show();
                break;

            default:
                Log.e("MainActivity",
                      "action bar menu item not handled = " + item.getItemId());
                break;
        }

        return true;
    }   // end public boolean onOptionsItemSelected(MenuItem item)


    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////// PRIVATE MEMBER FUNCTIONS /////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    /**
     * get CMA activity report info
     */
    private void getActivityReportInfo()
    {
        CMAActivityInfo activity_info = new CMAActivityInfo(this);
        activity_info.getInfo();
        Log.i("CMAActivityInfo", activity_info.toString());
    }


}   // end public class MainActivity extends AppCompatActivity



