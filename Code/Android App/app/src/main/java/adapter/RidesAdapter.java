package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.techpp.R;
import com.techpp.RideHistory;

import java.util.ArrayList;
import java.util.List;

import modal.RideHistoryModal;

public class RidesAdapter extends BaseAdapter {
    private Context context;
    private List<RideHistoryModal> rideHistoryList; //data source of the list adapter
    private final String TAG = "RidesAdapter";

    public RidesAdapter(Context context, List<RideHistoryModal> rideHistoryList) {
        this.context = context;
        this.rideHistoryList = rideHistoryList;
    }

    @Override
    public int getCount() {
        return rideHistoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return rideHistoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            convertView = LayoutInflater.from(context).
                    inflate(R.layout.rides_adapter, parent, false);


        // get current item to be displayed
        RideHistoryModal rides = (RideHistoryModal) getItem(position);

        // get the TextView for item name and item description
        TextView startLatTV = (TextView)
                convertView.findViewById(R.id.startLatRidesHistoryAdaoter);
        TextView startLongTV = (TextView)
                convertView.findViewById(R.id.startLong_ridesHistoryAdapter);
        TextView endLatTV = (TextView)
                convertView.findViewById(R.id.endLat_ridesHistoryAdapter);
        TextView endLongTV = (TextView)
                convertView.findViewById(R.id.endLong_ridesHistoryModel);
        TextView dateTV = (TextView)
                convertView.findViewById(R.id.date_ridesHistoryAdapter);
        TextView timeTV = (TextView)
                convertView.findViewById(R.id.time_ridesHistoryAdapter);

        startLongTV.setText("Long: " + rides.getStartLong());
        startLatTV.setText("Lat: "+ rides.getStartLat());
        endLatTV.setText("End Lat: "+ rides.getEndLat());
        endLongTV.setText("End Long: "+ rides.getEndLong());
        dateTV.setText("Dated: "+ rides.getStartDate());
        timeTV.setText("Time: "+ rides.getStartTime());


        //sets the text for item name and item description from the current item objects
        Log.i(TAG , "this is the msg");

       // Log.i(TAG , "names"+textViewItemName+textViewItemDescription);

        // returns the view for the current row
        return convertView;

    }
}
