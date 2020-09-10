package com.techpp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modal.User;
import utils.ApiUtils;
import utils.AppUtils;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener , FutureCallback<String>,
        CheckBox.OnCheckedChangeListener {
    Button signupBT;
    EditText firstNameED, lastNameED, emailED, codeED;
    TextView bhaiCharaTV, phone_number_tv;
    String phoneNumberstr;
    CheckBox driverCheckBox;
    ProgressDialog pd = null;
    private static final String TAG = "SignUpActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"in oncreate of SUA");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        phone_number_tv =  findViewById(R.id.phone_number_signup);
        signupBT = findViewById(R.id.signup_bt);
        firstNameED = findViewById(R.id.first_name_ed);
        lastNameED = findViewById(R.id.last_name_ed);
        emailED = findViewById(R.id.email_ed);
        codeED = findViewById(R.id.code_ed);
        bhaiCharaTV = findViewById(R.id.bhaichara_tv);
        driverCheckBox = findViewById(R.id.checkbox_driver_cb);
        Intent in = getIntent();
        phoneNumberstr = in.getStringExtra("phoneNumber");
        phone_number_tv.setText(phoneNumberstr);
        pd = new ProgressDialog(SignUpActivity.this);
        pd.setMessage("Loading");


        signupBT.setOnClickListener(this);


    }


        @Override
        public void onClick (View v){

        createNewUserAccount();
        pd.show();

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

    private void createNewUserAccount(){
        Log.i(TAG,"in create user account of SUA");
        Map<String, String> params  = new HashMap<String, String>();
        params.put("mobileNumber", phoneNumberstr);
        params.put("firstName", firstNameED.getText().toString());
        params.put("lastName", lastNameED.getText().toString());
        params.put("emailId", emailED.getText().toString());
        params.put("groupCode", codeED.getText().toString());
        if(driverCheckBox.isChecked()){
            params.put("roles", "Driver");
        }
        else{
            params.put("roles", "Rider");
        }
        ApiUtils.callAPI(getApplicationContext(), "createaccount", params, this);
            }

    @Override
    public void onCompleted(Exception e, String result) {
        Log.i(TAG,"in oncompleted of SUA");
        if(AppUtils.isNull(e)) {
            Log.i(TAG, result);
            Toast.makeText(SignUpActivity.this, "task is completed", Toast.LENGTH_LONG).show();
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
                    if(formValidation() && !driverCheckBox.isChecked()){

                    //Save use info into sharedprefs
                        Toast.makeText(SignUpActivity.this,"User Object saved in Shared Prefrences",Toast.LENGTH_LONG).show();
                    AppUtils.putInSharedPrefs(AppUtils.USER_OBJECT,createUserObject());
                        launchActiity(PrefrenceActivity.class);
                    //Intent i = new Intent(SignUpActivity.this, PrefrenceActivity.class);
                    Log.i(TAG, "completed succesfully SUA");}
                    else{
//                  AppUtils.putInSharedPrefs(AppUtils.USER_ROLES, "Driver");
                        Toast.makeText(SignUpActivity.this,"user Obj Saved in sharedPrefs",Toast.LENGTH_LONG).show();
                        AppUtils.putInSharedPrefs(AppUtils.USER_OBJECT,createUserObject());

                        launchActiity(DriverSignUp.class);
                    }
                } else {
                    Toast.makeText(SignUpActivity.this,"Some error occured",Toast.LENGTH_LONG).show();
                    Log.e(TAG,"Some error occured",e);

                }
            }
        } else {
            Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(TAG, "onCompleted: ", e);
        }
    }

    private String createUserObject(){
        User user = new User();
        Gson g = new Gson();
        user.setMobileNumber(phoneNumberstr);
        user.setFirstName(firstNameED.getText().toString());
        user.setLastName(lastNameED.getText().toString());
        user.setEmailId(emailED.getText().toString());
        user.setGroupCode(codeED.getText().toString());
        if(driverCheckBox.isChecked()){
            user.setRoles("Driver");
        }
        else{
            user.setRoles("Rider");
        }
        return g.toJson(user);




    }

    private void launchActiity(Class c) {
        Intent i = new Intent(SignUpActivity.this, c);
        i.putExtra("phoneNumber", phoneNumberstr);
        i.putExtra("groupCode", codeED.getText().toString());
        startActivity(i);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


    }

    }