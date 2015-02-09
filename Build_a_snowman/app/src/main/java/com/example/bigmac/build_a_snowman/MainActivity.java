package com.example.bigmac.build_a_snowman;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    // declare variables
    private EditText editTextTemperature, editTextCentimeters;
    private RadioButton radioButtonMad;
    private RadioGroup radioGroupMood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // give variables there ID
        editTextCentimeters = (EditText) findViewById(R.id.editTextCentimeters);
        editTextTemperature = (EditText) findViewById(R.id.editTextTemperature);
        radioButtonMad = (RadioButton) findViewById(R.id.Mad);
        radioGroupMood = (RadioGroup) findViewById(R.id.RadioMood);

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

    public void doMagic(View view) {
        //Get editText inputs, convert them into integers
        String snow = String.valueOf(editTextCentimeters.getText());
        String temp = String.valueOf(editTextTemperature.getText());
        int tempInt =Integer.parseInt(temp);
        int snowInt =Integer.parseInt(snow);
        //Check selected radiobutton Id
        int selectedMood = radioGroupMood.getCheckedRadioButtonId();
        //If temperature is above 0 degrees celsius it's to hot to build a snowman
        //If temperature is below -15 degrees celsius it's to cold to build a snowman
        //If the snow is below 10 cm, there is not enough snow to build a snowman
        //If the snow is above 150 cm, there is too much snow, doors wont open
        //Otherwise we can build a snowman, but if Elsa is mad Anna has to build the snowman herself
        if ( tempInt > 0 ){
            String answerTooHot = "Too bad it's to warm to build a snowman today.";
            Toast.makeText(this, answerTooHot, Toast.LENGTH_LONG).show();
        }
        else if ( tempInt < -15){
            String answerTooCold = "Too bad it's to cold to build a snowman today.";
            Toast.makeText(this, answerTooCold, Toast.LENGTH_LONG).show();
        }
        else if ( snowInt < 10 ){
            String answerNoSnow = "Too bad there is not enough snow to build a snowman today.";
            Toast.makeText(this, answerNoSnow, Toast.LENGTH_LONG).show();
        }
        else if ( snowInt > 150 ){
            String answerMuchSnow = "There is too much snow, you can't open the castle doors to " +
                    "go outside.";
            Toast.makeText(this, answerMuchSnow, Toast.LENGTH_LONG).show();
        }
        else if (selectedMood == radioButtonMad.getId()){
            String answerYes = "Yes we can go build a snowman but Elsa is mad so we have to build " +
                    "it alone :(.";
            Toast.makeText(this, answerYes, Toast.LENGTH_LONG).show();
        }
        else{
            String answerYes = "Yes we can go build a snowman together with Else today!";
            Toast.makeText(this, answerYes, Toast.LENGTH_LONG).show();
        }
    }
}
