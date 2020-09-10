package com.techpp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;

import java.util.HashMap;
import java.util.Map;

import modal.User;
import utils.ApiUtils;
import utils.AppUtils;

public class ProfileSettings extends AppCompatActivity implements View.OnClickListener , FutureCallback<String>{

    Button updateBT;
    EditText firstNameED, lastNameED, emailED, codeED;
    TextView bhaiCharaTV, phone_number_tv;
    String phoneNumberstr;
    ProgressDialog pd = null;
    private static final String TAG = "ProfileSettings";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"in oncreate of profileSettings");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);
        phone_number_tv =  findViewById(R.id.phone_number_profileUpdate);
        updateBT = findViewById(R.id.update_bt);
        firstNameED = findViewById(R.id.first_nameED_profileUpdate);
        lastNameED = findViewById(R.id.last_nameED_profileUpdate);
        emailED = findViewById(R.id.emailED_profileUpdate);
        codeED = findViewById(R.id.groupCodeED_profileUpdate);
        bhaiCharaTV = findViewById(R.id.titleTV_profileSettings);

        String userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT, "");
        User user = (User)AppUtils.convertJsonToObject(userObj, User.class);
        phone_number_tv.setText(user.getMobileNumber());
        firstNameED.setText(user.getFirstName());
        lastNameED.setText(user.getLastName());
        codeED.setText(user.getGroupCode());
        emailED.setText(user.getEmailId());

        pd = new ProgressDialog(ProfileSettings.this);
        pd.setMessage("Loading");


        updateBT.setOnClickListener(this);


    }


    @Override
    public void onClick (View v){
        if(v.getId() == R.id.update_bt ) {
            if (formValidation()) {
                updateUserAccount();
                pd.show();
            }
            else{

            }
        }
        else{

        }
    }
    private boolean formValidation(){
        Log.i(TAG,"in formvalidation of SUA");
        boolean isValidForm=true;
        String firstName = firstNameED.getText().toString();
        String lastName = lastNameED.getText().toString();
        String email = emailED.getText().toString();
        String code = codeED.getText().toString();

        if (AppUtils.isNullOrEmptyString(firstName)) {
            firstNameED.setError("No value entered");
            isValidForm=false;
        }
        if (AppUtils.isNullOrEmptyString(lastName)) {
            lastNameED.setError("No value entered");
            isValidForm=false;

        }
        if (AppUtils.isNullOrEmptyString(email)) {
            emailED.setError("No value entered");
            isValidForm=false;
        }
        if (AppUtils.isNullOrEmptyString(code)) {
            codeED.setError("No value entered");
            isValidForm=false;
        }


        return isValidForm;

    }

    private void updateUserAccount(){
        Log.i(TAG,"in create user account of updateProfile");
        Map<String, String> params  = new HashMap<String, String>();
        String userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT, "");
        User user = (User)AppUtils.convertJsonToObject(userObj, User.class);
        Toast.makeText(ProfileSettings.this, phoneNumberstr, Toast.LENGTH_LONG).show();
        params.put("mobileNumber", user.getMobileNumber());
        params.put("firstName", firstNameED.getText().toString());
        params.put("lastName", lastNameED.getText().toString());
        params.put("emailId", emailED.getText().toString());
        params.put("groupCode", codeED.getText().toString());
        params.put("roles", user.getRoles());
        ApiUtils.callAPI(getApplicationContext(), "profilesetting", params, this);
    }

    @Override
    public void onCompleted(Exception e, String result) {
        Log.i(TAG,"in oncompleted of SUA");
        pd.dismiss();

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
                        Toast.makeText(ProfileSettings.this,"User Object saved in Shared Prefrences",Toast.LENGTH_LONG).show();
                        AppUtils.putInSharedPrefs(AppUtils.USER_OBJECT,createUserObject());
                        Intent i = new Intent(ProfileSettings.this, PrefrenceActivity.class);
                        startActivity(i);
                        Log.i(TAG, "completed succesfully SUA");}
                else{


                }

            }
        } else {
            Toast.makeText(ProfileSettings.this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(TAG, "onCompleted: ", e);
        }
    }

    private String createUserObject(){
        User user = new User();
        Gson g = new Gson();
        String userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT, "");
        User userObject = (User)AppUtils.convertJsonToObject(userObj, User.class);
        phoneNumberstr = AppUtils.getFromSharedPrefs("MobileNumber", " ");
        user.setMobileNumber(phoneNumberstr);
        user.setFirstName(firstNameED.getText().toString());
        user.setLastName(lastNameED.getText().toString());
        user.setEmailId(emailED.getText().toString());
        user.setGroupCode(codeED.getText().toString());
        user.setRoles(userObject.getRoles());
        return g.toJson(user);

    }
}