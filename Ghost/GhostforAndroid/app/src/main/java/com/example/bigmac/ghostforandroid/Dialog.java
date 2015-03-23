package com.example.bigmac.ghostforandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Bastiaan Waanders on 22-03-15.
 */
public class Dialog {

    public static AlertDialog rules(Context context){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(R.string.rules);
        alertDialogBuilder.setCancelable(true);
        AlertDialog alertDialog = alertDialogBuilder.create();
        return alertDialog;
    }

    public static AlertDialog gameEnd(final Context context, Player player, final ArrayList<Player> selectedPlayers){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage("" + player.getName() + " has won this game");
        alertDialogBuilder.setPositiveButton(R.string.toHighScore,

                new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                int count = 1;
                Intent highScores = new Intent(context, HighScores_activity.class);
                for(Player player : selectedPlayers){
                    highScores.putExtra("Player" + count, player.getName() );
                    highScores.putExtra("PlayerId" + count, player.getId());
                    count++;
                }

                context.startActivity(highScores);

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        return alertDialog ;
    }

}
