package com.techpp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import utils.AppUtils;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    ProgressBar progressBar;
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AppUtils.initSharedPrefs(getApplicationContext());
        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(0);

        AsyncTask<String, String, String> a = new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                try {
                    for(int i=0;i<100;i++){
                        Thread.sleep(20);
                        publishProgress();

                    }
                } catch (InterruptedException e) {
                    Log.e(TAG, "some error eccured while thread sleeps", e);
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                counter ++;
                super.onProgressUpdate(values);
                progressBar.setProgress(counter);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(isFirstTime()){

                    Intent i = new Intent(SplashActivity.this,LandingActivity.class);
                    startActivity(i);
                    finish();

                }
                else{
                    Intent i = new Intent(SplashActivity.this, PrefrenceActivity.class );
                    startActivity(i);
                    finish();
                }


            }
        }.execute();

    }
    private static boolean isFirstTime(){


        if(AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT," ").equals(" ")){
            return true;
        }
        else {
            return false;
        }
    }

}
