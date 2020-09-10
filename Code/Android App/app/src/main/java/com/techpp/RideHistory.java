package com.techpp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
import adapter.RidesAdapter;
import modal.InterCityRides;
import modal.RideHistoryModal;
import modal.User;
import utils.ApiUtils;
import utils.AppUtils;

public class RideHistory extends AppCompatActivity implements FutureCallback<String>, View.OnClickListener {
    ListView listView;
    TextView titleTV;
    Button showRideHistoryBT;
    RidesAdapter ridesAdapter;
    public static final String TAG = "RideHistory";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_history);
        listView = findViewById(R.id.rides_list);
        titleTV = findViewById(R.id.title_rideHistory);
        showRideHistoryBT = findViewById(R.id.showRideHistory_rideHistory);
        showRideHistoryBT.setOnClickListener(this);
    }

    private void showRideHistory() {
        Log.i(TAG, "in ride request Rider Intercity");
        String userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT, "");
        User user = (User) AppUtils.convertJsonToObject(userObj, User.class);

        Map<String, String> params = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        params.put("mobileNumber", user.getMobileNumber());
        ApiUtils.callAPI(getApplicationContext(), "rideHistory", params, this);
    }




    @Override
    public void onCompleted(Exception e, String result) {
        Log.i(TAG, "in oncompleted of ride history");
        JsonObject responseJson = null;
        String responseCode = null;
        String payLoad = null;
        if (!AppUtils.isNull(e)) {
            Log.e(TAG, "some error occured", e);
            return;
        }

        Log.i(TAG, result);
        Toast.makeText(RideHistory.this, "task is completed", Toast.LENGTH_LONG).show();
        responseJson = AppUtils.parseServerResponse(result);
        if (!AppUtils.isNull(responseJson)) {
            responseCode = responseJson.get("responseCode").getAsString();
            Log.i(TAG, responseCode);
            if ("00".equals(responseCode)) {
                Log.i(TAG, responseJson.toString());
                Log.i(TAG, responseJson.get("payload").toString());
                payLoad = responseJson.getAsJsonArray("payload").toString();
                Log.i(TAG, payLoad);
                Type rideHistoryClassType = new TypeToken<ArrayList<RideHistoryModal>>(){}.getType();
                List<RideHistoryModal> ridesHistoryList = (ArrayList<RideHistoryModal>)AppUtils.convertJsonToObject(payLoad, rideHistoryClassType);
                if(!ridesHistoryList.isEmpty()) {
                    ridesAdapter = new RidesAdapter(getApplicationContext(), ridesHistoryList);
                    listView.setAdapter(ridesAdapter);
                }
                Toast.makeText(RideHistory.this, "No data Found", Toast.LENGTH_LONG).show();


            }
            else {
                Toast.makeText(RideHistory.this, "Failed To Execute", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.showRideHistory_rideHistory){
            showRideHistory();
        }
        else{

        }

    }
}
