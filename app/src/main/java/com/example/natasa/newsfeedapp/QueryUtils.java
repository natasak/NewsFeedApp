package com.example.natasa.newsfeedapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.example.natasa.newsfeedapp.MainActivity.LOG_TAG;

/**
 * Helper methods related to requesting and receiving news data from The Guardian.
 */

public class QueryUtils {

    // milliseconds
    private static final int READ_TIMEOUT = 10000;
    private static final int CONNECT_TIMEOUT = 15000;

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(READ_TIMEOUT);
            urlConnection.setConnectTimeout(CONNECT_TIMEOUT);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the news JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a list of {@link News} objects that has been built up from
     * parsing a JSON response.
     */
    public static List<News> extractFeatureFromJson(String articleJSON) {
        // If the JSON string is empty or null, then return early
        if (TextUtils.isEmpty(articleJSON)) {
            return null;
        }

        // Create an empty ArrayList (List) that we can start adding news to
        List<News> news = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create JSONObject from the JON response string
            JSONObject baseJsonResponse = new JSONObject(articleJSON);

            // Extract "response" JSON Object
            JSONObject response = baseJsonResponse.getJSONObject("response");

            // Extract "results" JSON Array
            JSONArray newsArray = response.getJSONArray("results");

            // Loop through each result in the array and for each news item
            //  create a News object
            for (int i=0; i < newsArray.length(); i++) {
                // Get article JSONObject at position i
                JSONObject currentArticle = newsArray.getJSONObject(i);

                // Extract "sectionName"
                String sectionName = currentArticle.getString("sectionName");

                // Extract "webPublicationDate"
                String webPublicationDate = currentArticle.getString("webPublicationDate");
                // Delete last 10 characters from the date string
                webPublicationDate = webPublicationDate.substring(0, webPublicationDate.length() - 10);

                // Extract "webTitle"
                String webTitle = currentArticle.getString("webTitle");

                // Extract "webUrl"
                String webUrl = currentArticle.getString("webUrl");

                // Get "tags" JSONArray
                JSONArray authorArray = currentArticle.getJSONArray("tags");

                // Get author in the authorArray (there is only 1), if there is any
                String author;
                if (authorArray.length() != 0) {
                    JSONObject currentAuthor = authorArray.getJSONObject(0);

                    // Extract author - "webTitle"
                    author = currentAuthor.getString("webTitle");
                } else {
                    author = "";
                }

                // Create News java object
                News article = new News(sectionName, webPublicationDate, webTitle, author, webUrl);

                // Add article to list of news
                news.add(article);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }

        // Return the list of news
        return news;
    }

    /**
     * Query the Guardian dataset and return a list of {@link News} objects.
     */
    public static List<News> fetchNewsData(String requestUrl) {

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request", e);
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
        List<News> news = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link News}s
        return news;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }
}
