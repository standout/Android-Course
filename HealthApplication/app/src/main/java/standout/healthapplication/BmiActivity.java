package standout.healthapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class BmiActivity extends Activity {

    private EditText weightEt;
    private EditText lengthEt;
    private TextView resultTv;
    private Button calculateBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        weightEt = (EditText)this.findViewById(R.id.weightEditText);
        lengthEt = (EditText)this.findViewById(R.id.lengthEditText);
        resultTv = (TextView)this.findViewById(R.id.resultTextView);

        calculateBtn = (Button)this.findViewById(R.id.calculateButton);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bmi, menu);
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

    public void onCalculate(View view) {
        double weightValue = Double.parseDouble(weightEt.getText().toString());
        double lengthValue = Double.parseDouble(lengthEt.getText().toString());

        // Calls calculate method to get an BMI result
        double resultValue = calculateBmi(weightValue, lengthValue);

        resultTv.setText(String.valueOf(resultValue));

    }

    /*
    Calculates BMI
    @returns resultValue
     */
    public double calculateBmi(double weight, double length){
        double result = Math.round(weight / (length * length));
        return result;
    }
}
