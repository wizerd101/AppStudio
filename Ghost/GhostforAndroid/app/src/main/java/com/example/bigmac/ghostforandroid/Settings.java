package com.example.bigmac.ghostforandroid;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Bastiaan Waanders on 12-03-15.
 */
public class Settings extends PreferenceActivity {

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }


}
