package com.example.hariskljajic.demov2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener,
        View.OnLongClickListener,
        AdapterView.OnItemSelectedListener,
        RatingBar.OnRatingBarChangeListener {

    // Adapters
    ArrayAdapter<CharSequence> studentAdapter;
    // Private variables
    private String currentStudent;
    // Button variables
    Button testBtn;
    Button testBtnLong;
    // RadioButton variables
    RadioButton radioBtnHamburger;
    RadioButton radioBtnHotdog;
    // Spinner variables
    Spinner studentSpinner;
    // RatingBar variables
    RatingBar nameRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting references from XML
        testBtn = (Button) findViewById(R.id.testBtn);
        testBtnLong = (Button) findViewById(R.id.testBtnLong);
        radioBtnHamburger = (RadioButton) findViewById(R.id.radioButtonHamburger);
        radioBtnHotdog = (RadioButton) findViewById(R.id.radioButtonHotdog);
        studentSpinner = (Spinner)findViewById(R.id.studentSpinner);
        nameRatingBar = (RatingBar) findViewById(R.id.nameRatingBar);

        // Setting our clickListeners
        testBtn.setOnClickListener(this);
        testBtnLong.setOnLongClickListener(this);
        radioBtnHamburger.setOnClickListener(this);
        radioBtnHotdog.setOnClickListener(this);
        studentSpinner.setOnItemSelectedListener(this);
        nameRatingBar.setOnRatingBarChangeListener(this);

        // Populating the Spinner with an adapter and the adapter with an String XML array from resources
        studentAdapter = ArrayAdapter.createFromResource(this, R.array.studentArray, android.R.layout.simple_spinner_item);
        studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentSpinner.setAdapter(studentAdapter);
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
        if (id == R.id.action_refresh) {
            // Action does nothing!
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    onClick listener implemented from View.OnClickListener.
    Triggering every time a user clicks a registered button.
     */
    @Override
    public void onClick(View v) {
        // Using a switch to filter which widget was click as all widgets registered uses same method.
        // Using Toast class to display a text. It takes a context, text message and duration of message shown.
        switch (v.getId()){
            case R.id.radioButtonHamburger:
                Toast.makeText(this, "Hamburgare klick", Toast.LENGTH_SHORT).show();
                break;
            case R.id.radioButtonHotdog:
                Toast.makeText(this, "Korv klick", Toast.LENGTH_SHORT).show();
                break;
            case R.id.testBtn:
                Toast.makeText(this, "Vanligt klick", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    /*
      onLongClick listener implemented from View.OnLongClickListener
      It does not need a filter as we only have one widget registered for it.
     */
    @Override
    public boolean onLongClick(View v) {
        // Using Toast class to display a text. It takes a context, text message and duration of message shown.
        Toast.makeText(this, "Långt klick", Toast.LENGTH_SHORT).show();
        return true;
    }
    /*
      onItemSelected listener implemented from AdapterView.OnItemSelectedListener
      It does not need a filter as we only have one widget registered for it.
      Method takes a position and id among other params. We are using position to extract the item selected from our Adapter.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CharSequence studentName = studentAdapter.getItem(position);
        currentStudent = String.valueOf(studentName);
        Toast.makeText(this, studentName, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Does nothing...
    }
    /*
     onRatingChanged listener implemented from RatingBar.OnRatingBarChangeListener
     It does not need a filter as we only have one registered.
     Method takes however a rating among other params which we use to know which rating user clicked.
     */
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        Toast.makeText(this, currentStudent + " " + String.valueOf(rating), Toast.LENGTH_SHORT).show();
    }
}
