package web.socket.handler;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.techpp.R;
import org.java_websocket.WebSocket;
import rx.Observable;
import rx.Subscriber;
import ua.naiksoftware.stomp.LifecycleEvent;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.client.StompClient;
import ua.naiksoftware.stomp.client.StompMessage;
import utils.AppUtils;

public class WebSocketHandler extends AppCompatActivity {

    TextView msgTv;
    Button sendBt;
    private static final String TAG = "WebSocketHandler";

    StompClient mStompClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_socket_handler);
        msgTv = findViewById(R.id.textviewhandler);
        sendBt = findViewById(R.id.sendbtn);

        sendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStompClient.send("/websocket/getMsg/1", "Mobile msg");
                if(mStompClient.isConnected()){
                    Toast.makeText(WebSocketHandler.this, "Web Socket still connected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(WebSocketHandler.this, "Web Socket disconnected", Toast.LENGTH_SHORT).show();
                }
            }
        });
        inItWebSocket();
    }

    private void inItWebSocket(){
        Log.i(TAG, "first line for inITWebSocket");

        // ...
        setMessage("Connecting");
        mStompClient = Stomp.over(WebSocket.class, "ws://192.168.10.12:8080/startwebsocket/websocket");

        subscribe();
        mStompClient.send("/websocket/getMsg/1", "Mobile msg");

        mStompClient.lifecycle().subscribe(lifecycleEvent -> {
            switch (lifecycleEvent.getType()) {
                case OPENED:
                    Log.i
                            (TAG, "Stomp connection opened");
                    setMessage("OPENED");

                    break;
                case ERROR:
                    Log.i(TAG, "Stomp connection error");
                    setMessage("Error");
                    break;
                case CLOSED:
                    Log.i(TAG, "Stomp connection closed");
                    setMessage("Closed");
                    break;
            }
        });

        Log.i(TAG, "above connect function");
//        subscribe();
        mStompClient.connect();

        Log.i(TAG, "after connect function");
    }

    private void subscribe(){
        Log.i(TAG, "Subscribing to a channel ");
//        mStompClient.topic("/driver/1").subscribe(topicMessage -> {
//            Log.i(TAG, "*************Received " + topicMessage.getPayload());
//            setMessage(topicMessage.getPayload());
//        }, throwable -> {
//            Log.i(TAG, "Error on subscribe topic");
//            Log.e(TAG, "Error on subscribe topic", throwable);
//        });

        mStompClient.topic("/driver/1").subscribe(new Subscriber<StompMessage>() {
            @Override
            public void onCompleted() {
                setMessage("On complete");
            }

            @Override
            public void onError(Throwable e) {
                setMessage("on error");
            }

            @Override
            public void onNext(StompMessage stompMessage) {
                setMessage("On Next");
            }
        });
        Log.i(TAG, "Subscribing Done, wait for response");
    }

    private void setMessage(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                msgTv.setText(text);
            }
        });
    }
}
