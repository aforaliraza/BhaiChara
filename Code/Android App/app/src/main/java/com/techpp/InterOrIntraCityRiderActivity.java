package com.techpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InterOrIntraCityRiderActivity extends AppCompatActivity implements View.OnClickListener {

    TextView interCityTV, intraCityTV, titleTV;
    ImageView bornToRideIV;
    View dividerTitleImageV, dividerImageTextViewV, dividerTextViewV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_intra_city_rider);
        interCityTV = findViewById(R.id.inter_city_intra_inter_rider_tv);
        intraCityTV = findViewById(R.id.intra_city_ride_inter_intra_rider_tv);
        titleTV = findViewById(R.id.bhaichara_interintra_rider);
        bornToRideIV = findViewById(R.id.live_to_ride_interintrarider);
        dividerTitleImageV = findViewById(R.id.titleimage_blankview_interintra_rider);
        dividerImageTextViewV = findViewById(R.id.divider_borntoride_tv_intrainterrider);
        dividerTextViewV = findViewById(R.id.divider_intrainter__intrainterrider);

        interCityTV.setOnClickListener(this);
        intraCityTV.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.inter_city_intra_inter_rider_tv) {
            launchActiity(InterCityRiderActivity.class);
        } else if (v.getId() == R.id.intra_city_ride_inter_intra_rider_tv) {
            launchActiity(MapsActivity.class);
        }

        }

    private void launchActiity (Class c){
        Intent i = new Intent(InterOrIntraCityRiderActivity.this, c);
        startActivity(i);

    }
}