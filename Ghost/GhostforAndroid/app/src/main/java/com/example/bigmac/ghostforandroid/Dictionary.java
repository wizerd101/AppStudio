package com.example.bigmac.ghostforandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;


/**
 * Created by Bastiaan Waanders on 02-03-15.
 */

public class Dictionary {

    static HashSet<String> dictionaryHash = new HashSet<String>();
    static boolean match = true;


    Dictionary(Context context, String language) {

        String word;
        InputStream dictionaryStream = null;

        if(language.equals("dutch")){
            dictionaryStream = context.getResources().openRawResource(R.raw.dutch);
        }
        else{
            dictionaryStream = context.getResources().openRawResource(R.raw.english);

        }

        InputStreamReader inputReader = new InputStreamReader(dictionaryStream);
        BufferedReader buffReader = new BufferedReader(inputReader);


        try {
            while ((word = buffReader.readLine()) != null) {
                dictionaryHash.add(word);
            }
        } catch (IOException e) {

        }

    }


    public static boolean matchWord(String word) {

        if (word.length() <= 3)  {
            checkMatchSubstring(word);
        } else if(dictionaryHash.contains(word)){
            match = false;
        }else{
            checkMatchSubstring(word);
        }
        return match;
    }

    public static boolean checkMatchSubstring(String word){
        String subString;
        boolean checkMatch = false;
        for (String realWord : dictionaryHash) {

            for (int letter = 1; letter < realWord.length() && !checkMatch; letter++) {
                subString = realWord.substring(0, letter);

                if (word.equals(subString)) {
                    match = true;
                    checkMatch = true;
                    break;
                }

                else {
                    match = false;
                }
            }
        }
        return match;
    }
}



