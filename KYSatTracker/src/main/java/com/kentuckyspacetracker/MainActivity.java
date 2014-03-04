package com.kentuckyspacetracker;
/*
    This is the main activity class.

    This activity serves as the top of the tree for the entire program and also
    displays the twitter feed.

 */
import android.app.Activity;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class MainActivity extends ListActivity {

    //Define Global Activity Variables used for twitter feed on main activity
    private ListActivity activity;
    final static String ScreenName = "KySpace";
    final static String LOG_TAG = "cpmitc4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove Title Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Set content view to appropriate layout XML
        setContentView(R.layout.activity_main);
        //Set to fullscreen view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Set global variable to store this activity for use later
        activity = this;
        //Call asynchronous task to download twitter feed for news view
        downloadTweets();
    }

    /*
    This function is tied to a button click on the layout XML.
    When called this function launched the New About class which is
    the KySpace virtual tour activity

    Status: Verified through use.

     */
    public void buttonOnClickAbout (View v)
    {
        //button click
        //Intent myIntent = new Intent( this, AboutLauncherActivity.class) ;
        Intent myIntent = new Intent( this, NewAbout.class) ;
        startActivity(myIntent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

    }
    /*
    This function is tied to a button click on the layout XML.
    When called, this function launches the About Launcher which
    became the HELP section.

    NOTE: Naming convention is kinda hacky due to adopting a new activity style for the
     swipe style tour function for the about.  Should be changed to help activity in the future
    to avoid confusion.

    Status: Verified through use.

     */
    public void buttonOnClickInfo (View v)
    {
        //button click
        Intent myIntent = new Intent( this, AboutLauncherActivity.class) ;
        //Intent myIntent = new Intent( this, NewAbout.class) ;
        startActivity(myIntent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

    }
    /*
    This function is tied to a button click on the layout XML.
    When called this function checks if firefox is installed on the mobile device.
    The true case is launching the advanced tracker in a firefox window.
    The false case is launching the Play Store to install firefox

    Status: Verified through use

     */
    public void buttonLaunchOnClick (View v)
    {
        //button click
        //First check if Firefox is installed
        boolean installed = appInstalledOrNot("org.mozilla.firefox");
        if (installed)
        {
            String url = "http://54.201.143.25";
            Intent intent = new Intent(Intent.ACTION_MAIN, null);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setComponent(new ComponentName("org.mozilla.firefox", "org.mozilla.firefox.App"));
            intent.setAction("org.mozilla.gecko.BOOKMARK");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("args", "--url=" + url);
            intent.setData(Uri.parse(url));
            startActivity(intent);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
        //Firefox isn't installed so launch prompt
        else
        {
            String url = "https://play.google.com/store/apps/details?id=org.mozilla.firefox";
            Intent url_intent = new Intent (
                    Intent.ACTION_VIEW,
                    Uri.parse(url)
            );
            url_intent.addCategory(Intent.CATEGORY_BROWSABLE);
            startActivity(url_intent);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }

    }
    /*
    This function is tied to a button click on the layout XML.  This function
    launches the static tracker using Heaven's Above images.

    Status: Verified tr
    */

    public void buttonLaunchHeavens(View v)
    {

        //button click
        Intent myIntent = new Intent( this, HeavensAbove.class) ;
        startActivity(myIntent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

    public void PhoneClick (View v)
    {
        String phoneCallUri = "tel:8593251179";
        Intent phoneCallIntent = new Intent(Intent.ACTION_CALL);
        phoneCallIntent.setData(Uri.parse(phoneCallUri));
        startActivity(phoneCallIntent);

    }

    public void EmailClick(View v)
    {

        /* Create the Intent */
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        /* Fill it with Data */
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"tclements@kentuckyspace.com"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Kentucky Space App Contact");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Text");

        /* Send it off to the Activity-Chooser */
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }


    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed ;
    }


    /*
        NOTE: THE FOLLOWING FUNCTIONS ARE ADDED FOR THE TWITTER NEWSFEED ON THE MAIN ACTIVITY
     */


    /* This function downloads a twitter timeline after first checking to see if there is a network connection
    *
    * Status: Verified through use to be functioning
    * */
    public void downloadTweets() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadTwitterTask().execute(ScreenName);
        } else {
            Log.v(LOG_TAG, "No network connection available.");
        }
    }

    // Uses an AsyncTask to download a Twitter user's timeline
    private class DownloadTwitterTask extends AsyncTask<String, Void, String> {
        final static String CONSUMER_KEY = "Le898YaM5BluxwdcUQop4w";
        final static String CONSUMER_SECRET = "BRO8RiAPgiFvyR1fh9OXFo6vlYNNy2LelVrmTbwE";
        final static String TwitterTokenURL = "https://api.twitter.com/oauth2/token";
        final static String TwitterStreamURL = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=";

        @Override
        protected String doInBackground(String... screenNames) {
            String result = null;

            if (screenNames.length > 0) {
                result = getTwitterStream(screenNames[0]);
            }
            return result;
        }


        // onPostExecute convert the JSON results into a Twitter object (which is an Array list of tweets
        @Override
        protected void onPostExecute(String result) {
            Twitter twits = jsonToTwitter(result);
            TextView text = new TextView(activity);
            TextView date = new TextView(activity);
            // lets write the results to the console as well
            for (Tweet tweet : twits) {
                Log.i(LOG_TAG, tweet.getText());
                Log.i(LOG_TAG, tweet.getDateCreated());

                text.append(tweet.getText());
                date.append( tweet.getDateCreated());
            }


            //Set list adapter as modified twitter adapter for news feed
            setListAdapter(new twitteradaptor(activity, twits));


        }

        // converts a string of JSON data into a Twitter object
        private Twitter jsonToTwitter(String result) {
            Twitter twits = null;
            if (result != null && result.length() > 0) {
                try {
                    Gson gson = new Gson();
                    twits = gson.fromJson(result, Twitter.class);
                } catch (IllegalStateException ex) {
                    // just eat the exception
                }
            }
            return twits;
        }

        // convert a JSON authentication object into an Authenticated object
        private Authenticated jsonToAuthenticated(String rawAuthorization) {
            Authenticated auth = null;
            if (rawAuthorization != null && rawAuthorization.length() > 0) {
                try {
                    Gson gson = new Gson();
                    auth = gson.fromJson(rawAuthorization, Authenticated.class);
                } catch (IllegalStateException ex) {
                    // just eat the exception
                }
            }
            return auth;
        }

        private String getResponseBody(HttpRequestBase request) {
            StringBuilder sb = new StringBuilder();
            try {

                DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
                HttpResponse response = httpClient.execute(request);
                int statusCode = response.getStatusLine().getStatusCode();
                String reason = response.getStatusLine().getReasonPhrase();

                if (statusCode == 200) {

                    HttpEntity entity = response.getEntity();
                    InputStream inputStream = entity.getContent();

                    BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    String line = null;
                    while ((line = bReader.readLine()) != null) {
                        sb.append(line);
                    }
                } else {
                    sb.append(reason);
                }
            } catch (UnsupportedEncodingException ex) {
            } catch (ClientProtocolException ex1) {
            } catch (IOException ex2) {
            }
            return sb.toString();
        }
        /*
           This function returns a Twitter steam using Twitter API 1.1

           Parameter: String screenName of twitter USER
           USAGE NOTES: Requires Twitter CONSUMER_KEY and CONSUMER_SECRET variables to be defined
                        above.  These values are taken from ap creation process for Twitter.
                        Requires username and pw of twitter user to create.

           Status: Verified through use for KySpace twitter.

         */
        private String getTwitterStream(String screenName) {
            String results = null;

            // Step 1: Encode consumer key and secret
            try {
                // URL encode the consumer key and secret
                String urlApiKey = URLEncoder.encode(CONSUMER_KEY, "UTF-8");
                String urlApiSecret = URLEncoder.encode(CONSUMER_SECRET, "UTF-8");

                // Concatenate the encoded consumer key, a colon character, and the
                // encoded consumer secret
                String combined = urlApiKey + ":" + urlApiSecret;

                // Base64 encode the string
                String base64Encoded = Base64.encodeToString(combined.getBytes(), Base64.NO_WRAP);

                // Step 2: Obtain a bearer token
                HttpPost httpPost = new HttpPost(TwitterTokenURL);
                httpPost.setHeader("Authorization", "Basic " + base64Encoded);
                httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                httpPost.setEntity(new StringEntity("grant_type=client_credentials"));
                String rawAuthorization = getResponseBody(httpPost);
                Authenticated auth = jsonToAuthenticated(rawAuthorization);

                // Applications should verify that the value associated with the
                // token_type key of the returned object is bearer
                if (auth != null && auth.token_type.equals("bearer")) {

                    // Step 3: Authenticate API requests with bearer token
                    HttpGet httpGet = new HttpGet(TwitterStreamURL + screenName);

                    // construct a normal HTTPS request and include an Authorization
                    // header with the value of Bearer <>
                    httpGet.setHeader("Authorization", "Bearer " + auth.access_token);
                    httpGet.setHeader("Content-Type", "application/json");
                    // update the results with the body of the response
                    results = getResponseBody(httpGet);
                }
            } catch (UnsupportedEncodingException ex) {
            } catch (IllegalStateException ex1) {
            }
            return results;
        }
    }



}

