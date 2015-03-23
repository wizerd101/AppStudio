package com.example.bigmac.ghostforandroid;

import android.util.Log;

/**
 * Created by Bastiaan Waanders on 19-03-15.
 */
public class Player {
    private String name;
    private String id;
    private int score;

    public Player(String name, String id, int score) {
        this.name = name;
        this.id = id;
        this.score = score;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public String getName() {
        //returns the player name
        return name;
    }
    public String getId() {
        //returns the player id
        return this.id;
    }
    public int getScore() {
        //returns the player score;
        return this.score;
    }

    public void incrementScore() {
        //increments the score by one
        this.score++;
    }
}