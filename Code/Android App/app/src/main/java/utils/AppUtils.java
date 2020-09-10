package utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;
import java.util.List;

import modal.Driver;
import modal.Rider;

public class AppUtils {
    public final static String USER_OBJECT = "userObj";
    public final static String DRIVER_OBJECT = "driverObj";
    public final static String RIDE_OBJECT = "rideObj";
    public final static String INTERCITYRIDES_RIDER = "interCityRidesRider";
    public final static String INTERCITYRIDES_Driver = "interCityRidesDriver";
    private static SharedPreferences sharedPrefs;
    private final static String SHARED_PREF_NAME = "bhaichara";
    private static final String TAG = "AppUtils";
    public final static String DRIVER_PHONENUMBER = "driverPhoneNumber";

    public static void initSharedPrefs(Context context){
        sharedPrefs = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void putInSharedPrefs(String key, String value){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(key, value);
        editor.commit();

    }

    public static String getFromSharedPrefs(String key, String defValue){
        if(isNull(sharedPrefs)){
            Log.i(TAG, "Shared prefs is nulls");
        }
        return sharedPrefs.getString(key, defValue);
    }

    public static boolean isNullOrEmptyString(String str){
        if(null == str || "".equals(str))
            return true;
        return false;
    }
    public static boolean isValidLenght(String value, int length) {
        if(isNullOrEmptyString(value) || value.length() < length)
            return false;
        return true;
    }
    public static boolean isNull(Object obj) {
        if(null == obj ){
            return true;
        }
        return false;
    }

    public static JsonObject parseServerResponse(String result){
        JsonObject jsonObject = null;
        try {
            jsonObject = new JsonParser().parse(result).getAsJsonObject();
        }
        catch (Exception e){
            Log.e(TAG, "Some error occured while", e);
        }
        return jsonObject;
    }

    public static Object convertJsonToObject(String payload, Class c){
        Gson g = new Gson();
        return g.fromJson(payload, c);
    }

    public static Object convertJsonToObject(String payload, Type type){
        Gson g = new Gson();
        return g.fromJson(payload, type);
    }
    public static boolean isNullOrEmptyDriverList(List<Driver> list){
      if ( list.isEmpty()){
          return true;
      }
        return false;
    }
    public static boolean isNullOrEmptyMarkerList(List<Marker> list){
        if ( list.isEmpty()){
            return true;
        }
        return false;

    }

    public static boolean isNullOrEmptyList(List<Rider> list){
        if ( list.isEmpty()){
            return true;
        }
        return false;

    }
}
