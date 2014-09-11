package com.example.hariskljajic.customadapterdemo.Activites;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hariskljajic.customadapterdemo.Adapters.CustomAdapter;
import com.example.hariskljajic.customadapterdemo.R;

import java.util.ArrayList;


public class MainActivity extends ListActivity {

    ArrayList<String> nameList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameList = new ArrayList<String>();
        nameList.add("Haris");
        nameList.add("Joachim");
        nameList.add("Daniel");
        nameList.add("Alex");
        nameList.add("Kim");
        nameList.add("Nisse");
        nameList.add("Emil");
        nameList.add("Magnus");
        nameList.add("Tobias");

        CustomAdapter customAdapter = new CustomAdapter(this, nameList);

        setListAdapter(customAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
