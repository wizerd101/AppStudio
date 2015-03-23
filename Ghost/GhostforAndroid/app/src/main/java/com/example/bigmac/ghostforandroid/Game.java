package com.example.bigmac.ghostforandroid;


import android.app.Activity;
import android.graphics.Typeface;


import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bastiaan Waanders on 24-02-15.
 */
public class Game{

    String[] allowedCharacters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q"
            ,"r","s","t","u","v","w","x","y","z"};
    StringBuffer word = new StringBuffer();
    String letter = "";
    int activePlayer = 1;
    Players p = new Players();


    public String getWord(){
        //returns the word
        return word.toString();
    }

    public void setWord(String letters){
        // clears the word first, after that safes the letters passed as argument
        word.setLength(0);
        word.append(letters);
    }

    public int getActivePlayer(){
        //returns the active player
        return this.activePlayer;
    }

    public int setActivePlayer(int active){
        return activePlayer = active;
    }

    public boolean appendLetter(Activity activity){
        //Returns true if the letter is appended to the word and false if it did not.
        boolean success = false;
        getLetter(activity);

        if(allowedInput(letter)){
            setLetter(letter);
            success = true;
        }
        return success;
    }

    public void getLetter(Activity activity){
        //Returns the letter of the letter input from the EditText field.
            EditText inputLetter = (EditText) activity.findViewById(R.id.wordInput);

            letter = inputLetter.getText().toString().toLowerCase();

    }

    public boolean allowedInput(String inputLetter) {
        //Returns true if the input character is allowed and false when it is not
        boolean allowed = false;

        for (String allowedLetter : allowedCharacters) {
            if (inputLetter.equals(allowedLetter)) {
                allowed = true;
            }
        }
        return allowed;
    }

    public StringBuffer setLetter(String letter){
        //appends the letter to the word.
        return word.append(letter);
    }

    public boolean checkMatchDictionary(){

        return Dictionary.matchWord(word.toString());
    }

    public void setPlayerStyle(Activity activity){
        //Changes the active player to textstyle to normal and unactive player to bold

        TextView player1 = (TextView) activity.findViewById(R.id.player1);
        TextView player2 = (TextView) activity.findViewById(R.id.player2);

        if(activePlayer == 1){
            player1.setTypeface(Typeface.create(player1.getTypeface(), Typeface.NORMAL));
            player2.setTypeface(Typeface.create(player2.getTypeface(), Typeface.BOLD));

        }else{
            player1.setTypeface(Typeface.create(player1.getTypeface(), Typeface.BOLD));
            player2.setTypeface(Typeface.create(player2.getTypeface(), Typeface.NORMAL));
        }

        changePlayer();
    }

    public int changePlayer(){
        if(activePlayer == 1){
            activePlayer = 2;
        }else{
            activePlayer = 1;
        }
        return activePlayer;
    }

    public void updateLayout(Activity activity) {
        TextView textViewWord = (TextView) activity.findViewById(R.id.textViewWord);
        EditText inputLetter = (EditText) activity.findViewById(R.id.wordInput);

        textViewWord.setText(word);
        inputLetter.setText("");

        setPlayerStyle(activity);
    }
    public void incrementScoreWinner(Player winner){
        ArrayList<Player> players = p.allPlayers();
        for(int x = 0; x < players.size(); x++){
            if(players.get(x).getId().equals(winner.getId())){
                players.get(x).incrementScore();
            }
        }
    }

}



