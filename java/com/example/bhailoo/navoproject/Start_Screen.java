package com.example.bhailoo.navoproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;


public class Start_Screen extends Activity {
String user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start__screen);
        final EditText user_name=(EditText)findViewById(R.id.editText);
        final EditText password=(EditText)findViewById(R.id.editText2);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_name.getText().toString().equalsIgnoreCase("") || password.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(Start_Screen.this, "Enter proper credentials", Toast.LENGTH_LONG).show();
                }else{
                    user = user_name.getText().toString();
                    pass = password.getText().toString();
                    new Verify().execute((Void) null);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start__screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class Verify extends AsyncTask<Void, Void, Void> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog =  new ProgressDialog(Start_Screen.this);
            progressDialog.setMessage("Verifying Credentials");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            String url = getString(R.string.url)+"/login.php";
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            try {
                ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
                list.add(new BasicNameValuePair("user", user));
                list.add(new BasicNameValuePair("pass", pass));
                httpPost.setEntity(new UrlEncodedFormEntity(list));

                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                final String response = httpClient.execute(httpPost, responseHandler);
                Log.d("Response", response);
                if (response.startsWith("Found")) {
                   // Toast.makeText(Start_Screen.this, "Logging As Parent", Toast.LENGTH_LONG).show();
//                    String[] u = response.split(", ");
                    Intent i;
                    i = new Intent(Start_Screen.this, MainActivity.class);
                    i.putExtra("user", user);
                    startActivity(i);
                    finish();}
                    else if(response.startsWith("Admin")){
                           Intent i;
                  //  Toast.makeText(Start_Screen.this, "Logging As Admin", Toast.LENGTH_LONG).show();
                        i=new Intent(Start_Screen.this,Admin_Activity.class);
                       i.putExtra("user",user);
                        startActivity(i);
                    finish();
                    }
                else if(response.startsWith("Faculty"))
                {
                    Intent i;
                    i=new Intent(Start_Screen.this,Activity_faculty.class);
                    Toast.makeText(Start_Screen.this, "Logging As Faculty", Toast.LENGTH_LONG).show();
                    i.putExtra("user",user);
                    startActivity(i);
                    finish();
                }
                 else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Start_Screen.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } catch (Exception e) {
                Log.e("Error", e.toString());
                e.printStackTrace();
            }
            return null;
        }

    }

}
