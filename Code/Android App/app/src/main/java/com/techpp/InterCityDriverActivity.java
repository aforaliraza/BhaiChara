package com.techpp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.InterCityDriversAdapter;
import modal.InterCityRides;
import modal.User;
import utils.ApiUtils;
import utils.AppUtils;

public class InterCityDriverActivity extends AppCompatActivity implements View.OnClickListener, FutureCallback<String> {


    Button btnDatePickerB, btnTimePickerB, findRidesB;
    EditText txtDateED, txtTimeED, sourceED, destinationED, vehicleDetailsED, vehicleNumberED ;
    TextView titleTV,  seatsRequiredTV;
    ImageView plusIV, minusIV;
    View dividerDateTimeV;
    int carCapacity = 0;
    public static final String TAG = "InterCityDriverActivity";
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_city_driver);

        btnDatePickerB = findViewById(R.id.datebutton_intercity_driveractivity);
        btnTimePickerB = findViewById(R.id.timebutton_intercity_driveractivity);
        findRidesB = findViewById(R.id.searchdriver_intercitydriveractivity_bt);
        txtDateED = findViewById(R.id.date_intercity_driveractivity);
        txtTimeED = findViewById(R.id.time_intercity_driveractivity);
        sourceED = findViewById(R.id.source_intercity_driveractivity);
        destinationED = findViewById(R.id.destination_intercity_driveractivity);
        seatsRequiredTV = findViewById(R.id.carcapacity_intercitydriveractivity_tv);
        titleTV = findViewById(R.id.bhaichara_intercitydriver_tv);
        plusIV = findViewById(R.id.add_seatsrequired_intercitydriveractivity_iv);
        minusIV = findViewById(R.id.deduct_carcapacity_intercitydriveractivity_iv);
        vehicleDetailsED = findViewById(R.id.vehicle_intercity_driveractivity);
        vehicleNumberED = findViewById(R.id.vehicle_number_intercity_driveractivity);
        dividerDateTimeV = findViewById(R.id.divider_date_time_intercity_driver);

        btnDatePickerB.setOnClickListener(this);
        btnTimePickerB.setOnClickListener(this);
        minusIV.setOnClickListener(this);
        plusIV.setOnClickListener(this);
        findRidesB.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.datebutton_intercity_driveractivity) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDateED.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        else if (v.getId() == R.id.timebutton_intercity_driveractivity) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTimeED.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        else if(v.getId() == R.id.add_seatsrequired_intercitydriveractivity_iv){
            if(carCapacity>=0&&carCapacity<4) {
                carCapacity++;
                seatsRequiredTV.setText(String.valueOf(carCapacity));
            }
        }
        else if(v.getId() == R.id.deduct_carcapacity_intercitydriveractivity_iv){
            if(carCapacity>0&&carCapacity<5) {
                carCapacity--;
            seatsRequiredTV.setText(String.valueOf(carCapacity));
            }
        }
        else if(v.getId() == R.id.searchdriver_intercitydriveractivity_bt){
                 addRide();
        }
        else{

        }
    }

    private void addRide() {
        Log.i(TAG, "in ride request Rider Intercity");
        String userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT, "");
        Log.i(TAG, userObj);
        User user = (User) AppUtils.convertJsonToObject(userObj, User.class);

        Map<String, String> params = new HashMap<String, String>();
        Log.i(TAG, "mobile number");
//        Log.i(TAG, user.getMobileNumber());
        String mobileNumber = AppUtils.getFromSharedPrefs("MobileNumber", " ");
        params.put("mobileNumber", mobileNumber);
        params.put("source", sourceED.getText().toString());
        params.put("destination", destinationED.getText().toString());
        params.put("carType", vehicleDetailsED.getText().toString());
        params.put("carNumber", vehicleNumberED.getText().toString());
        params.put("seatsCapacity", seatsRequiredTV.getText().toString());
        params.put("roles", "Driver");
        params.put("date", txtDateED.getText().toString());
        params.put("time", txtTimeED.getText().toString());
        params.put("status", " ");
        ApiUtils.callAPI(getApplicationContext(), "intercityRidesInsert", params, this);
    }

    @Override
    public void onCompleted(Exception e, String result) {

        Log.i(TAG, "in oncompleted of InterCityDriverActivity");
        JsonObject responseJson = null;
        String responseCode = null;
        String payLoad = null;
        if (!AppUtils.isNull(e)) {
            Log.e(TAG, "some error occured", e);
            return;
        }

        Log.i(TAG, result);
        Toast.makeText(InterCityDriverActivity.this, "task is completed", Toast.LENGTH_LONG).show();
        responseJson = AppUtils.parseServerResponse(result);
        if (!AppUtils.isNull(responseJson)) {
            responseCode = responseJson.get("responseCode").getAsString();
            Log.i(TAG, responseCode);
            if ("00".equals(responseCode)) {
                Log.i(TAG, responseJson.toString());
                Log.i(TAG, responseJson.get("payload").toString());
//                Log.i(TAG, payLoad);
                AppUtils.putInSharedPrefs(AppUtils.INTERCITYRIDES_Driver, createInterCityRidesDriverActivity());
                Toast.makeText(InterCityDriverActivity.this, "Ride Added Successfully", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext() , RideAddedDriverInterCity.class);
                startActivity(i);


            }
            else {
                Toast.makeText(InterCityDriverActivity.this, "Failed To Execute", Toast.LENGTH_LONG).show();
            }
        }


    }
    private String createInterCityRidesDriverActivity(){

        InterCityRides interCityRides = new InterCityRides();
        Gson g = new Gson();
        String userObj;
        userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT,"");
        User user = (User)AppUtils.convertJsonToObject(userObj, User.class);
        interCityRides.setRoles("Driver");
        interCityRides.setDestination(destinationED.getText().toString());
        interCityRides.setTime(txtTimeED.getText().toString());
        interCityRides.setDate(txtDateED.getText().toString());
        interCityRides.setCarType(vehicleDetailsED.getText().toString());
        interCityRides.setCarNumber(vehicleNumberED.getText().toString());
        interCityRides.setMobileNumber(user.getMobileNumber());
        interCityRides.setSeatsCapacity(seatsRequiredTV.getText().toString());
        interCityRides.setSource(sourceED.getText().toString());
        interCityRides.setStatus(" ");
        return g.toJson(interCityRides);
    }
}
