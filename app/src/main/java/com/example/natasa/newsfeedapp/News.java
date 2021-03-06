package com.example.natasa.newsfeedapp;

/**
 * {@link News} represents a single news details.
 * Each object has 4 properties: article title, section name, author name and date published.
 */
public class News {

    // Title of the article
    private String mTitle;

    // Section name
    private String mSection;

    // Author
    private String mAuthor;

    // date published
    private String mDate;

    // URL
    private String mUrl;

    /**
     * Create a new News object
     */
    public News(String section, String date, String title, String author, String url) {
        mTitle = title;
        mSection = section;
        mAuthor = author;
        mDate = date;
        mUrl = url;
    }

    // Get the title
    public String getTitle() {
        return mTitle;
    }

    // Get section
    public String getSection() {
        return mSection;
    }

    // Get author
    public String getAuthor() {
        return mAuthor;
    }

    // Get date
    public String getDate() {
        return mDate;
    }

    // Get URL

    public String getUrl() {
        return mUrl;
    }
}
