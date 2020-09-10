package com.techpp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

import modal.Driver;
import modal.Rider;
import utils.AppUtils;

public class CustomMarkerInfoWindowViewRider implements GoogleMap.InfoWindowAdapter {
    private Context context;
    private final View markerItemView;
    List<Driver> driversList;
    MarkerCallBack markerCallBack;

    public CustomMarkerInfoWindowViewRider(Context context, MarkerCallBack markerCallBack) {

        this.context = context.getApplicationContext();
        this.markerCallBack = markerCallBack;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        markerItemView = layoutInflater.inflate(R.layout.marker_rider, null);

    }

    @Override
    public View getInfoWindow(Marker arg0) {
        return null;
            }

    @Override
    public View getInfoContents(Marker arg0) {
       // ImageView iv = markerItemView.findViewById(R.id.pic_marker_info);
        Rider rider = (Rider) arg0.getTag();

        if(AppUtils.isNull(rider)){
            return null;
        }
         //       ImageView iv = markerItemView.findViewById(R.id.pic_marker_info);
                TextView requestRideTextView = markerItemView.findViewById(R.id.request_ride_ridermarker);
                TextView phoneNumberTextView =  markerItemView.findViewById(R.id.phonenumber_marker_rider);
                TextView typeOfRideTextView =  markerItemView.findViewById(R.id.typeofride_marker_rider);
                //driverNameTextView.setText("DRIVER NAME IS: "+driver.getUser().getFirstName()+" "+driver.getUser().getLastName());
                phoneNumberTextView.setText("Rider phone number is: "+ rider.getMobileNumber());
                if(rider.getTypeOfRide().equals("1")){
                typeOfRideTextView.setText("Type of ride is: "+"HOME TO WORK");
                }
                else if(rider.getTypeOfRide().equals("2")){
                    typeOfRideTextView.setText("Type of ride is: "+" WORK TO HOME");
                }
                else if(rider.getTypeOfRide().equals("3")){
                    typeOfRideTextView.setText("Type of ride is: "+" INTERCITY");
                }
                else{

                }
         markerCallBack.markerClickRider(rider);
        return markerItemView;  // 4



    }
    public void setDriversList(List<Driver> driversList){
        this.driversList = driversList;
    }

    public void callBack(){

    }

}