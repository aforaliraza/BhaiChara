package com.techpp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import java.util.HashMap;
import java.util.Map;
import modal.User;
import utils.ApiUtils;
import utils.AppUtils;


public class LandingActivity extends AppCompatActivity implements View.OnClickListener, FutureCallback<String> {

    EditText phoneNumberED;
    Button verifyBT;
    ProgressDialog pd = null;
    ImageView appLogoIV;
    View blankViewV;
    private static final String TAG = "LandingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"in oncreate of LA");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_activity);
        appLogoIV = findViewById(R.id.logo_landing_activty);
        blankViewV = findViewById(R.id.view_blank);
        phoneNumberED = findViewById(R.id.phoneNumberED);
        verifyBT = findViewById(R.id.verify_btn);
        verifyBT.setOnClickListener(this);
        pd = new ProgressDialog(LandingActivity.this);
        pd.setMessage("Loading");

    }

    @Override
    public void onClick(View v) {
        Log.i(TAG,"in onclick of LA");
        String phoneNumber = null;


        if (v.getId() == R.id.verify_btn) {
            phoneNumber = phoneNumberED.getText().toString();
            if (AppUtils.isNullOrEmptyString(phoneNumber)) {
                phoneNumberED.setError("No value entered");
            } else if (AppUtils.isValidLenght(phoneNumber, 11)) {
                verifyPhoneAccount(phoneNumber);
                pd.show();

            } else {
                phoneNumberED.setError("Invalid Value");
            }
        }
    }
    private void startPrefrenceActivity(){
        Intent i = new Intent(LandingActivity.this, PrefrenceActivity.class);
        startActivity(i);
        finish();
    }

    private void startSignUpActivity(String phoneNumber) {
        Log.i(TAG,"in startsignupactivity of LA");
        Intent i = new Intent(LandingActivity.this, SignUpActivity.class);
        i.putExtra("phoneNumber", phoneNumber);
        startActivity(i);
        finish();
    }

    private void verifyPhoneAccount(String phoneNumber) {
        Log.i(TAG,"in verifyphoneaccount of LA");
        Map<String, String> params = new HashMap<String, String>();
        AppUtils.putInSharedPrefs("MobileNumber", phoneNumber);
        params.put("mobilenum", phoneNumber);
        ApiUtils.callAPI(getApplicationContext(), "checkuser", params, this);


    }

    @Override
    public void onCompleted(Exception e, String result) {
        JsonObject responseJson = null;
        String responseCode = null;
        User user = null;
        pd.dismiss();
        Log.i(TAG, "Api call complete");

        if (AppUtils.isNull(e)) {
            Log.i(TAG, result);
            responseJson = AppUtils.parseServerResponse(result);
            if (!AppUtils.isNull(responseJson)) {
                responseCode = responseJson.get("responseCode").getAsString();
                Log.i(TAG, responseCode);
                if ("00".equals(responseCode)) {
                    //Save use info into sharedprefs
                    user = (User) AppUtils.convertJsonToObject(responseJson.get("payload").toString(), User.class);
                  //  AppUtils.putInSharedPrefs(AppUtils.USER_MOBILE_NUMBER, phoneNumberED.getText().toString());
                    AppUtils.putInSharedPrefs(AppUtils.USER_OBJECT,responseJson.get("payload").toString());
                    startPrefrenceActivity();
                    Log.i(TAG, user.toString());
                } else if ("05".equals(responseCode)) {
                    startSignUpActivity(phoneNumberED.getText().toString());
                }
            }
        } else {
            Toast.makeText(LandingActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(TAG, "onCompleted: ", e);
        }
    }


    }






