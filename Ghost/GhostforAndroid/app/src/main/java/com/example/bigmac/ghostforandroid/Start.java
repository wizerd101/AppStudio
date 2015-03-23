package com.example.bigmac.ghostforandroid;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by Bastiaan Waanders on 25-02-15.
 */
public class Start extends ActionBarActivity{

    Menu menu;
    Players p = new Players();

    static ArrayList<Integer> checkBoxIds = new ArrayList<Integer>();
    static ArrayList<Player> selectedPlayers = new ArrayList<Player>();

    private static final int SETTINGS_INFO = 1;

    public static String language = "english";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        checkBoxIds.clear();
        loadPreviousPlayers();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.backToStart:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public static String getLanguage(){
        return language;
    }
    public static ArrayList<Player> getSelectedPlayersArrayList(){
        return selectedPlayers;
    }

    public void startGame(View view){
        //getSelectedPlayers is called to add the checked players to ArrayList selectedPlayers
        //When selectedPlayers contains more then 2 values, there are to many players selected
        //When selectedPlayers contains less then 2 values, there are to few players selected

        getSelectedPlayers();

        Context context = getApplicationContext();
        int duration = android.widget.Toast.LENGTH_SHORT;

        if(selectedPlayers.size() == 2){
            int count = 1;
            //Create Intent for the Game_activity
            //Intents contains the following extras, player names form ArrayList selectedPlayers
            //and the language which by default is set to English.

            Intent startGameIntent = new Intent(this, Game_activity.class);
            for(Player player : selectedPlayers){
                startGameIntent.putExtra("PlayerId" + count, player.getId());
                count++;
            }

            startGameIntent.putExtra("Language", getLanguage());
            startActivity(startGameIntent);

        }else if(selectedPlayers.size() > 2){

            CharSequence message = "You have selected to much selectedPlayers";
            android.widget.Toast toMuchPlayers = android.widget.Toast.makeText(context, message, duration);
            toMuchPlayers.show();

        }else if(selectedPlayers.size() < 2){

            CharSequence message = "You have selected not enough selectedPlayers";
            android.widget.Toast toMuchPlayers = android.widget.Toast.makeText(context, message, duration);
            toMuchPlayers.show();

        }

    }

    public void startSettings(View view){
        //Intent created for going to the Settings screen.

        Intent settingsIntent = new Intent(getApplicationContext(), Settings.class);
        startActivityForResult(settingsIntent, SETTINGS_INFO);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == SETTINGS_INFO){

            updateLanguage();
        }

    }

    public void updateLanguage(){
        //Get the string value from prefDicLang which will contain the chosen language,
        // by default this is set to English

        SharedPreferences languagePref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        language = languagePref.getString("prefDicLang", "english");

    }


    public void getSelectedPlayers(){
        //Clear arrayList containing selectedPlayers from previous games.

        selectedPlayers.clear();

        //Loop trough all the checkboxes to check which players are selected
        for( int setZero = 0 ; setZero < checkBoxIds.size(); setZero = setZero + 1 ) {

            int checkBoxIdLayout = getResources().getIdentifier(""+checkBoxIds.get(setZero),"id", getPackageName());

            CheckBox playerCheckBox = (CheckBox) findViewById(checkBoxIdLayout);

            if(playerCheckBox.isChecked()){
                Player selectedPlayer = p.allPlayers().get(setZero);
                selectedPlayers.add(selectedPlayer);
            }

        }

    }

    public void addPlayer(View view){
        //Add a player name to the checkbox list of the players.
        //Names of players should be longer then three characters,

        EditText playerName = (EditText) findViewById(R.id.newPlayerEditText);
        String name = playerName.getText().toString();

        if(name.length() <= 3 ){

            Context context = getApplicationContext();
            CharSequence loseText = "You need to type in a name, longer then 3 letters!";
            int duration = android.widget.Toast.LENGTH_SHORT;

            android.widget.Toast lose = android.widget.Toast.makeText(context, loseText, duration);
            lose.show();

        }else{
            updatePlayers(p.createPlayer(name));
            playerName.setText("");
        }
    }

    public void updatePlayers(Player player){
        LinearLayout choosePlayerLayout = (LinearLayout) findViewById(R.id.players);

        CheckBox playerCheckbox = new CheckBox(getApplicationContext());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        int playerId = View.generateViewId();


        params.setMargins(0, 10, 0, 0);
        playerCheckbox.setText(player.getName());
        playerCheckbox.setId(playerId);
        playerCheckbox.setChecked(false);
        playerCheckbox.setTextColor(Color.BLACK);
        checkBoxIds.add(playerId);

        choosePlayerLayout.addView(playerCheckbox);

    }

    public void loadPreviousPlayers(){
        p.setPlayers(getApplicationContext());
        for(Player player : p.allPlayers()){
            updatePlayers(player);
        }
    }


    public void rulesDialog(View view){
        //shows the player the rules of ghost
        AlertDialog dialog = Dialog.rules(this);
        dialog.show();

    }


    public void onStop(){
        p.savePlayers(getApplicationContext());
        super.onStop();
    }

}






