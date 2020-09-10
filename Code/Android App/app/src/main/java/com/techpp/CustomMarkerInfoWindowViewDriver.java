package com.techpp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

import modal.Driver;
import utils.AppUtils;

public class CustomMarkerInfoWindowViewDriver implements GoogleMap.InfoWindowAdapter {
    private Context context;
    private final View markerItemView;
    List<Driver> driversList;
    MarkerCallBack markerCallBack;

    public CustomMarkerInfoWindowViewDriver(Context context, MarkerCallBack markerCallBack) {

        this.context = context.getApplicationContext();
        this.markerCallBack = markerCallBack;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        markerItemView = layoutInflater.inflate(R.layout.marker, null);

    }

    @Override
    public View getInfoWindow(Marker arg0) {
        return null;
            }

    @Override
    public View getInfoContents(Marker arg0) {
       // ImageView iv = markerItemView.findViewById(R.id.pic_marker_info);
        Driver driver = (Driver) arg0.getTag();

        if(AppUtils.isNull(driver)){
            return null;
        }
         //       ImageView iv = markerItemView.findViewById(R.id.pic_marker_info);
                TextView driverNameTextView = markerItemView.findViewById(R.id.drivers_name_marker);
                TextView carTypeTextView =  markerItemView.findViewById(R.id.car_type_marker);
                TextView carNumberTextView =  markerItemView.findViewById(R.id.car_number_marker);
                TextView seatsAvailableTextView =  markerItemView.findViewById(R.id.seats_available_marker);
                driverNameTextView.setText("DRIVER NAME IS: "+driver.getUser().getFirstName()+" "+driver.getUser().getLastName());
                carTypeTextView.setText("CAR TYPE IS: "+""+driver.getVehicle().getCarType());
                carNumberTextView.setText("CAR NUMBER IS:"+" "+driver.getVehicle().getCarNumber());
                seatsAvailableTextView.setText("SEATS AVAILABLE ARE:"+" "+driver.getVehicle().getSeatAvailable());
        markerCallBack.markerClickDriver(driver);
        return markerItemView;  // 4



    }
    public void setDriversList(List<Driver> driversList){
        this.driversList = driversList;
    }

    public void callBack(){

    }

}