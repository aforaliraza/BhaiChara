package com.techpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import utils.AppUtils;

public class InterOrIntraCityDriverActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView keepCalmAndInterCityIV, liveToDriveIV;
    TextView titleTV, interCityTV, intraCityTV;
    View dividerOne, dividerTwo, dividerThree, dividerFour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_intra_city_driver);
        keepCalmAndInterCityIV = findViewById(R.id.keepcalmintercitydriver_interintra_driver);
        liveToDriveIV = findViewById(R.id.livetodrivedriver_interintra_driver);
        titleTV = findViewById(R.id.bhaichara_interintra_driver);
        interCityTV = findViewById(R.id.intercity_intrainter_driver_tv);
        intraCityTV = findViewById(R.id.intracity_intrainter_driver_tv);
        dividerOne = findViewById(R.id.divider_one);
        dividerTwo = findViewById(R.id.divider_two);
        dividerThree = findViewById(R.id.divider_three);
        dividerFour = findViewById(R.id.divider_four);

        interCityTV.setOnClickListener(this);
        intraCityTV.setOnClickListener(this);



    }
    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.intercity_intrainter_driver_tv){
           if(AppUtils.getFromSharedPrefs(AppUtils.INTERCITYRIDES_Driver, " ").equals(" ")) {
               launchActiity(InterCityDriverActivity.class);
           }
           else{
               launchActiity(RideAddedDriverInterCity.class);
           }
        }

        else if(v.getId() == R.id.intracity_intrainter_driver_tv){
            launchActiity(StartRide.class);

        }

        else {
                    }


    }
    private void launchActiity(Class c) {
        Intent i = new Intent(InterOrIntraCityDriverActivity.this, c);
        startActivity(i);

    }

    }
