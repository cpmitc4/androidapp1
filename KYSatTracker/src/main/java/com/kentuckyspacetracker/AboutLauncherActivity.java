package com.kentuckyspacetracker;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * This is the current Help Page.  Name should be changed for clarity.
 */
public class AboutLauncherActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set Full Screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.aboutlauncher);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void buttonLaunchHeavens(View v){

        //button click
        Intent myIntent = new Intent( this, HeavensAbove.class) ;
        startActivity(myIntent);


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
        }

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

}
