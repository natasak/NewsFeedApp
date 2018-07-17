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

        // Create a fake list of News objects
        ArrayList<News> news = new ArrayList<News>();
        news.add(new News("Title 1", "Section name 1", "author name 1", "date 1"));
        news.add(new News("Title 2", "Section name 2", "author name 2", "date 2"));
        news.add(new News("Title 3", "Section name 3", "author name 3", "date 3"));
        news.add(new News("Title 4", "Section name 4", "author name 4", "date 4"));
        news.add(new News("Title 5", "Section name 5", "author name 5", "date 5"));
        news.add(new News("Title 6", "Section name 6", "author name 6", "date 6"));
        news.add(new News("Title 7", "Section name 7", "author name 7", "date 7"));
        news.add(new News("Title 8", "Section name 8", "author name 8", "date 8"));
        news.add(new News("Title 9", "Section name 9", "author name 9", "date 9"));
        news.add(new News("Title 10", "Section name 10", "author name 10", "date 10"));

        // Create an {@link NewsAdapter}, whose data source is a list of
        // {@link News}s. The adapter knows how to create list item views for each item
        // in the list
        NewsAdapter newsAdapter = new NewsAdapter(this, news);

        // Get a reference to the {@link ListView} in the layout and attach adapter to the listView
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(newsAdapter);

    }
}
