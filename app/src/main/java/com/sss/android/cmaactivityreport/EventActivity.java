package com.sss.android.cmaactivityreport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class EventActivity extends AppCompatActivity
{
    // identifies the current event type in the array
    public final static String EVENT_TYPE         = "event_id";

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
        int event_type     = this_intent.getIntExtra(EventActivity.EVENT_TYPE, DEFAULT_EVENT_TYPE);

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
                Log.i("EventActivity", "onClick::itemValue = " + itemValue);

                // go back to the main activity
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("event_id", position);
                startActivity(intent);
            }
        });
    }   // end OnCreate

}   // end public class EventActivity extends AppCompatActivity
