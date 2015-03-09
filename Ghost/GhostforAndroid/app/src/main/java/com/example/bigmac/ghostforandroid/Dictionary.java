package com.example.bigmac.ghostforandroid;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Bastiaan Waanders on 02-03-15.
 */

public class Dictionary {

    static HashSet<String> dictionaryHash = new HashSet<String>();
    static Iterator iterator = dictionaryHash.iterator();
    static boolean match = true;


    Dictionary(Context context) {

        String word;

        InputStream dictionaryStream = context.getResources().openRawResource(R.raw.english);

        InputStreamReader inputReader = new InputStreamReader(dictionaryStream);
        BufferedReader buffReader = new BufferedReader(inputReader);


        try {
            while ((word = buffReader.readLine()) != null) {
                /* only relevant for hashtable
                ArrayList<String> subString = new ArrayList<String>();

                for(int letter = 1; letter <= word.length(); letter++){
                    subString.add(word.substring(0, letter));
                }*/

                dictionaryHash.add(word); //HASHTABLE, subString);
            }
        } catch (IOException e) {

        }

    }


    public static boolean matchWord(String word) {

        if (word.length() <= 3)  {
            checkMatchSubstring(word);
        } else if(dictionaryHash.contains(word)){
            match = false;
            //Log.v("Status", "You lose");
        }else{
            checkMatchSubstring(word);
            //Log.v("Status", "You can play on");
        }
        return match;
    }
    public static boolean checkMatchSubstring(String word){
        String subString;
        boolean checkMatch = false;
        for (String realWord : dictionaryHash) {

            for (int letter = 1; letter < realWord.length() && checkMatch == false ; letter++) {
                subString = realWord.substring(0, letter);

                if (word.equals(subString)) {
                    match = true;
                    checkMatch = true;
                    //Log.v("Status", "You can play on");
                    break;
                }

                else {
                    match = false;
                    //Log.v("Status", "You lose substring was: " + subString +" word was: "+ word);
                }
            }
        }
        return match;
    }
}



