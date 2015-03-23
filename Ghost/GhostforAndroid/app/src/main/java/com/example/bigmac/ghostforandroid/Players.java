package com.example.bigmac.ghostforandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Bastiaan Waanders on 09-03-15.
 */

public class Players {

    private static ArrayList <Player> players = new ArrayList<Player>();
    Gson playersGson = new Gson();
    Type arrayListType = new TypeToken<List<Player>>(){}.getType();

    public Player createPlayer(String name){
        Player p = new Player(name, setId(), 0);
        players.add(p);
        return p;
    }
    public ArrayList<Player> allPlayers(){
        return players;
    }

    public String setId(){

        UUID id = UUID.randomUUID();
        for(Player player: players){
            if(id.equals(player.getId()))
                setId();
        }
        return id.toString();
    }

    public void setPlayers(Context context){
        SharedPreferences playersPref = context.getSharedPreferences("PLAYERS", Context.MODE_PRIVATE);
        String allPlayers =playersPref.getString("Players", null);

        if(allPlayers != null){
            players.clear();
            List<Player> playersList = Collections.synchronizedList(
                    (List<Player>) playersGson.fromJson(allPlayers, arrayListType));
            players.addAll(playersList);
        }

    }
    public void savePlayers(Context context){

        SharedPreferences.Editor playerEdit = context.getSharedPreferences("PLAYERS", context.MODE_PRIVATE).edit();
        String allPlayers  = playersGson.toJson(players);
        playerEdit.putString("Players", allPlayers);
        Log.v("Status", "Saving info " + allPlayers);
        playerEdit.apply();
    }


}
