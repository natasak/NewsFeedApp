package com.example.natasa.newsfeedapp;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
    }

    /**
     *    Setup of preferences that user can modify
     */
    public static class NewsPreferenceFragment extends PreferenceFragment {

    }
}

