package com.techpp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PrefrenceActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView bookARideIV, profileSettingsIV, transactonHistoryIV, rideHistoryIV;
    ProgressDialog   pd = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
       // this.getSupportActionBar().hide();
        bookARideIV = findViewById(R.id.book_a_ride_home);
        profileSettingsIV = findViewById(R.id.profile_settings);
        transactonHistoryIV = findViewById(R.id.transaction_history);
        rideHistoryIV = findViewById(R.id.ride_history);
        pd = new ProgressDialog(PrefrenceActivity.this);
        pd.setMessage("Loading");

        bookARideIV.setOnClickListener(this);
        profileSettingsIV.setOnClickListener(this);
        transactonHistoryIV.setOnClickListener(this);
        rideHistoryIV.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.book_a_ride_home){
            launchActiity(InterOrIntraCityDriverActivity.class);
            pd.show();
        }

        else if(v.getId() == R.id.profile_settings){
            launchActiity(ProfileSettings.class);
            pd.show();

        }

        else if(v.getId() == R.id.transaction_history){
             launchActiity(TransactionHistory.class);
             pd.show();
        }

        else if(v.getId() == R.id.ride_history){
            launchActiity(RideHistory.class);
            pd.show();

        }
        else{

        }


    }


    private void launchActiity(Class c) {
        Intent i = new Intent(PrefrenceActivity.this, c);
        startActivity(i);

    }

}
