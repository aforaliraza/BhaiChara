package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.techpp.R;
import java.util.List;
import modal.InterCityRides;


public class InterCityDriversAdapter extends BaseAdapter {
    private Context context;
    private List<InterCityRides> rides; //data source of the list adapter
    private final String TAG = "InterCityDriversAdapter";

    public InterCityDriversAdapter(Context context, List<InterCityRides> rides) {
        this.context = context;
        this.rides = rides;
    }

    @Override
    public int getCount() {
        return rides.size();
    }

    @Override
    public Object getItem(int position) {
        return rides.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).
                inflate(R.layout.intercity_driversavailable_adapter, parent, false);


        // get current item to be displayed
        InterCityRides rides = (InterCityRides) getItem(position);

        // get the TextView for item name and item description
        TextView sourceTV = (TextView)
                convertView.findViewById(R.id.sourceICRAdapter);
        TextView destinationTV = (TextView)
                convertView.findViewById(R.id.destination_ICRAdapter);
        TextView carTypeTV = (TextView)
                convertView.findViewById(R.id.car_type_ICRAdapter);
        TextView carNumberTV = (TextView)
                convertView.findViewById(R.id.car_number_ICRAdapter);
        TextView dateTV = (TextView)
                convertView.findViewById(R.id.date_ICRAdapter);
        TextView timeTV = (TextView)
                convertView.findViewById(R.id.time_ICRAdapter);
        TextView mobileNumberTV = (TextView)
                convertView.findViewById(R.id.phoneNumber_ICRAdapter);
        TextView seatsAvailableTV = (TextView)
                convertView.findViewById(R.id.seats_ICRAdapter);

        ImageView location = (ImageView)
                convertView.findViewById(R.id.location);
        ImageView car = (ImageView)
                convertView.findViewById(R.id.car);
        ImageView calenar = (ImageView)
                convertView.findViewById(R.id.calendar);
        ImageView mobile = (ImageView)
                convertView.findViewById(R.id.mobile);



        //sets the text for item name and item description from the current item objects
        Log.i(TAG , "this is the msg");
        sourceTV.setText("From: "+" "+rides.getSource());
        destinationTV.setText("To : " +" "+rides.getDestination());
        dateTV.setText("Date: "+" "+rides.getDate());
        timeTV.setText("Time: "+" "+rides.getTime());
        carTypeTV.setText("Car Type: "+" "+"Civic");
        carNumberTV.setText("Car Number: "+" "+ "Lez 1212");
        mobileNumberTV.setText("Call: "+" "+rides.getMobileNumber());
        seatsAvailableTV.setText("Seats Available"+" "+rides.getSeatsCapacity());

        return convertView;

    }


}
