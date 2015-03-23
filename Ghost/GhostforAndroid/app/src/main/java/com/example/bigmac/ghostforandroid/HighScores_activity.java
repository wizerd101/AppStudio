package com.example.bigmac.ghostforandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.*;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bastiaan Waanders on 19-03-15.
 */
public class HighScores_activity extends ActionBarActivity {
    Players p = new Players();
    Start start = new Start();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscores);
        showHighScores();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.backToStart:
                toStart(getApplicationContext());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void toStart(Context context) {
        Intent backToStart = new Intent(context, Start.class);
        startActivity(backToStart);
    }

    public void showHighScores() {
        for (Player player : p.allPlayers()) {
            LinearLayout choosePlayerLayout = (LinearLayout) findViewById(R.id.highScoreList);
            TextView playerTextView = new TextView(getApplicationContext());


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(0, 10, 0, 0);
            playerTextView.setText(player.getName() + " " + player.getScore());
            playerTextView.setTextColor(Color.BLACK);

            choosePlayerLayout.addView(playerTextView);

        }
    }

    public void playAgain(View view) {

        Intent playAgain = new Intent(this, Game_activity.class);
        int count = 1;
        for(Player player : start.getSelectedPlayersArrayList()){
            playAgain.putExtra("Player" + count, player.getName() );
            playAgain.putExtra("PlayerId" + count, player.getId());
            count++;
        }
        playAgain.putExtra("Language", Start.getLanguage());
        startActivity(playAgain);
    }

    public void toStart(View view) {
        Intent backToStart = new Intent(this, Start.class);
        onStop();
        startActivity(backToStart);
    }
    public void onStop(){
        p.savePlayers(getApplicationContext());
        super.onStop();
    }
}
