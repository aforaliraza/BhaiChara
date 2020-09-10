package com.techpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import modal.InterCityRides;
import utils.AppUtils;

public class RideAddedDriverInterCity extends AppCompatActivity implements View.OnClickListener {
    TextView sourceTV, destinationTV, dateTV, timeTV, vehicleDetailsTV, vehicleNumbersTV, titleTV;
    Button backBT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_added_driver_inter_city);

        sourceTV = findViewById(R.id.sourceRideAddedDriverIC);
        destinationTV = findViewById(R.id.destination_rideAddedDriverIC);
        dateTV = findViewById(R.id.date_rideAddedDriverIC);
        timeTV = findViewById(R.id.time_rideAddedDriverIC);
        vehicleDetailsTV = findViewById(R.id.car_type_rideAddedDriverIC);
        vehicleNumbersTV = findViewById(R.id.car_number_rideAddedDriverIC);
        titleTV = findViewById(R.id.title_rideAddedDriverIC);
        backBT = findViewById(R.id.backToInterCity_rideAddedDriverIC);
        backBT.setOnClickListener(this);
        String stringObj = AppUtils.getFromSharedPrefs(AppUtils.INTERCITYRIDES_Driver, " ");
        InterCityRides interCityRides = (InterCityRides) AppUtils.convertJsonToObject(stringObj, InterCityRides.class);
        sourceTV.setText("Source: "+ interCityRides.getSource());
        destinationTV.setText("Destination: " +  interCityRides.getDestination());
        dateTV.setText("Dated: "+ interCityRides.getDate());
        timeTV.setText("Time: "+ interCityRides.getTime());
        vehicleDetailsTV.setText("Vehicledetails: " + interCityRides.getCarType());
        vehicleNumbersTV.setText("VehicleNumber: " + interCityRides.getCarNumber());
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.backToInterCity_rideAddedDriverIC){
            Intent i = new Intent(getApplicationContext(), InterCityDriverActivity.class);
            startActivity(i);
        }

    }
}
