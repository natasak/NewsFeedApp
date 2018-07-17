package com.example.natasa.newsfeedapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        final NewsAdapter newsAdapter = new NewsAdapter(this, news);

        // Get a reference to the {@link ListView} in the layout and attach adapter to the listView
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(newsAdapter);

        // When the item in news list is clicked, open the correct website
        // for that article
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current article that was clicked on
                News currentArticle = newsAdapter.getItem(position);

                //Convert the String URL into a URI object
                //(to pass into the Intent constructor)
                Uri articleUri = Uri.parse(currentArticle.getUrl());

                //Create a new Intent to view the article URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, articleUri);

                //Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

    }
}
