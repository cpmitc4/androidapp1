package com.kentuckyspacetracker;

/*
This activity is associated with the splash screen.  it is called upon launch and then immediatly
launches the main activity.


 */

        import android.app.Activity;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.os.Handler;
        import android.preference.PreferenceManager;
        import android.view.WindowManager;

public class SplashScreen extends Activity {

    // Splash screen timer in ms
    private static int SPLASH_TIME_OUT = 3000;

    /*
        On Create
        -

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer.
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);
                if(!previouslyStarted){
                    SharedPreferences.Editor edit = prefs.edit();
                    edit.putBoolean(getString(R.string.pref_previously_started), Boolean.TRUE);
                    edit.commit();
                    showHelp();
                }
                else{
                // Start app main activity
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                // close this activity
                finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }
    /*
        This function is called upon first launch.

        The decision point is verified to work but at this point we don't have a
        different activity to call.

     */
    private void showHelp()
    {
        //For now this launches the main activity as well.
        //Replace main activity with first time tour when that exists-CM
        Intent i = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(i);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }

}