package com.example.bhailoo.navoproject;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Admin_Result_Insert extends AppCompatActivity {


    String name,roll_no;
    String subject, grade;
    InputStream is = null;
    String result = null;
    String line = null;
    String ce501,ce502,ce503,ce504,ce505;
    int code;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_);

        final EditText roll_number = (EditText) findViewById(R.id.editText7);
        final EditText CE501 = (EditText) findViewById(R.id.editText3);
        final EditText CE502 = (EditText) findViewById(R.id.editText4);
        final EditText CE503 = (EditText) findViewById(R.id.editText8);
        final EditText CE504 = (EditText) findViewById(R.id.editText9);
        final EditText CE505 = (EditText) findViewById(R.id.editText6);
        Button submit = (Button) findViewById(R.id.button3);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                roll_no = roll_number.getText().toString();
                 ce501=CE501.getText().toString();
                 ce502=CE502.getText().toString();
                 ce503=CE503.getText().toString();
                 ce504=CE504.getText().toString();
                 ce505=CE505.getText().toString();

                insert();
            }
        });
    }

    public void insert() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("roll_number",roll_no));
        nameValuePairs.add(new BasicNameValuePair("CE501",ce501));
        nameValuePairs.add(new BasicNameValuePair("Ce502",ce502));
        nameValuePairs.add(new BasicNameValuePair("Ce503",ce503));
        nameValuePairs.add(new BasicNameValuePair("Ce504",ce504));
        nameValuePairs.add(new BasicNameValuePair("Ce505",ce505));


        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(getString(R.string.url)+"/insert.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        } catch (Exception e) {
            Log.e("Fail 1", e.toString());
            Toast.makeText(getApplicationContext(), "Invalid IP Address",
                    Toast.LENGTH_LONG).show();
        }

        try {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            sb.append(roll_no + "\n");
            sb.append(ce501 + "\n");
            sb.append(ce502 + "\n");
            sb.append(ce503 + "\n");
            sb.append(ce504 + "\n");
            sb.append(ce505);


          /*  while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }*/

            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");
        } catch (Exception e) {
            Log.e("Fail 2", e.toString());
        }
        try
        {
            JSONObject json_data = new JSONObject(result);
            code=(json_data.getInt("code"));

            if(code==1)
            {
                Toast.makeText(getBaseContext(), "Inserted Successfully",
                        Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getBaseContext(), "Sorry, Try Again",
                        Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin__result__insert, menu);
        return true;
    }
}
