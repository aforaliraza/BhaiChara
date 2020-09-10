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
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modal.Ride;
import modal.User;
import utils.ApiUtils;
import utils.AppUtils;

public class StartRide extends AppCompatActivity implements OnMapReadyCallback, LocationListener, View.OnClickListener, FutureCallback<String> {

    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    LocationRequest mLocationRequest;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    FusedLocationProviderClient mFusedLocationClient;
    TextView startARideTV, cancelRideTV;
    private static final String TAG = "StartRide";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_ride);

        startARideTV = findViewById(R.id.tv_start_a_ride_startride_activity);
        cancelRideTV = findViewById(R.id.tv_cancel_a_ride_startride_activity);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment_startride);
        mapFrag.getMapAsync(this);
        startARideTV.setOnClickListener(this);
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
            }

            else {
                //Request Location Permission
                checkLocationPermission();
            }
        }
        else {
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
                    mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);


                //move map camera
                //mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));
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
                                ActivityCompat.requestPermissions(StartRide.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
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
            mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);


        //markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.markerp));
        //mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_start_a_ride_startride_activity){
            createNewRideAccount();
            Toast.makeText(StartRide.this, "We did it boo",Toast.LENGTH_LONG).show();
        }
              else if(v.getId() == R.id.tv_cancel_a_ride_startride_activity) {
                  createNewRideAccount();
                  launchActiity(PrefrenceActivity.class);

        }


    }
        private String createRideObject(){
        Ride ride = new Ride();
        Gson g = new Gson();
     //   Log.i(TAG, AppUtils.getFromSharedPrefs(AppUtils.USER_MOBILE_NUMBER, "numberNotA"));
        ride.setMobileNumber("1234789");
        Log.i(TAG, "Latitide     :"+mLastLocation.getLatitude());
        ride.setStartLat(""+mLastLocation.getLatitude());
        Log.i(TAG, "longitude   :"+mLastLocation.getLongitude());
        ride.setStartLong(""+mLastLocation.getLongitude());
        ride.setComment("");
        ride.setRideStatus("");
        ride.setRideType("");
        ride.setEndLat("");
        ride.setEndLong("");

        return g.toJson(ride);
    }
    @Override
    public void onCompleted(Exception e, String result) {
        Log.i(TAG,"in oncompleted of SUA");
        if(AppUtils.isNull(e)) {
            Log.i(TAG, result);
            Toast.makeText(StartRide.this, "task is completed", Toast.LENGTH_LONG).show();
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
//                    AppUtils.putInSharedPrefs(AppUtils.RIDE_OBJECT,createRideObject());
                    Intent i = new Intent(StartRide.this, DriverMapsActivity.class);
                    Log.i(TAG, responseCode+" "+ "responseCode");
                    startActivity(i);
                    Toast.makeText(StartRide.this, "It worked boo" ,Toast.LENGTH_LONG).show();
                    Log.i(TAG, "completed succesfully StartRide");
                } else {
                    Toast.makeText(StartRide.this,"Some error occured",Toast.LENGTH_LONG).show();
                    Log.e(TAG,"Some error occured",e);

                }
            }
        } else {
            Toast.makeText(StartRide.this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(TAG, "onCompleted: ", e);
        }
    }
    private void createNewRideAccount(){
        Log.i(TAG,"in create user account of SUA");
        Map<String, String> params  = new HashMap<String, String>();
     //   Log.i(TAG, AppUtils.getFromSharedPrefs(AppUtils.USER_MOBILE_NUMBER, "no valueNo Boo"));
        String userObj;
        Log.i(TAG, AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT, " ")+"getting userObj");
        userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT,"");
        User user = (User)AppUtils.convertJsonToObject(userObj, User.class);
        params.put("mobileNumber", user.getMobileNumber());
        Log.i(TAG, ""+mLastLocation.getLatitude());
        params.put("startLat", ""+mLastLocation.getLatitude());
        Log.i(TAG, ""+mLastLocation.getLongitude());
        params.put("startLong", ""+mLastLocation.getLongitude());
        params.put("endLat", "00" );
        params.put("endLong", "00");
        params.put("rideType", " ");
        params.put("rideStatus", " ");
        params.put("comment", " ");
        ApiUtils.callAPI(getApplicationContext(), "ridestart", params, this);
    }

    private void launchActiity(Class c) {
        Intent i = new Intent(StartRide.this, c);
        startActivity(i);

    }




}






