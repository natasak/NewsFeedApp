package com.example.natasa.newsfeedapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QueryUtils {

    /** JSON response for
     * https://content.guardianapis.com/search?section=travel&order-by=newest&show-tags=contributor&q=travel&api-key=...
     */
    private static final String SAMPLE_JSON_RESPONSE = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":21014,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":2102,\"orderBy\":\"newest\",\"results\":[{\"id\":\"travel/2018/jul/17/robben-island-cape-town-south-africa-freedom-swim\",\"type\":\"article\",\"sectionId\":\"travel\",\"sectionName\":\"Travel\",\"webPublicationDate\":\"2018-07-17T09:00:01Z\",\"webTitle\":\"The Freedom Swim: from Mandela’s Robben Island to the Cape Town shore\",\"webUrl\":\"https://www.theguardian.com/travel/2018/jul/17/robben-island-cape-town-south-africa-freedom-swim\",\"apiUrl\":\"https://content.guardianapis.com/travel/2018/jul/17/robben-island-cape-town-south-africa-freedom-swim\",\"tags\":[],\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"travel/2018/jul/17/family-trip-cirali-turkey-beaches-mountains-food-return-trip\",\"type\":\"article\",\"sectionId\":\"travel\",\"sectionName\":\"Travel\",\"webPublicationDate\":\"2018-07-17T05:30:57Z\",\"webTitle\":\"An old flame: a return trip to the beaches and mountains of Çıralı, Turkey\",\"webUrl\":\"https://www.theguardian.com/travel/2018/jul/17/family-trip-cirali-turkey-beaches-mountains-food-return-trip\",\"apiUrl\":\"https://content.guardianapis.com/travel/2018/jul/17/family-trip-cirali-turkey-beaches-mountains-food-return-trip\",\"tags\":[{\"id\":\"profile/andrew-bostock\",\"type\":\"contributor\",\"webTitle\":\"Andrew Bostock\",\"webUrl\":\"https://www.theguardian.com/profile/andrew-bostock\",\"apiUrl\":\"https://content.guardianapis.com/profile/andrew-bostock\",\"references\":[],\"bio\":\"<p><a href=\\\"http://www.bgtw.org/andrew-bostock.html\\\">Andrew Bostock</a> first travelled to Greece over 25 years ago, and has lived in the country. He is the author of the Bradt Guide to the Peloponnese region. <a href=\\\"https://twitter.com/andybostock\\\">@andybostock</a></p>\",\"firstName\":\"bostock\",\"lastName\":\"andrew\"}],\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"travel/2018/jul/14/wingly-flight-sharing-channel\",\"type\":\"article\",\"sectionId\":\"travel\",\"sectionName\":\"Travel\",\"webPublicationDate\":\"2018-07-14T06:00:37Z\",\"webTitle\":\"Wingly: will the ‘Uber of the skies’ take off?\",\"webUrl\":\"https://www.theguardian.com/travel/2018/jul/14/wingly-flight-sharing-channel\",\"apiUrl\":\"https://content.guardianapis.com/travel/2018/jul/14/wingly-flight-sharing-channel\",\"tags\":[{\"id\":\"profile/rupertjones\",\"type\":\"contributor\",\"webTitle\":\"Rupert Jones\",\"webUrl\":\"https://www.theguardian.com/profile/rupertjones\",\"apiUrl\":\"https://content.guardianapis.com/profile/rupertjones\",\"references\":[],\"bio\":\"<p>Rupert Jones is the deputy editor of the Guardian's <a href=\\\"http://www.guardian.co.uk/theguardian/money\\\"> money section</a>. He has also worked as a personal finance correspondent for the <a href=\\\"http://www.guardian.co.uk/theguardian\\\">Guardian</a></p>\",\"bylineImageUrl\":\"https://uploads.guim.co.uk/2017/12/27/Rupert-Jones.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2017/12/27/Rupert_Jones,_L.png\",\"firstName\":\"Rupert\",\"lastName\":\"Jones\"}],\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"travel/2018/jul/14/holiday-guide-cantal-france-best-restaurants-hotels\",\"type\":\"article\",\"sectionId\":\"travel\",\"sectionName\":\"Travel\",\"webPublicationDate\":\"2018-07-14T06:00:34Z\",\"webTitle\":\"Cantal, France, holiday guide: what to see plus the best restaurants and hotels\",\"webUrl\":\"https://www.theguardian.com/travel/2018/jul/14/holiday-guide-cantal-france-best-restaurants-hotels\",\"apiUrl\":\"https://content.guardianapis.com/travel/2018/jul/14/holiday-guide-cantal-france-best-restaurants-hotels\",\"tags\":[{\"id\":\"profile/johnbrunton\",\"type\":\"contributor\",\"webTitle\":\"John Brunton\",\"webUrl\":\"https://www.theguardian.com/profile/johnbrunton\",\"apiUrl\":\"https://content.guardianapis.com/profile/johnbrunton\",\"references\":[],\"bio\":\"<p>John Brunton is a regular contributor to The Guardian, writing and taking photographs on travel, food and wine. Based in Paris and Venice, as well as travelling regularly in South East Asia he blogs at <a href=\\\"http://www.thewinetattoo.com\\\">thewinetattoo.com</a> and posts on Twitter <a href=\\\"https://twitter.com/thewinetattoo\\\">@thewinetattoo</a></p>\",\"firstName\":\"brunton\",\"lastName\":\"\"}],\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"travel/2018/jul/13/albania-kala-festival-new-younger-holidaymakers-beaches\",\"type\":\"article\",\"sectionId\":\"travel\",\"sectionName\":\"Travel\",\"webPublicationDate\":\"2018-07-13T05:30:02Z\",\"webTitle\":\"Kala festival, Albania: party time in what feels like a paradise\",\"webUrl\":\"https://www.theguardian.com/travel/2018/jul/13/albania-kala-festival-new-younger-holidaymakers-beaches\",\"apiUrl\":\"https://content.guardianapis.com/travel/2018/jul/13/albania-kala-festival-new-younger-holidaymakers-beaches\",\"tags\":[{\"id\":\"profile/will-coldwell\",\"type\":\"contributor\",\"webTitle\":\"Will Coldwell\",\"webUrl\":\"https://www.theguardian.com/profile/will-coldwell\",\"apiUrl\":\"https://content.guardianapis.com/profile/will-coldwell\",\"references\":[],\"bio\":\"<p>Will Coldwell is a reporter for Guardian Travel.</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Education/Clearing_Pix/furniture/2012/10/5/1349435287443/will-coldwell-003.jpg\",\"firstName\":\"coldwell\",\"lastName\":\"will\",\"twitterHandle\":\"will_coldwell\"}],\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"travel/2018/jul/12/england-fans-plan-holiday-escapes-world-cup-semi-final-defeat-croatia\",\"type\":\"article\",\"sectionId\":\"travel\",\"sectionName\":\"Travel\",\"webPublicationDate\":\"2018-07-12T17:22:18Z\",\"webTitle\":\"England fans plan holidays after World Cup semi-final defeat\",\"webUrl\":\"https://www.theguardian.com/travel/2018/jul/12/england-fans-plan-holiday-escapes-world-cup-semi-final-defeat-croatia\",\"apiUrl\":\"https://content.guardianapis.com/travel/2018/jul/12/england-fans-plan-holiday-escapes-world-cup-semi-final-defeat-croatia\",\"tags\":[{\"id\":\"profile/isabelchoat\",\"type\":\"contributor\",\"webTitle\":\"Isabel Choat\",\"webUrl\":\"https://www.theguardian.com/profile/isabelchoat\",\"apiUrl\":\"https://content.guardianapis.com/profile/isabelchoat\",\"references\":[],\"bio\":\"<p>Isabel Choat is a <a href=\\\"http://www.guardian.co.uk/travel\\\">travel</a> editor for the Guardian</p>\",\"firstName\":\"choat\",\"lastName\":\"\"}],\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"travel/2018/jul/12/best-uk-outdoor-family-culture-trips-readers-tips\",\"type\":\"article\",\"sectionId\":\"travel\",\"sectionName\":\"Travel\",\"webPublicationDate\":\"2018-07-12T05:30:32Z\",\"webTitle\":\"10 of the best UK outdoor family culture trips: readers’ travel tips\",\"webUrl\":\"https://www.theguardian.com/travel/2018/jul/12/best-uk-outdoor-family-culture-trips-readers-tips\",\"apiUrl\":\"https://content.guardianapis.com/travel/2018/jul/12/best-uk-outdoor-family-culture-trips-readers-tips\",\"tags\":[{\"id\":\"profile/guardian-readers\",\"type\":\"contributor\",\"webTitle\":\"Guardian readers\",\"webUrl\":\"https://www.theguardian.com/profile/guardian-readers\",\"apiUrl\":\"https://content.guardianapis.com/profile/guardian-readers\",\"references\":[],\"bio\":\"<p>The Guardian readers contributor tag is applied to any content that is solely or partly created by you, our readers. It includes projects, galleries and stories involving data, photography, perspectives and more. Thank you for your ongoing inspiration and participation</p>\",\"firstName\":\"readers\",\"lastName\":\"guardian\"}],\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"travel/2018/jul/11/send-us-a-tip-on-road-trips-worldwide-to-win-a-200-hotel-voucher\",\"type\":\"article\",\"sectionId\":\"travel\",\"sectionName\":\"Travel\",\"webPublicationDate\":\"2018-07-11T09:22:25Z\",\"webTitle\":\"Send us a tip on road trips worldwide to win a £200 hotel voucher\",\"webUrl\":\"https://www.theguardian.com/travel/2018/jul/11/send-us-a-tip-on-road-trips-worldwide-to-win-a-200-hotel-voucher\",\"apiUrl\":\"https://content.guardianapis.com/travel/2018/jul/11/send-us-a-tip-on-road-trips-worldwide-to-win-a-200-hotel-voucher\",\"tags\":[],\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"travel/2018/jul/11/wild-swimming-lake-district-brecon-beacons-cairngorms\",\"type\":\"article\",\"sectionId\":\"travel\",\"sectionName\":\"Travel\",\"webPublicationDate\":\"2018-07-11T05:30:03Z\",\"webTitle\":\"Strokes of genius: the beauty and calm of wild swimming\",\"webUrl\":\"https://www.theguardian.com/travel/2018/jul/11/wild-swimming-lake-district-brecon-beacons-cairngorms\",\"apiUrl\":\"https://content.guardianapis.com/travel/2018/jul/11/wild-swimming-lake-district-brecon-beacons-cairngorms\",\"tags\":[{\"id\":\"profile/danielstart\",\"type\":\"contributor\",\"webTitle\":\"Daniel Start\",\"webUrl\":\"https://www.theguardian.com/profile/danielstart\",\"apiUrl\":\"https://content.guardianapis.com/profile/danielstart\",\"references\":[],\"bio\":\"<p>Daniel Start is a travel writer, photographer and environmental consultant. He is the author of numerous books on wild swimming and winner of the Writers' Guild Award for Non-Fiction</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2013/7/18/1374146608256/Daniel-Start.jpg\",\"firstName\":\"start\",\"lastName\":\"\"},{\"id\":\"profile/alexandra-heminsley\",\"type\":\"contributor\",\"webTitle\":\"Alexandra Heminsley\",\"webUrl\":\"https://www.theguardian.com/profile/alexandra-heminsley\",\"apiUrl\":\"https://content.guardianapis.com/profile/alexandra-heminsley\",\"references\":[],\"bio\":\"<p>Alexandra Heminsley is a freelance journalist and author. She is contributing editor at Elle magazine and writes for Red, Grazia, and Stylist. Her second book, Running Like a Girl, is published by Windmill</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2012/3/1/1330628773698/Alexandra_heminsley.jpg\",\"firstName\":\"heminsley\",\"lastName\":\"alexandra\"},{\"id\":\"profile/gemma-cairney\",\"type\":\"contributor\",\"webTitle\":\"Gemma Cairney\",\"webUrl\":\"https://www.theguardian.com/profile/gemma-cairney\",\"apiUrl\":\"https://content.guardianapis.com/profile/gemma-cairney\",\"references\":[],\"bio\":\"<p>Gemma Cairney is an author and broadcaster.<br></p>\",\"bylineImageUrl\":\"https://uploads.guim.co.uk/2018/01/03/Gemma-Cairney.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2018/01/03/Gemma_Cairney,_L.png\",\"firstName\":\"Gemma\",\"lastName\":\"Cairney\"},{\"id\":\"profile/katerew\",\"type\":\"contributor\",\"webTitle\":\"Kate Rew\",\"webUrl\":\"https://www.theguardian.com/profile/katerew\",\"apiUrl\":\"https://content.guardianapis.com/profile/katerew\",\"references\":[],\"bio\":\"<p>Kate Rew is author of Wild Swim (Guardian Books, £12.99) and founder of the<br />Outdoor Swimming Society</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2008/08/15/katerew.jpg\",\"firstName\":\"rew\",\"lastName\":\"kate\"}],\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"travel/2018/jul/10/suffolk-broads-family-boating-holiday-canoeing-river-waveney\",\"type\":\"article\",\"sectionId\":\"travel\",\"sectionName\":\"Travel\",\"webPublicationDate\":\"2018-07-10T05:30:35Z\",\"webTitle\":\"Broads horizons\",\"webUrl\":\"https://www.theguardian.com/travel/2018/jul/10/suffolk-broads-family-boating-holiday-canoeing-river-waveney\",\"apiUrl\":\"https://content.guardianapis.com/travel/2018/jul/10/suffolk-broads-family-boating-holiday-canoeing-river-waveney\",\"tags\":[{\"id\":\"profile/joanneoconnor\",\"type\":\"contributor\",\"webTitle\":\"Joanne O'Connor\",\"webUrl\":\"https://www.theguardian.com/profile/joanneoconnor\",\"apiUrl\":\"https://content.guardianapis.com/profile/joanneoconnor\",\"references\":[],\"bio\":\"<p>Joanne O'Connor is a journalist and travel writer. She is the Observer's former travel editor.</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/contributor/2015/10/6/1444150540527/Joanne-O-Connor.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2017/10/06/Joanne-O'Connor,-R.png\",\"firstName\":\"o'connor\",\"lastName\":\"\"}],\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"}]}}";

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link News} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<News> extractNews() {

        // Create an empty ArrayList that we can start adding news to
        ArrayList<News> news = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create JSONObject from the JON response string
            JSONObject baseJsonResponse = new JSONObject(SAMPLE_JSON_RESPONSE);

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
}
