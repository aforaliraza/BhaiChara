package com.techpp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import modal.User;
import modal.VehicleDetails;
import utils.ApiUtils;
import utils.AppUtils;

public class VehicleRide extends AppCompatActivity implements View.OnClickListener, FutureCallback<String> {


    Button addVehicleBT;
    EditText carTypeED, vehicleNumberED, sourceED, destinationED;
    RadioButton motorBikeRB, carRB;
    RadioGroup vehicleTypeRG;
    int carCapacity = 0, radioButtonId;
    TextView capacityBoxTV;
    ImageView addIV, deductIV;
    private static final String TAG = "VehicleRide";
    String currentDate, currentTime, phoneNumber;
    ProgressDialog   pd = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);

        addVehicleBT =  findViewById(R.id.addvehicle_vehicledetails_bt);
        carTypeED =  findViewById(R.id.cartype_vehicledetails_activity);
        vehicleNumberED =  findViewById(R.id.vehiclenumber_vehicledetails_activity);
        sourceED =  findViewById(R.id.source_vehicledetails_activity);
        destinationED =  findViewById(R.id.destination_vehicledetails_activity);
        capacityBoxTV =  findViewById(R.id.carcapacity_vehicledetails_tv);
        vehicleTypeRG =  findViewById(R.id.radio_group_vehicle_type);
        addIV = findViewById(R.id.add_carcapacity_vehicledetails_iv);
        deductIV =  findViewById(R.id.deduct_carcapacity_vehicledetails_iv);
        motorBikeRB =  findViewById(R.id.motorbike_vehicledetails_radio_button);
        carRB = findViewById(R.id.car_vehicledetails_radio_button);
        radioButtonId = vehicleTypeRG.getCheckedRadioButtonId();
      //  phoneNumber = AppUtils.getFromSharedPrefs(AppUtils.USER_MOBILE_NUMBER, "number not found");
        currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        pd = new ProgressDialog(VehicleRide.this);
        pd.setMessage("Loading");




        addVehicleBT.setOnClickListener(this);
        addIV.setOnClickListener(this);
        deductIV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addvehicle_vehicledetails_bt){
          addNewVehicleDetails();
        //  pd.show();
        }

        else if(v.getId() == R.id.add_carcapacity_vehicledetails_iv){
            if(radioButtonId == R.id.motorbike_vehicledetails_radio_button ){
                carCapacity = 1;
                capacityBoxTV.setText(""+carCapacity);
            }
            else {
                carCapacity++;
                capacityBoxTV.setText(""+carCapacity);
            }

        }

        else if(v.getId() == R.id.deduct_carcapacity_vehicledetails_iv){
            carCapacity--;
        }

        else{

        }

    }


    private void addNewVehicleDetails(){

        Map<String, String> params  = new HashMap<String, String>();
        String userObj;
        userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT,"");
        User user = (User)AppUtils.convertJsonToObject(userObj, User.class);
        params.put("mobileNumber", user.getMobileNumber());
        params.put("carType", carTypeED.getText().toString());
        params.put("carNumber", vehicleNumberED.getText().toString());
        params.put("seatAvailable", ""+carCapacity);
        params.put("date", currentDate );
        params.put("time", currentTime);
        params.put("source", sourceED.getText().toString() );
        params.put("destination", sourceED.getText().toString());

        ApiUtils.callAPI(getApplicationContext(), "addvehicledetails", params, this);
    }

    @Override
    public void onCompleted(Exception e, String result) {
        Log.i(TAG, result);
        Log.i(TAG,"in oncompleted of SUA");
        if(AppUtils.isNull(e)) {
            Log.i(TAG, result);
            Toast.makeText(VehicleRide.this, "task is completed", Toast.LENGTH_LONG).show();
        }
        else{
            Log.e(TAG,"some error occured",e);
        }
        JsonObject responseJson = null;
        String responseCode = null;


        if (AppUtils.isNull(e)) {
            Log.i(TAG, result);
            responseJson = AppUtils.parseServerResponse(result);
            if (!AppUtils.isNull(responseJson)) {
              //  Log.i(TAG, responseCode);
                responseCode = responseJson.get("responseCode").getAsString();
                //Log.i(TAG, responseCode);
                if ("00".equals(responseCode)) {
                    //Save use info into sharedprefs
                    AppUtils.putInSharedPrefs(AppUtils.RIDE_OBJECT, createVehicleRideObject());
                    Intent i = new Intent(VehicleRide.this, InterOrIntraCityDriverActivity.class);
                    //Intent i = new Intent(VehicleRide.this, InterOrIntraCityDriverActivity.class);
                    startActivity(i);
                    finish();
                   // Log.i(TAG, "completed succesfully SUA");
                } else {
                    Toast.makeText(VehicleRide.this,"Some error occured",Toast.LENGTH_LONG).show();
                   // Log.e(TAG,"Some error occured",e);

                }
            }
        } else {
            Toast.makeText(VehicleRide.this, e.getMessage(), Toast.LENGTH_LONG).show();
            //Log.e(TAG, "onCompleted: ", e);
        }
    }

    private String createVehicleRideObject(){
        VehicleDetails vehicleDetails = new VehicleDetails();
        Gson g = new Gson();
        String userObj;
        userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT,"");
        User user = (User)AppUtils.convertJsonToObject(userObj, User.class);
        vehicleDetails.setMobile_no(user.getMobileNumber());
        vehicleDetails.setCarType(carTypeED.getText().toString());
        vehicleDetails.setCarNumber(vehicleNumberED.getText().toString());
        vehicleDetails.setSeatAvailable(""+carCapacity);
        vehicleDetails.setDate(currentDate);
        vehicleDetails.setTime(currentTime);
        vehicleDetails.setDestination(destinationED.getText().toString());
        vehicleDetails.setSource(sourceED.getText().toString());
        return g.toJson(vehicleDetails);

    }



}