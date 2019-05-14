package com.sss.android.cmaactivityreport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Creates and displays the About activity for the CMA activity report
 * application
 */
public class ActivityAbout extends AppCompatActivity
{
    private final static String TAG = "ActivityAbout";
    private Button mEmailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mEmailButton = findViewById(R.id.button_about_email);
        mEmailButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // send the email to me
                String my_email_addr = getString(R.string.activity_about_my_email_addr);
                String email_subject = getString(R.string.activity_about_email_subject);
                String email_text    = getString(R.string.activity_about_email_text);

                Intent send_email = new Intent(Intent.ACTION_SEND);
                send_email.setType(("message/rfc822"));
                send_email.putExtra(Intent.EXTRA_EMAIL, new String[]{my_email_addr});
                send_email.putExtra(Intent.EXTRA_SUBJECT, email_subject);
                send_email.putExtra(Intent.EXTRA_TEXT, email_text);

                try
                {
                    startActivity(Intent.createChooser(send_email,
                            "Send Activity Report Issue"));
                }
                catch(android.content.ActivityNotFoundException ex)
                {
                    Log.e(TAG, "ERROR: no email clients installed");
//                    Toast.makeText(
//                        this, "WARNING: there are no email clients installed.",
//                        Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}   // end public class ActivityAbout extends AppCompatActivity
