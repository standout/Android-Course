package com.example.hariskljajic.customadapter;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MyActivity extends ListActivity implements AdapterView.OnItemLongClickListener {

    ArrayList<String> nameList;
    ArrayAdapter<String> nameAdapter;

    EditText nameEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        nameEt = (EditText) findViewById(R.id.nameEt);

        nameList = new ArrayList<String>();
        nameList.add("Haris");
        nameList.add("Niklas");
        nameList.add("David");

        nameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);

        setListAdapter(nameAdapter);

        getListView().setOnItemLongClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        Toast.makeText(this, nameAdapter.getItem(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        String name = nameAdapter.getItem(position);

        nameList.remove(name);
        nameAdapter.notifyDataSetChanged();

        return false;
    }


    public void onClickAdd(View view) {
        String name = nameEt.getText().toString();
        nameList.add(name);

        nameAdapter.notifyDataSetChanged();
    }


}
