package com.example.bigmac.ghostforandroid;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;


import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Bastiaan Waanders on 24-02-15.
 */
public class Game extends ActionBarActivity {

    ArrayList<Integer> storeLetterId = new ArrayList<Integer>();
    int Placeholder = -1;
    StringBuffer word = new StringBuffer();
    int setId = 0;
    int activePlayer = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        Dictionary dictionary = new Dictionary(getApplicationContext());
        Dictionary matchWord  = new Dictionary(getApplicationContext());
    }



    /* uncommented for menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main.xml; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    /*get input from inputLetter - done
        get full array of letters - done
        check if added letter forms word
        if so say lose, if not next player
        set active player text bold
        set edit text to text view
        add next text edit - done
        update array
        */
    public void game (View view){

        addLetter();

        if (!checkMatchDictionary()){
            //show lose dialog
            Context context = getApplicationContext();
            CharSequence loseText = "Too bad you lost";
            int duration = Toast.LENGTH_SHORT;

            Toast lose = Toast.makeText(context, loseText , duration);
            lose.show();
        }
        else{
            //change player
            //change Edit text in Text view
            //add new edittext
            addEditText();
        }
    }

    public int setIdTextEdit(){

        setId ++;

        storeLetterId.add(setId);

        return setId;
    }

    public int getPlaceholderId(){

        int getId = storeLetterId.get(Placeholder);

        return getId;
    }


    public String getLetter(){
        //returns letter which was inputted by the player
        EditText inputLetter = (EditText) findViewById(getPlaceholderId());

        return inputLetter.getText().toString().toLowerCase();
    }
    /*
    public StringBuffer deleteLetter(){
        //deletes the last letter from string
        int length  = word.length();
        word.deleteCharAt(length);

        return word;

    }
    */
    public boolean checkMatchDictionary(){

        return Dictionary.matchWord(word.toString());
    }

    public StringBuffer addLetter(){

        String letter;
        if(storeLetterId.isEmpty()){
            EditText firstInputLetter = (EditText) findViewById(R.id.wordInput0);
            return word.insert(0, firstInputLetter.getText().toString().toLowerCase());
        }else {
            letter = getLetter();
            return word.append(letter);
        }

    }

    public void setPlayerStyle(){
        if(activePlayer == 1){
            TextView player1 = (TextView) findViewById(R.id.player1);
            TextView player2 = (TextView) findViewById(R.id.player2);
            player1.setTypeface(Typeface.create(player1.getTypeface(), Typeface.NORMAL));
            player2.setTypeface(Typeface.create(player2.getTypeface(), Typeface.BOLD));
            changePlayer();

        }else{
            TextView player1 = (TextView) findViewById(R.id.player1);
            TextView player2 = (TextView) findViewById(R.id.player2);
            player1.setTypeface(Typeface.create(player1.getTypeface(), Typeface.BOLD));
            player2.setTypeface(Typeface.create(player2.getTypeface(), Typeface.NORMAL));
            changePlayer();
        }

    }

    public int changePlayer(){
        if(activePlayer == 1){
            activePlayer = 2;
        }else{
            activePlayer = 1;
        }
        return activePlayer;

    }

    public void addEditText() {
        //adds a new EditText to layout textEditLayout
        LinearLayout letterLayout =  (LinearLayout) findViewById(R.id.textEditLayout);
        EditText letter = new EditText(this);

        LinearLayout.LayoutParams rules = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        rules.setMargins(10, 0, 0, 0);
        letter.setText("");
        letter.setTextSize(40);
        letter.setId(setIdTextEdit());
        letterLayout.addView(letter, rules);

        setPlayerStyle();
        Placeholder = Placeholder + 1;


    }
    /*
    if (inputLetter.getText().toString().equals("")) {
        String editTextValue = inputLetter.getText().toString();
        inputLetter.setText(editTextValue);
    }
    else {
        displayLetter.setText(inputLetter.getText().toString());
        letterLayout.addView(letter, rules);
        */
    }



