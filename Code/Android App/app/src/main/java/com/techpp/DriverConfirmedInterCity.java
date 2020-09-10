package com.techpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import modal.InterCityRides;
import utils.AppUtils;

public class DriverConfirmedInterCity extends AppCompatActivity implements View.OnClickListener {

    TextView sourceTV, destinationTV, dateTV, timeTV, vehicleDetailsTV, vehicleNumbersTV, phoneNumberTV, titleTV;
    Button backBT;
    ImageView locationIV, calendarIV, carIV, mobileIV;
    String confirmedDriver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_confirmed_inter_city);
        sourceTV = findViewById(R.id.sourceDriverConfirmedDCIC);
        destinationTV = findViewById(R.id.destination_driverConfirmedDCIC);
        dateTV = findViewById(R.id.date_driverConfirmedDCIC);
        timeTV = findViewById(R.id.time_driverConfirmedDCIC);
        vehicleDetailsTV = findViewById(R.id.car_type_driverConfirmedDCIC);
        vehicleNumbersTV = findViewById(R.id.car_number_driverConfirmedDCIC);
        phoneNumberTV = findViewById(R.id.phoneNumber_driverConfirmedIC);
        titleTV = findViewById(R.id.title_driverConfirmedDCIC);
        backBT = findViewById(R.id.backToList_driverConfirmedIC);
        locationIV = findViewById(R.id.locationDCIC);
        calendarIV = findViewById(R.id.calendarDCIC);
        mobileIV = findViewById(R.id.mobileDCIC);
        carIV = findViewById(R.id.carDCIC);

        Intent in = getIntent();
        confirmedDriver = in.getStringExtra("driverConfirmed");
        InterCityRides interCityRides = (InterCityRides) AppUtils.convertJsonToObject(confirmedDriver, InterCityRides.class);
        sourceTV.setText(interCityRides.getSource());
        destinationTV.setText(interCityRides.getDestination());
        vehicleDetailsTV.setText("Civic");
        vehicleNumbersTV.setText("Lez 1232");
        dateTV.setText(interCityRides.getDate());
        timeTV.setText(interCityRides.getTime());
        phoneNumberTV.setText(interCityRides.getMobileNumber());
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.backToList_driverConfirmedIC){
            Intent i = new Intent(getApplicationContext(), InterCityRiderActivity.class);
            startActivity(i);


        }
        else{

        }

    }
}
