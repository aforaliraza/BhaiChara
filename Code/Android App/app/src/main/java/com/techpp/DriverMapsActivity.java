package com.techpp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modal.Driver;
import modal.Rider;
import modal.User;
import utils.ApiUtils;
import utils.AppUtils;
import utils.RideStatus;
import utils.RideTypes;


public class DriverMapsActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener, View.OnClickListener,
        FutureCallback<String>, MarkerCallBack {

    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    LocationRequest mLocationRequest;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    FusedLocationProviderClient mFusedLocationClient;
    CustomMarkerInfoWindowViewRider markerWindowView = null;
    TextView proceedToEndRide, homeToWorkTV, workToHomeTV, intercityTV, driverRoleTV, riderRoleTV, refreshTV, confirmRideTV,
            cancelRideTV;
    Rider rider;
    Map<String, Double> driverDistanceTravelled = null;
    Double distanceTravelled;
    Location firstLocation;
    Driver driverObjForBilling;


    private static final String TAG = "DriverMapsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_maps);
        confirmRideTV = findViewById(R.id.tv_confirm_ride_maps_activity_driver);
        confirmRideTV.setVisibility(View.INVISIBLE);
        cancelRideTV = findViewById(R.id.tv_cancel_ride_maps_activity_driver);
       cancelRideTV.setVisibility(View.INVISIBLE);
        proceedToEndRide = findViewById(R.id.proceed_to_endRide_maps_activity_driver);
        refreshTV = findViewById(R.id.tv_refresh__maps_activity_driver);
        homeToWorkTV = findViewById(R.id.tv_home_to_work_maps_activity_driver);
        homeToWorkTV.setEnabled(false);
        workToHomeTV = findViewById(R.id.tv_work_home__maps_activity_driver);
        workToHomeTV.setEnabled(false);
        intercityTV = findViewById(R.id.tv_intercity_maps_activity_driver);
        intercityTV.setEnabled(false);
        driverRoleTV = findViewById(R.id.driver_mapsactivity_driver);
        riderRoleTV = findViewById(R.id.rider_mapsactivity_driver);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_driver_final);
        mapFrag.getMapAsync(this);
        mapFrag.getMapAsync( googleMap -> {
            markerWindowView = new CustomMarkerInfoWindowViewRider(getApplicationContext(), this);
            googleMap.setInfoWindowAdapter(markerWindowView);
        });


        proceedToEndRide.setOnClickListener(this);
        homeToWorkTV.setOnClickListener(this);
        workToHomeTV.setOnClickListener(this);
        intercityTV.setOnClickListener(this);
        driverRoleTV.setOnClickListener(this);
        riderRoleTV.setOnClickListener(this);
        refreshTV.setOnClickListener(this);
        confirmRideTV.setOnClickListener(this);
        cancelRideTV.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000); // interval
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                mGoogleMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        } else {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
            mGoogleMap.setMyLocationEnabled(true);
        }
    }

    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            List<Location> locationList = locationResult.getLocations();
            if (locationList.size() > 0) {
                //The last location in the list is the newest
                Location location = locationList.get(locationList.size() - 1);

                //patch added at 11 august
                firstLocation = locationList.get(locationList.size()-1);
                //patch ended 11 august


                Log.i("MapsActivity", "Location: " + location.getLatitude() + " " + location.getLongitude());
                mLastLocation = location;
                if (mCurrLocationMarker != null) {
                    mCurrLocationMarker.remove();
                }

                //Place current location marker
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Current Position");
                int height = 90;
                int width = 90;
                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.markerc);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                mCurrLocationMarker = mGoogleMap.addMarker(markerOptions.draggable(true));

            }
        }
    };

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(DriverMapsActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    @Override
    public void onLocationChanged(Location location) {

        //patch added 11 august
        distanceTravelled = distance(firstLocation.getLatitude(), firstLocation.getLongitude(), location.getLatitude(), location.getLongitude());
        String userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT, "");
        User user = (User) AppUtils.convertJsonToObject(userObj, User.class);

        if(!AppUtils.isNull(driverObjForBilling)){
            driverDistanceTravelled.put(user.getMobileNumber(), distanceTravelled );
            if(!AppUtils.isNullOrEmptyList(driverObjForBilling.getRiders())){
                for (Rider rider: driverObjForBilling.getRiders()) {
                    driverDistanceTravelled.put(rider.getMobileNumber(), distanceTravelled);
                }
            }
        }
        firstLocation = location;
        //patch ended 11 august
        mLastLocation = location;







        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");

        int height = 90;
        int width = 90;
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.markerc);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions.draggable(false));
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.tv_home_to_work_maps_activity_driver) {
            Log.i(TAG, RideTypes.HOME_TO_WORK);
            bookARide(RideTypes.HOME_TO_WORK);
            homeToWorkTV.setBackgroundColor(getResources().getColor(R.color.red));
            homeToWorkTV.setTextColor(getResources().getColor(R.color.white));
            workToHomeTV.setBackgroundColor(getResources().getColor(R.color.white));
            workToHomeTV.setTextColor(getResources().getColor(R.color.black));
            intercityTV.setBackgroundColor(getResources().getColor(R.color.white));
            intercityTV.setTextColor(getResources().getColor(R.color.black));


        } else if (v.getId() == R.id.tv_work_home__maps_activity_driver) {
            Log.i(TAG, RideTypes.WORK_TO_HOME);
            bookARide(RideTypes.WORK_TO_HOME);
            workToHomeTV.setBackgroundColor(getResources().getColor(R.color.red));
            workToHomeTV.setTextColor(getResources().getColor(R.color.white));
            homeToWorkTV.setBackgroundColor(getResources().getColor(R.color.white));
            homeToWorkTV.setTextColor(getResources().getColor(R.color.black));
            intercityTV.setBackgroundColor(getResources().getColor(R.color.white));
            intercityTV.setTextColor(getResources().getColor(R.color.black));

        } else if (v.getId() == R.id.tv_intercity_maps_activity_driver) {
            Log.i(TAG, RideTypes.INTER_CITY + " " + "inter city");
            bookARide(RideTypes.INTER_CITY);
            intercityTV.setBackgroundColor(getResources().getColor(R.color.red));
            intercityTV.setTextColor(getResources().getColor(R.color.white));
            workToHomeTV.setBackgroundColor(getResources().getColor(R.color.white));
            workToHomeTV.setTextColor(getResources().getColor(R.color.black));
            intercityTV.setBackgroundColor(getResources().getColor(R.color.white));
            intercityTV.setTextColor(getResources().getColor(R.color.black));

        } else if (v.getId() == R.id.driver_mapsactivity_driver) {

        } else if (v.getId() == R.id.rider_mapsactivity_driver) {
            switchRoles();

        } else if (v.getId() == R.id.proceed_to_endRide_maps_activity_driver) {
            List<String> keySet = new ArrayList<String>(driverDistanceTravelled.keySet());
             BillingActivity.initilizeLists( keySet ,individualFare(valueList()));  ;
            launchActivity(EndRide.class);

        } else if (v.getId() == R.id.tv_refresh__maps_activity_driver) {
            refresh();

        }
        else if (v.getId() == R.id.tv_cancel_ride_maps_activity_driver) {
            confirmCancelRIde(RideStatus.CANCELLED);

        }
        else if (v.getId() == R.id.tv_confirm_ride_maps_activity_driver) {
            confirmCancelRIde(RideStatus.RIDE_CONFIRMED);

        }
        else {

        }
    }


    @Override
    public void onCompleted(Exception e, String result) {

        Log.i(TAG, "in oncompleted of mapsActivity driver");
        JsonObject responseJson = null;
        String responseCode = null;
        String requestType = null;
        String payLoad = null;
        if (!AppUtils.isNull(e)) {
            Log.e(TAG, "some error occured", e);
            return;
        }
        Log.i(TAG, result);
        Toast.makeText(DriverMapsActivity.this, "task is completed", Toast.LENGTH_LONG).show();
        responseJson = AppUtils.parseServerResponse(result);
        if (!AppUtils.isNull(responseJson)) {
            responseCode = responseJson.get("responseCode").getAsString();
            requestType = responseJson.get("requestType").getAsString();
            Log.i(TAG, responseCode);
            Log.i(TAG, requestType);
            if ("00".equals(responseCode)) {
                if ("01".equals(requestType)) {

                    Intent i = new Intent(DriverMapsActivity.this, MapsActivity.class);
                    startActivity(i);

                } else if ("07".equals(requestType)) {
                    Log.i(TAG, responseJson.toString());
                    Log.i(TAG, responseJson.get("payload").toString());
                    Log.i(TAG, requestType + "request type for  book a ride mapsActivity driver");
                    payLoad = responseJson.getAsJsonObject("payload").toString();
                    Driver driver1 = (Driver) AppUtils.convertJsonToObject(payLoad, Driver.class);
                    driverObjForBilling = driver1;
                    setRiderMarkers(driver1);
                    Log.i(TAG, driver1.getUser().getFirstName());

                } else if ("08".equals(requestType)) {
                    Log.i(TAG, responseJson.toString());
                    Log.i(TAG, responseJson.get("payload").toString());
                    Log.i(TAG, requestType + "request type for  book a ride mapsActivity driver");
                    payLoad = responseJson.getAsJsonObject("payload").toString();
                    Driver driver2 = (Driver) AppUtils.convertJsonToObject(payLoad, Driver.class);


                    Toast.makeText(DriverMapsActivity.this, "Refreshed Successfully", Toast.LENGTH_LONG).show();

                }
            }
            Log.i(TAG, "completed succesfully");
            return;
        } else {
            Toast.makeText(DriverMapsActivity.this, "Some error occured last else", Toast.LENGTH_LONG).show();
            Log.e(TAG, "Some error occured last else", e);
        }
    }

    private void bookARide(String rideType) {
        String userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT, "");
        String driverMobileNumber = AppUtils.getFromSharedPrefs(AppUtils.DRIVER_PHONENUMBER, "");
        User user = (User) AppUtils.convertJsonToObject(userObj, User.class);
        Map<String, String> params = new HashMap<String, String>();
        Log.i(TAG, "in book a ride of maps activity");
        Log.i(TAG, user.getMobileNumber() + "user mobilenumber book a ride");
        Log.i(TAG, driverMobileNumber + "" + "driver mobile number book a ride");

        params.put("mobilenumuser", rider.getMobileNumber() );
        params.put("mobilenumdriver", user.getMobileNumber());
        params.put("pickuplat", mLastLocation.getLatitude() + "");
        params.put("pickuplong", mLastLocation.getLongitude() + "");
        params.put("destinationlat", "00");
        params.put("destinationlong", "00");
        params.put("groupcodeuser", user.getGroupCode());
        params.put("rideType", rideType);
        ApiUtils.callAPI(getApplicationContext(), "bookaride", params, this);
    }

    private void switchRoles() {

        String userObj;
        userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT, "");
        User user = (User) AppUtils.convertJsonToObject(userObj, User.class);
        Map<String, String> params = new HashMap<String, String>();
        params.put("mobileNumber", user.getMobileNumber());
        params.put("firstName", user.getFirstName());
        params.put("lastName", user.getLastName());
        params.put("emailId", user.getEmailId());
        params.put("groupCode", user.getGroupCode());
        params.put("roles", "Rider");
        ApiUtils.callAPI(getApplicationContext(), "changeroles", params, this);
    }

    private void refresh() {

        String userObj;
        userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT, "");
        String driverMobileNumber = AppUtils.getFromSharedPrefs(AppUtils.DRIVER_PHONENUMBER, "");
        User user = (User) AppUtils.convertJsonToObject(userObj, User.class);
        Map<String, String> params = new HashMap<String, String>();
        Log.i(TAG, user.getMobileNumber()+"refresh method");
        Log.i(TAG, user.getGroupCode()+"refresh method");
        params.put("groupCode", user.getGroupCode());
        params.put("mobileNumber", user.getMobileNumber());
        ApiUtils.callAPI(getApplicationContext(), "confirmRide", params, this);
    }
    private void confirmCancelRIde(String  rideStatus) {

        String userObj;
        userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT, "");
        User user = (User) AppUtils.convertJsonToObject(userObj, User.class);
        String driverMobileNumber = AppUtils.getFromSharedPrefs(AppUtils.DRIVER_PHONENUMBER, "");

        Map<String, String> params = new HashMap<String, String>();
        Log.i(TAG, user.getMobileNumber()+"refresh method");
        Log.i(TAG, user.getGroupCode()+"refresh method");
        params.put("groupCode", user.getGroupCode());
        params.put("mobileNumber", user.getMobileNumber());
        params.put("riderMobileNumber", rider.getMobileNumber() );
        params.put("rideStatus", rideStatus);
        ApiUtils.callAPI(getApplicationContext(), "confirmOrCancelRide", params, this);
    }

    public void setRiderMarkers(Driver driver) {
        Log.i(TAG, driver.getRiders().toString());
        Marker markerRiders = null;
        int height = 90;
        int width = 90;
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.markerp);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        String userObj;
        userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT, "");
        User user = (User) AppUtils.convertJsonToObject(userObj, User.class);


        if(!AppUtils.isNullOrEmptyList(driver.getRiders())){
        for(Rider rider : driver.getRiders()) {
            // if(rider.getMobileNumber().equals(user.getMobileNumber())) {
            LatLng latLng = new LatLng(Double.parseDouble(rider.getSrcLat()),
                    Double.parseDouble(rider.getSrcLong()));
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Rider");
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
            markerOptions.draggable(false);
            markerRiders = mGoogleMap.addMarker(markerOptions);
            markerRiders.setTag(rider);

            //}
        }

        }
        else{
            Toast.makeText(DriverMapsActivity.this, "No riders available", Toast.LENGTH_LONG).show();

        }

    }


    @Override
    public void markerClickDriver(Driver driver) {

    }

    @Override
    public void markerClickRider(Rider rider) {
        confirmRideTV.setVisibility(View.VISIBLE);
        cancelRideTV.setVisibility(View.VISIBLE);
        workToHomeTV.setEnabled(true);
        homeToWorkTV.setEnabled(true);
        intercityTV.setEnabled(true);
        this.rider = rider;

    }
