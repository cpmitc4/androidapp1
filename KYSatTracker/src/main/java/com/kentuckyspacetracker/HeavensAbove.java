package com.kentuckyspacetracker;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * This is the activity associated with the Heavens Above based static tracker
 *
 *
 * Created by Chris Mitchell
 */
public class HeavensAbove extends Activity {
    private WebView mWebView;
    private  String currentSatellite;
    public String currentSatellitename;


    /*
        On Create Function:
        -Set Full Screen
        -Remove Title Bar
        -Initiate WebView
        -Set Initial Satellite view to KySat-2 Address
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove Title Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Set Content View
        setContentView(R.layout.heavensabovelayout);
        //Set Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Find WebView
        mWebView = (WebView) findViewById(R.id.activity_main_webview);
        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
       //Don't allow scrolling
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        webSettings.setSupportZoom(false);
        //Set background color to black
        mWebView.setBackgroundColor(0x00000000);
        //Force to load how we want it
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        //Load webpage with KySat-2
        mWebView.loadUrl("http://www.heavens-above.com/orbitdisplay.aspx?icon=default&width=1000&height=500&mode=M&satid=39384");

        //Save currentSatellite
        currentSatellite = "http://www.heavens-above.com/orbitdisplay.aspx?icon=default&width=1000&height=500&mode=M&satid=39384";


    }

    /*
        This function reloads the last satellite image
        Note: Currently Not used in this build but will incorporate into next interface change
     */
    public void onButtonClickRefresh(View V)
    {

        mWebView.loadUrl(currentSatellite);
    }

    /*
        This function is associated with a button click in the layout XML.
        This function loads the Eagle1 orbit into the webview.
        It also adjusts the TextView, and the button images to verify
        which satellite has been chosen.

        Status: Verified through use
     */
    public void onButtonClickEagle1(View V)
    {

        mWebView.loadUrl("http://www.heavens-above.com/orbitdisplay.aspx?icon=default&width=1100&height=550&mode=M&satid=39434");
        currentSatellite = "http://www.heavens-above.com/orbitdisplay.aspx?icon=default&width=1100&height=550&mode=M&satid=39434";

        ImageButton CurrentButton = (ImageButton)findViewById(R.id.eagle1button);
        CurrentButton.setImageResource(R.drawable.eagle1tile);

        CurrentButton = (ImageButton)findViewById(R.id.eagle2button);
        CurrentButton.setImageResource(R.drawable.eagle2tilepressed);

        CurrentButton = (ImageButton)findViewById(R.id.unisatbutton);
        CurrentButton.setImageResource(R.drawable.unisattilepressed);

        CurrentButton = (ImageButton)findViewById(R.id.kysat2button);
        CurrentButton.setImageResource(R.drawable.kysat2tilepressed);

        TextView CurrentText = (TextView)findViewById(R.id.satelliteTextView);
        CurrentText.setText("Current Satellite: Eagle-1");
    }
    /*
        This function is associated with a button click in the layout XML.
        This function loads the Eagle2 orbit into the webview.
        It also adjusts the TextView, and the button images to verify
        which satellite has been chosen.

        Status: Verified through use
     */
    public void onButtonClickEagle2(View V)
    {

        mWebView.loadUrl("http://www.heavens-above.com/orbitdisplay.aspx?icon=default&width=1100&height=550&mode=M&satid=39436");
        currentSatellite = "http://www.heavens-above.com/orbitdisplay.aspx?icon=default&width=1100&height=550&mode=M&satid=39436";
           ImageButton CurrentButton = (ImageButton)findViewById(R.id.eagle2button);
           CurrentButton.setImageResource(R.drawable.eagle2tile);

        CurrentButton = (ImageButton)findViewById(R.id.eagle1button);
        CurrentButton.setImageResource(R.drawable.eagle1tilepressed);

        CurrentButton = (ImageButton)findViewById(R.id.unisatbutton);
        CurrentButton.setImageResource(R.drawable.unisattilepressed);

        CurrentButton = (ImageButton)findViewById(R.id.kysat2button);
        CurrentButton.setImageResource(R.drawable.kysat2tilepressed);

        TextView CurrentText = (TextView)findViewById(R.id.satelliteTextView);
        CurrentText.setText("Current Satellite: Eagle-2");
    }
    /*
        This function is associated with a button click in the layout XML.
        This function loads the UniSat-5 orbit into the webview.
        It also adjusts the TextView, and the button images to verify
        which satellite has been chosen.

        Status: Verified through use
     */
    public void onButtonClickUniSat(View V)
    {

        mWebView.loadUrl("http://www.heavens-above.com/orbitdisplay.aspx?icon=default&width=1100&height=550&mode=M&satid=39421");
        currentSatellite = "http://www.heavens-above.com/orbitdisplay.aspx?icon=default&width=1100&height=550&mode=M&satid=39421";

        ImageButton CurrentButton = (ImageButton)findViewById(R.id.unisatbutton);
        CurrentButton.setImageResource(R.drawable.unisattile);

        CurrentButton = (ImageButton)findViewById(R.id.eagle1button);
        CurrentButton.setImageResource(R.drawable.eagle1tilepressed);

        CurrentButton = (ImageButton)findViewById(R.id.eagle2button);
        CurrentButton.setImageResource(R.drawable.eagle2tilepressed);

        CurrentButton = (ImageButton)findViewById(R.id.kysat2button);
        CurrentButton.setImageResource(R.drawable.kysat2tilepressed);

        TextView CurrentText = (TextView)findViewById(R.id.satelliteTextView);
        CurrentText.setText("Current Satellite: UniSat-5");

    }
    /*
        This function is associated with a button click in the layout XML.
        This function loads the KySat-2 orbit into the webview.
        It also adjusts the TextView, and the button images to verify
        which satellite has been chosen.

        Status: Verified through use
     */
    public void onButtonClickKySat2(View V)
    {

        mWebView.loadUrl("http://www.heavens-above.com/orbitdisplay.aspx?icon=default&width=1100&height=550&mode=M&satid=39384");
        currentSatellite = "http://www.heavens-above.com/orbitdisplay.aspx?icon=default&width=1100&height=550&mode=M&satid=39384";

        ImageButton CurrentButton = (ImageButton)findViewById(R.id.kysat2button);
        CurrentButton.setImageResource(R.drawable.kysat2tile);

        CurrentButton = (ImageButton)findViewById(R.id.eagle1button);
        CurrentButton.setImageResource(R.drawable.eagle1tilepressed);

        CurrentButton = (ImageButton)findViewById(R.id.eagle2button);
        CurrentButton.setImageResource(R.drawable.eagle2tilepressed);

        CurrentButton = (ImageButton)findViewById(R.id.unisatbutton);
        CurrentButton.setImageResource(R.drawable.unisattilepressed);

        TextView CurrentText = (TextView)findViewById(R.id.satelliteTextView);
        CurrentText.setText("Current Satellite: KySat-2");
    }

}
