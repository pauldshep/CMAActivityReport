package com.sss.android.cmaactivityreport;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Event Types Activity Class
 */
public class ActivityEventTypes extends AppCompatActivity
{
    // identifies the current event type in the array
    public final static String EVENT_TYPE         = "event_id";

    // request code associated with event types
    public final static int    REQUEST_CODE = 1;

    // default event type
    public final static int    DEFAULT_EVENT_TYPE = 1;

    // list of event types
    public final static String[] CMAActivityTypes = new String[]
            {
                    "Other",
                    "Secular",
                    "Outreach",
                    "CMA Fellowship"
            };

    // event type indexes
    public final static Integer EVENT_TYPE_OTHER      = 0;
    public final static Integer EVENT_TYPE_SECULAR    = 1;
    public final static Integer EVENT_TYPE_OUTREACH   = 2;
    public final static Integer EVENT_TYPE_FELLOWSHIP = 3;

    private ListView listViewEventTypes;


    /**
     * Called first when this activity is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // get intent that started this activity
        Intent this_intent = getIntent();
        int event_type     = this_intent.getIntExtra(ActivityEventTypes.EVENT_TYPE, DEFAULT_EVENT_TYPE);

        listViewEventTypes = (ListView)findViewById(R.id.listViewEventType);



        // create adapter and assign to list view
        ArrayAdapter<String> list_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,    // layout for the row
                android.R.id.text1,                     // TextView resource id
                CMAActivityTypes);                     // array of data

        listViewEventTypes.setAdapter(list_adapter);

        // Click listener
        listViewEventTypes.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String itemValue    = (String)listViewEventTypes.getItemAtPosition(position);
                Log.i("ActivityEventTypes", "onClick::itemValue = " + itemValue);

                // go back to the main activity passing the new event id
                //Intent intent = new Intent(getApplicationContext(), ActivityMain.class);
                //intent.putExtra(EVENT_TYPE, position);
                //startActivity(intent);

                Intent intent = new Intent();
                intent.putExtra(EVENT_TYPE, position);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        Button btn_help = findViewById(R.id.button_help);
        btn_help.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                AlertDialog alertDialog =
                    new AlertDialog.Builder(ActivityEventTypes.this).create();
                alertDialog.setTitle("CMA Events");
                alertDialog.setMessage(
                    "Secular - Put on by non-Christian motorcycling groups: rallies, races, poker runs, chapter meetings, bike shows, swap meets, etc\n\n" +
                        "Outreach - Ministry with non-motorcycling groups: church visit, jail, childrens home homeless shelter parade, crusades, etc\n\n" +
                        "Fellowship - CMA events such as: CMA rallies, SOR, RFS, chapter meetings, chapter Bible studies, chapter rides, cookouts, etc");

                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });

    }   // end OnCreate

}   // end public class ActivityEventTypes extends AppCompatActivity
