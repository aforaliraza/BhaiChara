package adapter;

import android.app.usage.UsageStats;
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
import modal.TransactionHistoryModal;
import modal.User;
import utils.AppUtils;

public class TransactionHistoryAdapter extends BaseAdapter {
    private Context context;
    private List<TransactionHistoryModal> transactionHistoryList; //data source of the list adapter
    private final String TAG = "TransactionHistoryAdapter";

    public TransactionHistoryAdapter(Context context, List<TransactionHistoryModal> transactionHistoryList) {
        this.context = context;
        this.transactionHistoryList = transactionHistoryList;
    }

    @Override
    public int getCount() {
        return transactionHistoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return transactionHistoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).
                inflate(R.layout.transaction_history_adapter, parent, false);


        // get current item to be displayed
        TransactionHistoryModal transactionHistoryModal = (TransactionHistoryModal) getItem(position);

        // get the TextView for item name and item description
        TextView phoneNumberHolderTV = (TextView)
                convertView.findViewById(R.id.phoneNumberholderTransactionAdapter);
        TextView phoneNumberTV = (TextView)
                convertView.findViewById(R.id.phoneNumberTransactionHistory);
        TextView amountHolderTV = (TextView)
                convertView.findViewById(R.id.amountholderTransactionAdapter);
        TextView amountTV = (TextView)
                convertView.findViewById(R.id.amountTransactionHistory);
        ImageView clockIV = (ImageView)
                convertView.findViewById(R.id.clockblack);
        ImageView dollarIV = (ImageView)
                convertView.findViewById(R.id.dollar);

        String jsonObject = AppUtils.getFromSharedPrefs(AppUtils.USER_OBJECT, "");
        User user = (User) AppUtils.convertJsonToObject(jsonObject, User.class);


        phoneNumberHolderTV.setText(user.getFirstName()+ " " +user.getLastName());
        phoneNumberTV.setText(transactionHistoryModal.getMobileNumber());
        amountHolderTV.setText("The amount was:  ");
        amountTV.setText(transactionHistoryModal.getAmount());


        // returns the view for the current row
        return convertView;

    }
}
