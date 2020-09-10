package com.techpp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import java.util.HashMap;
import java.util.Map;
import modal.Driver;
import modal.User;
import utils.ApiUtils;
import utils.AppUtils;

public class DriverSignUp extends AppCompatActivity implements View.OnClickListener , FutureCallback<String> {

    Button signupBT;
    EditText licenseNoED;
    TextView bhaiCharaTV, phone_number_tv;
    String phoneNumberStr, groupCodeStr;
    ProgressDialog pd = null;

    private static final String TAG = "DriverSignUp";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_sign_up);
        phone_number_tv = findViewById(R.id.phone_number_driver_signup);
        signupBT = findViewById(R.id.signup_driver_bt);
        licenseNoED = findViewById(R.id.license_no_ed);
        bhaiCharaTV = findViewById(R.id.bhaichara_tv);
        Intent in = getIntent();
        phoneNumberStr = in.getStringExtra("phoneNumber");
        groupCodeStr = in.getStringExtra("groupCode");
        phone_number_tv.setText(phoneNumberStr);
        pd = new ProgressDialog(DriverSignUp.this);
        pd.setMessage("Loading");


        signupBT.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(formValidation()){
            createNewDriverAccount();
        //    pd.show();

            //Intent i = new Intent(DriverSignUp.this, VehicleRide.class);
            //startActivity(i);

        }


    }

    @Override
    public void onCompleted(Exception e, String result) {
        Log.i(TAG,"in oncompleted of SUA");
        if(AppUtils.isNull(e)) {
            Log.i(TAG, result);
            Toast.makeText(DriverSignUp.this, "task is completed", Toast.LENGTH_LONG).show();
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
                responseCode = responseJson.get("responseCode").getAsString();
                Log.i(TAG, responseCode);
                if ("00".equals(responseCode)) {
                    //Save use info into sharedprefs
                    AppUtils.putInSharedPrefs(AppUtils.DRIVER_OBJECT, createDriverObject());

                    Intent i = new Intent(DriverSignUp.this, VehicleRide.class);
                    startActivity(i);
                    finish();

                              Log.i(TAG, "completed succesfully SUA");
                } else {
                    Toast.makeText(DriverSignUp.this,"Some error occured",Toast.LENGTH_LONG).show();
                    Log.e(TAG,"Some error occured",e);

                }
            }
        } else {
            Toast.makeText(DriverSignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(TAG, "onCompleted: ", e);
        }
    }

    private boolean formValidation() {
        Log.i(TAG, "in formvalidation of driver signup");
        boolean isValidForm = true;
        String license = licenseNoED.getText().toString();

        if (AppUtils.isNullOrEmptyString(license)) {
            licenseNoED.setError("No value entered");
            isValidForm = false;
        }
        return isValidForm;

    }

    private String createDriverObject(){
        Driver driver = new Driver();
        Gson g = new Gson();
        driver.setMobileNumber(phoneNumberStr);
        driver.setLicenceNO(licenseNoED.getText().toString());
        driver.setGroupCode(groupCodeStr);
        driver.setCurentLocationLat(" ");
        driver.setCurentLocationLong(" ");
        driver.setStatus(" ");
        return g.toJson(driver);

    }

    private void createNewDriverAccount(){
        Log.i(TAG,"in create user account of driver signup");
        String userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT, "");
        User user = (User)AppUtils.convertJsonToObject(userObj, User.class);

        Map<String, String> params  = new HashMap<String, String>();
        params.put("mobileNumber", user.getMobileNumber() );
        params.put("licenceNO", licenseNoED.getText().toString());
        params.put("status", " ");
        params.put("curentLocationLong", " ");
        params.put("curentLocationLat", " ");
        params.put("groupCode", groupCodeStr);
        ApiUtils.callAPI(getApplicationContext(), "createdriversaccount", params, this);
    }



}