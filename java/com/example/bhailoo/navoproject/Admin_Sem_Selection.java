package com.example.bhailoo.navoproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;


public class Admin_Sem_Selection extends Activity {
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_sem_selection);

//        Button button = (Button)findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, MainActivity.class);
//                startActivity(i);
//            }
//        });
        RadioGroup rg=(RadioGroup)findViewById(R.id.Sem_Selection);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.radioButton7:
                    {
                         break;
                    }
                    case R.id.radioButton8:
                    {
                        Intent i=new Intent(Admin_Sem_Selection.this,Result_table.class);
                        i.putExtra("user",user);
                        startActivity(i);
                        break;
                    }
                    case R.id.radioButton9:
                    {
                        Intent i=new Intent(Admin_Sem_Selection.this,parents_review.class);
                        startActivity(i);
                        i.putExtra("user",user);
                        break;
                    }
                    case R.id.radioButton4:
                    {
                        Intent i=new Intent(Admin_Sem_Selection.this,parents_suggestion.class);
                        startActivity(i);
                        i.putExtra("user",user);
                        break;
                    }
                }

            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