//Gives displacement in Kilometers
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    private void launchActivity(Class c){
        Intent i = new Intent(DriverMapsActivity.this, c);
        startActivity(i);
    }

    private List<Double> valueList(){
        List<Double> value = new ArrayList<Double>(driverDistanceTravelled.values());
        return value;
      }


    private List<Double> individualFare(List<Double> distanceTravelled){
        double fareDriver = 0;
        double fareFirst = 0;
        double fareSecond = 0;
        double fareThird = 0;
        double fareFourth = 0;
        List<Double> costForTotalDistance = new ArrayList<Double>();

        for(int i=0; i<distanceTravelled.size(); i++){
            double outPut = distanceTravelled.get(i)*30;
            costForTotalDistance.add(i, outPut);
        }


        List<Double> farePartitionForAllRiders = new ArrayList<Double>();
        for(int i=0; i<costForTotalDistance.size(); i++){
            double firstFare;
            int denominator = i+1;

            firstFare = costForTotalDistance.get(i);
            firstFare = firstFare/denominator;
            farePartitionForAllRiders.add(i, firstFare);

        }

        List<Double> individualFare = new ArrayList<Double>();

        for(int i=0; i<farePartitionForAllRiders.size(); i++) {

            fareDriver = fareDriver + farePartitionForAllRiders.get(i);
        }
        for(int j =1; j<farePartitionForAllRiders.size(); j++){
            fareFirst = fareFirst + farePartitionForAllRiders.get(j);


        }  for(int k=2; k<farePartitionForAllRiders.size(); k++){
            //double sum = 0;
            fareSecond = fareSecond + farePartitionForAllRiders.get(k);


        }  for(int l=3; l<farePartitionForAllRiders.size(); l++){
            //double sum = 0;
            fareThird = fareThird + farePartitionForAllRiders.get(l);

        }   for(int m=4; m<farePartitionForAllRiders.size(); m++){
            //double sum = 0;
            fareFourth = fareFourth+ farePartitionForAllRiders.get(m);

        }   for(int n=5; n<farePartitionForAllRiders.size(); n++){
            //double sum = 0;
        }

        individualFare.add(fareDriver);
        individualFare.add(fareFirst);
        individualFare.add(fareSecond);
        individualFare.add(fareThird);
        individualFare.add(fareFourth);
        return individualFare;
    }
}