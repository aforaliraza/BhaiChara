package web.socket.handler;

import android.app.Activity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import java.net.URI;
import tech.gusavila92.websocketclient.WebSocketClient;
@Deprecated
public class WebSocketListener extends WebSocketClient {


    TextView textViewTV;
    URI uri;
    EditText editText;
    Activity activity;
    String TAG = "WebSocketListener";
    public WebSocketListener(URI uri, TextView tv, EditText editText, Activity activity){
        super(uri);
        this.uri = uri;
        this.editText = editText;
        textViewTV = tv;
        this.activity = activity;
    }


    @Override
    public void onOpen() {
        //send("/driver/12121");
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "onOpen");
                textViewTV.setText("in onOpen");
            }
        });

    }

    @Override
    public void onTextReceived(String message) {
        final String messageReceived = message;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                Log.i(TAG, "onTextReceived");
                textViewTV.setText("in onTextReceived"+" "+messageReceived );
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    public void onBinaryReceived(byte[] data) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "onBinaryReceivedd");
                textViewTV.setText("in onBinaryReceived");

            }
        });

    }

    @Override
    public void onPingReceived(byte[] data) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "onPingReceived");
                textViewTV.setText("in onPingReceived");
            }
        });

    }

    @Override
    public void onPongReceived(byte[] data) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "onPongReceived");
                textViewTV.setText("in onPongReceived");
            }
        });

    }

    @Override
    public void onException(Exception e) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "onException");
                textViewTV.setText("in onException"+"  "+ e);

            }
        });

    }

    @Override
    public void onCloseReceived() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "onCloseReceived");
                textViewTV.setText("in onCloseReceived");

            }
        });

    }
}
