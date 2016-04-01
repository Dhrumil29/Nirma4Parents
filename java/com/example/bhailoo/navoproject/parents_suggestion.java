package com.example.bhailoo.navoproject;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class parents_suggestion extends Activity {
    EditText Subject_email;
    EditText Description_parents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_suggestion);
         EditText  Subject_email= (EditText) findViewById(R.id.parents_subject);
         EditText Description_parents  = (EditText) findViewById(R.id.editText5);
        ;
        Button startBtn = (Button) findViewById(R.id.parents_submit);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
            }
        });
    }
    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {"dhrumilpshah29@gmail.com"};
        String[] CC = {""};
        String subject=Subject_email.getText().toString();
        String Description=Description_parents.getText().toString();
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:dhrumilpshah2910@gmail.com"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, Description);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email.","");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(parents_suggestion.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_parents_suggestion, menu);
        return true;
    }
}