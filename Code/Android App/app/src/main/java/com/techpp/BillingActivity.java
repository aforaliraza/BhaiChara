package com.techpp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import utils.AppUtils;

public class BillingActivity extends AppCompatActivity {
TextView titleTV, driverFareTV, driverAmountTV, firstRiderFareTV, firstRiderAmountTV, secondRiderFareTV, secondRiderAmountTV ,
        thirdRiderFareTV, thirdRiderAmountTV, fourthRiderFareTV, fourthRiderAmountTV;
static List<String> mobileNumbersBA;
static List<Double> amountFareBA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
        titleTV = findViewById(R.id.title_billingActivity);
        driverFareTV = findViewById(R.id.driverFareBillingActivity);
        driverAmountTV = findViewById(R.id.driverFareAmountBillingActivity);
        firstRiderFareTV = findViewById(R.id.firstRiderFareBillingActivity);
        firstRiderAmountTV = findViewById(R.id.firstRiderFareAmountBillingActivity);
        secondRiderFareTV = findViewById(R.id.secondRiderFareBillingActivity);
        secondRiderAmountTV = findViewById(R.id.secondRiderFareAmountBillingActivity);
        thirdRiderFareTV = findViewById(R.id.thirdRiderFareBillingActivity);
        thirdRiderAmountTV = findViewById(R.id.thirdRiderFareAmountBillingActivity);
        fourthRiderFareTV = findViewById(R.id.fourthRiderFareBillingActivity);
        fourthRiderAmountTV = findViewById(R.id.fourthRiderFareAmountBillingActivity);
        if(mobileNumbersBA.isEmpty()&& amountFareBA.isEmpty()){
            Toast.makeText(getApplicationContext(), "Some Error Occurred", Toast.LENGTH_LONG).show();
        }
        else{
            driverFareTV.setText("The fare for the user: "+mobileNumbersBA.get(0));
            driverAmountTV.setText(amountFareBA.get(0).toString());
            firstRiderFareTV.setText("The fare for the user: "+mobileNumbersBA.get(1));
            firstRiderAmountTV.setText(amountFareBA.get(1).toString());
            secondRiderFareTV.setText("The fare for the user: "+mobileNumbersBA.get(2));
            secondRiderAmountTV.setText(amountFareBA.get(2).toString());
            thirdRiderFareTV.setText("The fare for the user: "+mobileNumbersBA.get(3));
            thirdRiderAmountTV.setText(amountFareBA.get(3).toString());
            fourthRiderFareTV.setText("The fare for the user: "+mobileNumbersBA.get(4));
            fourthRiderAmountTV.setText(amountFareBA.get(4).toString());
        }
    }
    public static void initilizeLists(List<String> mobileNumbers, List<Double> amountFare){
       mobileNumbersBA = mobileNumbers;
        amountFareBA = amountFare;
    }
}
