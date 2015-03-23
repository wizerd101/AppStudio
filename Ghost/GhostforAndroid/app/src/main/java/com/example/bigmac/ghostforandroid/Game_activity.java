package com.example.bigmac.ghostforandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.*;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Bastiaan Waanders on 17-03-15.
 */
public class Game_activity extends ActionBarActivity {

    Game game;
    ArrayList<Player> activePlayers = new ArrayList<Player>();
    Players p = new Players();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        Bundle players = getIntent().getExtras();
        String language = players.getString("Language");

        Dictionary dictionary = new Dictionary(getApplicationContext(), language);

        setSelectedPlayers();

        game =  new Game();

        //Check if there is any data to be retrieved from the TextView
        TextView textViewWord = (TextView) findViewById(R.id.textViewWord);
        if (savedInstanceState != null) {

            String word = savedInstanceState.getString("wordSaved");
            textViewWord.setText(word);
            game.setWord(word);

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
    //Save the characters from TextView to make sure that when th devices orientation changes
    // no data will be lost.
        outState.putString("wordSaved",
                game.getWord());
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_game, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.backToStart:
                toStart(getApplicationContext());
                return true;
            case R.id.restartGame:
                restartGame();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void toStart(Context context) {
        Intent backToStart = new Intent(context, Start.class);
        startActivity(backToStart);
    }

    public void restartGame(){
        game.setWord("");
        game.setActivePlayer(2);
        game.updateLayout(this);
        restartMessage();
    }

    public void setSelectedPlayers(){

        TextView player1TextView  = (TextView) findViewById(R.id.player1);
        TextView player2TextView  = (TextView) findViewById(R.id.player2);

        Bundle players = getIntent().getExtras();

        String player1Id = players.getString("PlayerId1");
        String player2Id = players.getString("PlayerId2");

        for(Player player : p.allPlayers()){
            if(player.getId().equals(player1Id)){
                activePlayers.add(player);
                player1TextView.setText(player.getName());
                player1TextView.setTextColor(Color.BLACK);
            }else if(player.getId().equals(player2Id)){
                activePlayers.add(player);
                player2TextView.setText(player.getName());
                player2TextView.setTextColor(Color.BLACK);
            }
        }

    }

    public void game (View view){

        if(!game.appendLetter(this)){
            invalidInput();
        }
        else if (!game.checkMatchDictionary()){
            gameOver();
        }
        else{
            game.updateLayout(this);
        }
    }

    public void invalidInput(){
        Context context = getApplicationContext();
        CharSequence invalidInputMessage = "Sorry, you entered a character which is not allowed, try again!";
        int duration = Toast.LENGTH_SHORT;

        Toast error = Toast.makeText( context, invalidInputMessage , duration);
        error.show();
    }

    public void restartMessage(){
        Context context = getApplicationContext();
        CharSequence restartGame = "The game has been restarted";
        int duration = Toast.LENGTH_SHORT;

        Toast restart = Toast.makeText( context, restartGame , duration);
        restart.show();
    }

    public void gameOver(){
        Player winner;

        if(game.getActivePlayer() == 1){
            winner = activePlayers.get(1);
        }else{
            winner = activePlayers.get(0);
        }

        for(Player player : p.allPlayers()){
            if(player.getId().equals(winner.getId())){
                game.incrementScoreWinner(player);
            }
        }

        AlertDialog gameEnd = Dialog.gameEnd(this, winner, activePlayers);
        gameEnd.show();

    }



}
