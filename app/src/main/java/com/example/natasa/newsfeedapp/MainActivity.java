package com.example.natasa.newsfeedapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a fake list of articles
        ArrayList<News> news = QueryUtils.extractNews();


        // Create an {@link NewsAdapter}, whose data source is a list of
        // {@link News}s. The adapter knows how to create list item views for each item
        // in the list
        NewsAdapter newsAdapter = new NewsAdapter(this, news);

        // Get a reference to the {@link ListView} in the layout and attach adapter to the listView
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(newsAdapter);

    }
}
