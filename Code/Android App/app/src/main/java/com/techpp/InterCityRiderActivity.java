package com.techpp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
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
import modal.Driver;
import modal.InterCityRides;
import modal.User;
import utils.ApiUtils;
import utils.AppUtils;

public class InterCityRiderActivity extends AppCompatActivity implements View.OnClickListener, FutureCallback<String>,
        AdapterView.OnItemClickListener{


    EditText sourceED, destinationED;
    TextView titleTV, confirmDriverTV;
    ListView driversList;
    InterCityDriversAdapter interCityDriversAdapter;
    List<InterCityRides> driversAvailable;
    public static final String TAG = "InterCityRiderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_city_rider);

        sourceED = findViewById(R.id.source_intercity_rideractivity);
        destinationED = findViewById(R.id.destination_intercity_rideractivity);
        titleTV = findViewById(R.id.bhaichara_intercityrider_tv);
        confirmDriverTV = findViewById(R.id.confirmride_intercityrider_tv);
        driversList = findViewById(R.id.intercity_available_driversLV);
        driversList.setOnItemClickListener(this);
        confirmDriverTV.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.confirmride_intercityrider_tv) {
            rideRequest();
        } else {

        }
    }

    private void rideRequest() {
        Log.i(TAG, "in ride request Rider Intercity");
        String userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT, "");
        User user = (User) AppUtils.convertJsonToObject(userObj, User.class);

        Map<String, String> params = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd:MM:yy");
        params.put("mobileNumber", user.getMobileNumber());
        params.put("source", sourceED.getText().toString());
        params.put("destination", destinationED.getText().toString());
        params.put("carType", " ");
        params.put("carNumber", " ");
        params.put("seatsCapacity", " ");
        params.put("roles", "Rider");
        params.put("date", sdfDate.format(cal.getTime()));
        params.put("time", sdfTime.format(cal.getTime()));
        params.put("status", " ");
        ApiUtils.callAPI(getApplicationContext(), "intercityDrivers", params, this);
    }


    @Override
    public void onCompleted(Exception e, String result) {

        Log.i(TAG, "in oncompleted of mapsActivity");
        JsonObject responseJson = null;
        JsonElement jsonElement = null;
        String responseCode = null;
        String payLoad = null;
        if (!AppUtils.isNull(e)) {
            Log.e(TAG, "some error occured", e);
            return;
        }
        responseJson = AppUtils.parseServerResponse(result);
        responseCode = responseJson.get("responseCode").getAsString();
        Log.i(TAG, result);
        Toast.makeText(InterCityRiderActivity.this, "task is completed", Toast.LENGTH_LONG).show();
            if ("00".equals(responseCode)) {

                Log.i(TAG, responseJson.toString());
                jsonElement = responseJson.get("payload");
                if(!jsonElement.isJsonNull()){
                payLoad = responseJson.getAsJsonArray("payload").toString();
                Log.i(TAG, "Payload");
                Log.i(TAG, payLoad);
                Type interCityRidesListClassType = new TypeToken<ArrayList<InterCityRides>>(){}.getType();
                List<InterCityRides> driverList = (ArrayList<InterCityRides>)AppUtils.convertJsonToObject(payLoad, interCityRidesListClassType);
                if(!driverList.isEmpty()) {
                    interCityDriversAdapter = new InterCityDriversAdapter(getApplicationContext(), driverList);
                    driversList.setAdapter(interCityDriversAdapter);
                    driversAvailable = driverList;
                }
                else{
                    Toast.makeText(InterCityRiderActivity.this, "No data Found", Toast.LENGTH_LONG).show();
                }
                }

                else{
                    Toast.makeText(InterCityRiderActivity.this, "No drivers found", Toast.LENGTH_LONG).show();
                }

            }
            else {
                Toast.makeText(InterCityRiderActivity.this, "Failed To Execute", Toast.LENGTH_LONG).show();
            }
        }

    private String createInterCityRidesRiderActivity(){

        InterCityRides interCityRides = new InterCityRides();
        Gson g = new Gson();
        String userObj;
        userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT,"");
        User user = (User)AppUtils.convertJsonToObject(userObj, User.class);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd:MM:yy");

        interCityRides.setRoles("Rider");
        interCityRides.setDestination(destinationED.getText().toString());
        interCityRides.setTime(sdfTime.format(cal.getTime()));
        interCityRides.setDate(sdfDate.format(cal.getTime()));
        interCityRides.setCarType(" ");
        interCityRides.setCarNumber(" ");
        interCityRides.setMobileNumber(" ");
        interCityRides.setSeatsCapacity(" ");
        interCityRides.setSource(sourceED.getText().toString());
        interCityRides.setStatus(" ");
        return g.toJson(interCityRides);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(InterCityRiderActivity.this, "onItemClick", Toast.LENGTH_LONG).show();
        Log.i(TAG, "onItemClick");
        InterCityRides interCityRides = driversAvailable.get(position);
        Gson g = new Gson();
        String driverConfirmed = g.toJson(interCityRides);
        Intent i = new Intent(InterCityRiderActivity.this, DriverConfirmedInterCity.class);
        i.putExtra("driverConfirmed", driverConfirmed);
        startActivity(i);
    }
}