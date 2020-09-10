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
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modal.Driver;
import modal.Rider;
import modal.User;
import utils.ApiUtils;
import utils.AppUtils;
import utils.RideTypes;

public class MapsActivity extends AppCompatActivity
        implements OnMapReadyCallback, LocationListener, View.OnClickListener, FutureCallback<String>,MarkerCallBack {

    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    LocationRequest mLocationRequest;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    Marker driverLocationMarker;
    FusedLocationProviderClient mFusedLocationClient;
    TextView bookARideTV, RiderTV, DriverTV, confirmDriverTV, homeToWorkTVM, workToHomeTVM, intercityTVM;
    //private static boolean roles = true;
    private static final String TAG = "MapsActivity";
    CustomMarkerInfoWindowViewDriver markerWindowView = null;
    List<Marker> driverMarkerId = new ArrayList<Marker>();
    Driver driver;
    String rideTypeString = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        homeToWorkTVM = findViewById(R.id.tv_home_to_work_maps_activity);
        workToHomeTVM = findViewById(R.id.tv_work_home_maps_activity);
        intercityTVM = findViewById(R.id.tv_intercity_maps_activity);
        bookARideTV = findViewById(R.id.textview_book_a_ride_maps_activity);
        confirmDriverTV = findViewById(R.id.textview_confirm_a_ride_maps_activity);
        confirmDriverTV.setVisibility(View.INVISIBLE);
        RiderTV = findViewById(R.id.rider_mapsactivity);
        DriverTV = findViewById(R.id.driver_mapsactivity);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);
        mapFrag.getMapAsync( googleMap -> {
            markerWindowView = new CustomMarkerInfoWindowViewDriver(getApplicationContext(), this);
        googleMap.setInfoWindowAdapter(markerWindowView);
        });
        bookARideTV.setOnClickListener(this);
        RiderTV.setOnClickListener(this);
        DriverTV.setOnClickListener(this);
        confirmDriverTV.setOnClickListener(this);
        homeToWorkTVM.setOnClickListener(this);
        workToHomeTVM.setOnClickListener(this);
        intercityTVM.setOnClickListener(this);

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
       // mGoogleMap.setOnMarkerClickListener(this);
       // mGoogleMap.setOnPoiClickListener(this);
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000); // interval
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        //2nd method
        //CustomMarkerInfoWindowView customMarkerInfoWindowView = new CustomMarkerInfoWindowView(getApplicationContext());
        //googleMap.setInfoWindowAdapter(customMarkerInfoWindowView);

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
                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.markerp);
                Bitmap b = bitmapdraw.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                mCurrLocationMarker = mGoogleMap.addMarker(markerOptions.draggable(true));

             /*   if(roles) {
                    //   markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                    //setting up a marker of custom size
                    int height = 90;
                    int width = 90;
                    BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.markerp);
                    Bitmap b = bitmapdraw.getBitmap();
                    Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                    markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                    mCurrLocationMarker = mGoogleMap.addMarker(markerOptions.draggable(true));
                }
                else{
                    int height = 90;
                    int width = 90;
                    BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.markerc);
                    Bitmap b = bitmapdraw.getBitmap();
                    Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                    markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                    mCurrLocationMarker = mGoogleMap.addMarker(markerOptions.draggable(false));

                }*/
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
                                ActivityCompat.requestPermissions(MapsActivity.this,
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
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.markerp);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions.draggable(true));
        /*if(roles) {
            int height = 90;
            int width = 90;
            BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.markerp);
            Bitmap b = bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
            mCurrLocationMarker = mGoogleMap.addMarker(markerOptions.draggable(true));
        }
        else{
            int height = 90;
            int width = 90;
            BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.markerc);
            Bitmap b = bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
            mCurrLocationMarker = mGoogleMap.addMarker(markerOptions.draggable(false));

        }*/
        //markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.markerp));
        //mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
    }

    @Override
    public void onClick(View v) {
       if(v.getId() == R.id.textview_book_a_ride_maps_activity){
           if(rideTypeString.equals("")){
               Toast.makeText(MapsActivity.this, "Kindly Select Ride Type From The Given Options", Toast.LENGTH_LONG).show();
           }
           else{
               findDrivers();
           }


       }
       else if(v.getId() == R.id.rider_mapsactivity){
/*           roles = true;
           updateRoles();
          // roleRider(); */
       }
      // else if(v.getId() == R.id.driver_mapsactivity&& !isFirstTime() ){
       else if(v.getId() == R.id.driver_mapsactivity){
           if(!isFirstTime()){
              // roles = false;
        updateRoles();
        //roleDriver();
           }
           else{
               Intent i  = new Intent(MapsActivity.this, DriverSignUp.class);
               startActivity(i);
           }
    }

       else if(v.getId() == R.id.textview_confirm_a_ride_maps_activity ){

               bookARide(rideTypeString);
       }
        else if(v.getId() == R.id.tv_home_to_work_maps_activity){
           rideTypeString = RideTypes.HOME_TO_WORK;
            homeToWorkTVM.setBackgroundColor(getResources().getColor(R.color.red));
            homeToWorkTVM.setTextColor(getResources().getColor(R.color.white));
            workToHomeTVM.setBackgroundColor(getResources().getColor(R.color.white));
            workToHomeTVM.setTextColor(getResources().getColor(R.color.black));
            intercityTVM.setBackgroundColor(getResources().getColor(R.color.white));
            intercityTVM.setTextColor(getResources().getColor(R.color.black));


        }
        else if(v.getId() == R.id.tv_work_home_maps_activity){
            Log.i(TAG, RideTypes.WORK_TO_HOME);
           rideTypeString = RideTypes.WORK_TO_HOME;
            workToHomeTVM.setBackgroundColor(getResources().getColor(R.color.red));
            workToHomeTVM.setTextColor(getResources().getColor(R.color.white));
            homeToWorkTVM.setBackgroundColor(getResources().getColor(R.color.white));
            homeToWorkTVM.setTextColor(getResources().getColor(R.color.black));
            intercityTVM.setBackgroundColor(getResources().getColor(R.color.white));
            intercityTVM.setTextColor(getResources().getColor(R.color.black));

        }
        else if(v.getId() == R.id.tv_intercity_maps_activity ){
            Log.i(TAG, RideTypes.INTER_CITY+" "+ "inter city");
            rideTypeString = RideTypes.INTER_CITY;
            intercityTVM.setBackgroundColor(getResources().getColor(R.color.red));
            intercityTVM.setTextColor(getResources().getColor(R.color.white));
            workToHomeTVM.setBackgroundColor(getResources().getColor(R.color.white));
            workToHomeTVM.setTextColor(getResources().getColor(R.color.black));
            intercityTVM.setBackgroundColor(getResources().getColor(R.color.white));
            intercityTVM.setTextColor(getResources().getColor(R.color.black));

        }

        else{

       }
    }
    private static boolean isFirstTime(){
        if(AppUtils.getFromSharedPrefs(AppUtils.RIDE_OBJECT," ").equals(" ")){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void onCompleted(Exception e, String result) {
        Log.i(TAG,"in oncompleted of mapsActivity");
        JsonObject responseJson = null;
        String responseCode = null;
        String requestType = null;
        String payLoad = null;
        if(!AppUtils.isNull(e)) {
            Log.e(TAG,"some error occured",e);
            return;
        }

        Log.i(TAG, result);
        Toast.makeText(MapsActivity.this, "task is completed", Toast.LENGTH_LONG).show();
            responseJson = AppUtils.parseServerResponse(result);
            if (!AppUtils.isNull(responseJson)) {
                responseCode = responseJson.get("responseCode").getAsString();
                requestType = responseJson.get("requestType").getAsString();
                Log.i(TAG, responseCode);
                Log.i(TAG, requestType);
                if ("00".equals(responseCode)) {
                    if("01".equals(requestType)){
                        Intent i = new Intent(MapsActivity.this, DriverMapsActivity.class);
                        startActivity(i);

                      /*  if(roles){
                            roleRider();
                        } else{
                            roleDriver();
                            Intent i = new Intent(MapsActivity.this, MapsActivityDriver.class);
                            startActivity(i);

                        }*/
                        //update roles
                    } else if("04".equals(requestType)) {
                         Log.i(TAG, responseJson.toString());
                         Log.i(TAG, responseJson.get("payload").toString());
                         Log.i(TAG, requestType);
                         payLoad = responseJson.getAsJsonArray("payload").toString();
                        Log.i(TAG, payLoad);
                        Type driversListClassType = new TypeToken<ArrayList<Driver>>(){}.getType();
                        List<Driver> driverList = (ArrayList<Driver>)AppUtils.convertJsonToObject(payLoad, driversListClassType);
                        Log.i(TAG, driverList.size()+"size of list returned");
                        markerWindowView.setDriversList(driverList);
                        setDriverMarkers(driverList);
                        Toast.makeText(MapsActivity.this, "Drivers available ",  Toast.LENGTH_SHORT).show();
                    }

                    //latest patch five july

                    else if("07".equals(requestType)) {
                        Log.i(TAG, responseJson.toString());
                        Log.i(TAG, responseJson.get("payload").toString());
                        Log.i(TAG, requestType+"request type for  book a ride");
                        payLoad = responseJson.getAsJsonObject("payload").toString();
                        Driver driver1 = (Driver) AppUtils.convertJsonToObject(payLoad, Driver.class);
                        Log.i(TAG, driver1.getUser().getFirstName());
                        Toast.makeText(MapsActivity.this, "Ride Confirmed with " + " " +
                        driver1.getUser().getFirstName() +" " + driver1.getUser().getLastName(), Toast.LENGTH_LONG).show();

                        confirmDriverTV.setText(driver1.getUser().getFirstName() +
                                " " + driver1.getUser().getLastName() + " " + "is On his way");

                    }
                    Log.i(TAG, "completed succesfully");
                    return;
                } else {
                    Toast.makeText(MapsActivity.this,"Some error occured last else",Toast.LENGTH_LONG).show();
                    Log.e(TAG,"Some error occured last else",e);
                }
            }
    }

    private void updateRoles(){

        String userObj;
        userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT, "");
        User user = (User)AppUtils.convertJsonToObject(userObj, User.class);
        Map<String, String> params  = new HashMap<String, String>();
        params.put("mobileNumber", user.getMobileNumber());
        params.put("firstName", user.getFirstName());
        params.put("lastName", user.getLastName());
        params.put("emailId", user.getEmailId());
        params.put("groupCode", user.getGroupCode());
        params.put("roles", "Driver");
       /* if(roles){
            params.put("roles", "Rider");
        }
        else{
            params.put("roles", "Driver");
        }*/
        ApiUtils.callAPI(getApplicationContext(), "changeroles", params, this);
    }

    private void findDrivers(){
        Log.i(TAG,"in create user account of SUA");
        String userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT, "");
        User user = (User)AppUtils.convertJsonToObject(userObj, User.class);
        Map<String, String> params  = new HashMap<String, String>();
        params.put("groupCode", user.getGroupCode());
        ApiUtils.callAPI(getApplicationContext(), "driversByGroupCode", params, this);
    }

    private void bookARide(String rideType){
        String userObj = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT, "");
        User user = (User)AppUtils.convertJsonToObject(userObj, User.class);
        Map<String, String> params  = new HashMap<String, String>();
        AppUtils.putInSharedPrefs(AppUtils.DRIVER_PHONENUMBER, driver.getMobileNumber());
        Log.i(TAG,user.getMobileNumber()+ "user mobile number in maps activity");
        Log.i(TAG,driver.getMobileNumber()+ "driver mobile number in maps activity");
        params.put("mobilenumuser", user.getMobileNumber());
        params.put("mobilenumdriver", driver.getMobileNumber());
        params.put("pickuplat", mLastLocation.getLatitude()+"");
        params.put("pickuplong", mLastLocation.getLongitude()+"");
        params.put("destinationlat", "00");
        params.put("destinationlong", "00");
        params.put("groupcodeuser", user.getGroupCode());
        params.put("rideType", rideType);
        ApiUtils.callAPI(getApplicationContext(), "bookaride", params, this);
    }

    // mobilenumuser&mobilenumdriver&pickuplat&pickuplong&destinationlat&destinationlong&groupcodeuser





    public void setDriverMarkers(List<Driver> driversAvailable){
        Marker marker = null;
        int height = 90;
        int width = 90;
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.markerc);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        if( AppUtils.isNullOrEmptyMarkerList(driverMarkerId)) {

            for (Driver driver : driversAvailable) {
                //mCurrLocationMarker.remove();
                LatLng latLng = new LatLng(Double.parseDouble(driver.getCurentLocationLat()), Double.parseDouble(driver.getCurentLocationLong()));
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(driver.getUser().getFirstName() + "" + driver.getUser().getLastName());
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                markerOptions.draggable(false);
                marker = mGoogleMap.addMarker(markerOptions);
                driverMarkerId.add(marker);
                marker.setTag(driver);
                Toast.makeText(MapsActivity.this, driverMarkerId.size()+"size", Toast.LENGTH_LONG).show();
            }
        }
        else{
            removeExisitingDriverMarkers();
            driverMarkerId.clear();

            for (Driver driver : driversAvailable) {
                //mCurrLocationMarker.remove();
                Toast.makeText(MapsActivity.this, driverMarkerId.size()+"size", Toast.LENGTH_LONG).show();
                LatLng latLng = new LatLng(Double.parseDouble(driver.getCurentLocationLat()), Double.parseDouble(driver.getCurentLocationLong()));
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(driver.getUser().getFirstName() + "" + driver.getUser().getLastName());
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                markerOptions.draggable(false);
                marker = mGoogleMap.addMarker(markerOptions);
                driverMarkerId.add(marker);
                marker.setTag(driver);
            }
        }
        }

        private void removeExisitingDriverMarkers(){
        for (Marker marker : driverMarkerId) {
            marker.remove();
        }
        }

    /*public void roleRider(){
        bookARideTV.setText("Find Drivers");
        bookARideTV.setBackgroundColor(getResources().getColor(R.color.green));
        DriverTV.setBackgroundColor(getResources().getColor(R.color.white));
        DriverTV.setTextColor(getResources().getColor(R.color.black));
        RiderTV.setBackgroundColor(getResources().getColor(R.color.green));
        RiderTV.setTextColor(getResources().getColor(R.color.white));
        roles = true;
        Toast.makeText(this,"You are in rider mode", Toast.LENGTH_LONG).show();
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
            LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");

            int height = 90;
            int width = 90;
            BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.markerp);
            Bitmap b = bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
            mCurrLocationMarker = mGoogleMap.addMarker(markerOptions.draggable(true));
        }
    }

    public void roleDriver() {

        bookARideTV.setText("Find Riders");
        RiderTV.setBackgroundColor(getResources().getColor(R.color.white));
        RiderTV.setTextColor(getResources().getColor(R.color.black));
        DriverTV.setBackgroundColor(getResources().getColor(R.color.green));
        DriverTV.setTextColor(getResources().getColor(R.color.white));
        Toast.makeText(this, "You are in Driver mode", Toast.LENGTH_LONG).show();
        roles = false;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
            LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
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
    }*/

    @Override
    public void markerClickDriver(Driver driver) {
        Log.i(TAG, " in markerClicks"+"      "+driver.toString());
        confirmDriverTV.setVisibility(View.VISIBLE);
        confirmDriverTV.setText("Want to confirm a ride with"+" "+driver.getUser().getFirstName()+" "+driver.getUser().getLastName()+"?");
        this.driver = driver;
    }

    @Override
    public void markerClickRider(Rider rider) {

    }
}