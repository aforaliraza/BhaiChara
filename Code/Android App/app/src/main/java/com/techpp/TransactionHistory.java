package com.techpp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.RidesAdapter;
import adapter.TransactionHistoryAdapter;
import modal.RideHistoryModal;
import modal.TransactionHistoryModal;
import modal.User;
import utils.ApiUtils;
import utils.AppUtils;

public class TransactionHistory extends AppCompatActivity implements View.OnClickListener, FutureCallback<String> {

    ListView transactionHistory;
    TextView titleTV;
    Button showButtonBT;
    TransactionHistoryAdapter transactionHistoryAdapter;
    public static final String TAG = "TransactionHistory";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        transactionHistory = findViewById(R.id.transactions_list);
        titleTV = findViewById(R.id.title_transactionHistory);
        showButtonBT = findViewById(R.id.showtransactionHistory_transactionHistory);
        showButtonBT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.showtransactionHistory_transactionHistory){
            showTransactionHistory();
        }
        else{

        }
    }

    private void showTransactionHistory() {
        Log.i(TAG, "in show transactionhistory");
        String userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT, "");
        User user = (User) AppUtils.convertJsonToObject(userObj, User.class);
        Map<String, String> params = new HashMap<String, String>();
        params.put("mobileNumber", user.getMobileNumber());
        ApiUtils.callAPI(getApplicationContext(), "getTransactionHistory", params, this);
    }



    @Override
    public void onCompleted(Exception e, String result) {
        Log.i(TAG, "in oncompleted of transaction history");
        JsonObject responseJson = null;
        String responseCode = null;
        String payLoad = null;
        if (!AppUtils.isNull(e)) {
            Log.e(TAG, "some error occured", e);
            return;
        }

        Log.i(TAG, result);
        Toast.makeText(TransactionHistory.this, "task is completed", Toast.LENGTH_LONG).show();
        responseJson = AppUtils.parseServerResponse(result);
        if (!AppUtils.isNull(responseJson)) {
            responseCode = responseJson.get("responseCode").getAsString();
            Log.i(TAG, responseCode);
            if ("00".equals(responseCode)) {
                Log.i(TAG, responseJson.toString());
                Log.i(TAG, responseJson.get("payload").toString());
                payLoad = responseJson.getAsJsonArray("payload").toString();
                Log.i(TAG, payLoad);
                Type transactionHistoryClassType = new TypeToken<ArrayList<TransactionHistoryModal>>(){}.getType();
                List<TransactionHistoryModal> transactionHistoryList = (ArrayList<TransactionHistoryModal>)AppUtils.convertJsonToObject(payLoad, transactionHistoryClassType);
                if(!transactionHistoryList.isEmpty()) {
                    transactionHistoryAdapter = new TransactionHistoryAdapter(getApplicationContext(), transactionHistoryList);
                    transactionHistory.setAdapter(transactionHistoryAdapter);
                }else {
                    Toast.makeText(TransactionHistory.this, "No data Found", Toast.LENGTH_LONG).show();
                }

            }

            else if(responseCode.equals("02")){
                Toast.makeText(TransactionHistory.this, "No Transaction History Exist", Toast.LENGTH_LONG).show();
            }

            else {
                Toast.makeText(TransactionHistory.this, "Failed To Execute", Toast.LENGTH_LONG).show();
            }
        }


    }
}
