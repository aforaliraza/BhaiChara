package utils;

import android.content.Context;
import android.util.Log;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.Builders;
import java.util.Map;

public class ApiUtils {
    private static final String TAG = "ApiUtils";
      public final static String API_URL = "http://192.168.10.10:8080/";
    // 192.168.10.8

    public static void callAPI(Context context, String url, Map<String, String> params, FutureCallback<String> callback){
        Log.i(TAG,ApiUtils.API_URL+url);
        Ion.getDefault(context).getConscryptMiddleware().enable(false);
        final Builders.Any.B load = Ion.with(context).load(ApiUtils.API_URL + url);
        for (Map.Entry<String, String> entry : params.entrySet()) {

            load.setBodyParameter(entry.getKey(), entry.getValue());
        }
        load.asString().setCallback(callback);

    }
}
