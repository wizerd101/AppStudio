package com.example.bigmac.mr_potato_head;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;



public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    public void setPotato(ImageView imageView) {
        ImageView Potato = (ImageView) findViewById(R.id.potatoBody);
        Potato.setImageResource(R.drawable.body);


    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.checkbox_eyes:
                if (checked) {
                    ImageView part = (ImageView) findViewById(R.id.potatoEyes);
                    part.setVisibility(View.VISIBLE);

                } else {
                    ImageView part = (ImageView) findViewById(R.id.potatoEyes);

                    part.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.checkbox_arms:
                if (checked) {
                    ImageView part = (ImageView) findViewById(R.id.potatoArms);
                    part.setVisibility(View.VISIBLE);

                } else {
                    ImageView part = (ImageView) findViewById(R.id.potatoArms);

                    part.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.checkbox_ears:
                if (checked) {
                    ImageView part = (ImageView) findViewById(R.id.potatoEars);
                    part.setVisibility(View.VISIBLE);

                } else {
                    ImageView part = (ImageView) findViewById(R.id.potatoEars);

                    part.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.checkbox_eyebrows:
                if (checked) {
                    ImageView part = (ImageView) findViewById(R.id.potatoEyebrows);
                    part.setVisibility(View.VISIBLE);

                } else {
                    ImageView part = (ImageView) findViewById(R.id.potatoEyebrows);

                    part.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.checkbox_glasses:
                if (checked) {
                    ImageView part = (ImageView) findViewById(R.id.potatoGlasses);
                    part.setVisibility(View.VISIBLE);

                } else {
                    ImageView part = (ImageView) findViewById(R.id.potatoGlasses);

                    part.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.checkbox_hat:
                if (checked) {
                    ImageView part = (ImageView) findViewById(R.id.potatoHat);
                    part.setVisibility(View.VISIBLE);

                } else {
                    ImageView part = (ImageView) findViewById(R.id.potatoHat);

                    part.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.checkbox_mouth:
                if (checked) {
                    ImageView part = (ImageView) findViewById(R.id.potatoMouth);
                    part.setVisibility(View.VISIBLE);

                } else {
                    ImageView part = (ImageView) findViewById(R.id.potatoMouth);

                    part.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.checkbox_mustache:
                if (checked) {
                    ImageView part = (ImageView) findViewById(R.id.potatoMustache);
                    part.setVisibility(View.VISIBLE);

                } else {
                    ImageView part = (ImageView) findViewById(R.id.potatoMustache);

                    part.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.checkbox_nose:
                if (checked) {
                    ImageView part = (ImageView) findViewById(R.id.potatoNose);
                    part.setVisibility(View.VISIBLE);

                } else {
                    ImageView part = (ImageView) findViewById(R.id.potatoNose);

                    part.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.checkbox_shoes:
                if (checked) {
                    ImageView part = (ImageView) findViewById(R.id.potatoShoes);
                    part.setVisibility(View.VISIBLE);

                } else {
                    ImageView part = (ImageView) findViewById(R.id.potatoShoes);

                    part.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }
}
