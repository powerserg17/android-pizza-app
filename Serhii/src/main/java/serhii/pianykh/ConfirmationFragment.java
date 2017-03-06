//Serhii Pianykh, #300907406, Assignment1
package serhii.pianykh;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by rider on 14.10.2016.
 */

public class ConfirmationFragment extends Fragment {

    private static final String ARG_ORDER="order_object";
    private Order mOrder;

    private TextView mDearView;
    private TextView mPriceView;
    private TextView mOrderView;

    public static ConfirmationFragment newInstance(Order order) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ORDER, order);

        ConfirmationFragment fragment = new ConfirmationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mOrder = (Order) getArguments().getSerializable(ARG_ORDER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_confirmation, container, false);

        mDearView = (TextView)v.findViewById(R.id.confirm_name_label);
        String name = mOrder.getFullName();
        int index = name.indexOf(" ");
        name = name.substring(0,index);
        mDearView.setText(mDearView.getText()+" "+name+",");

        mPriceView = (TextView)v.findViewById(R.id.confirm_price_label);
        mPriceView.setText(mPriceView.getText()+"$"+Double.toString(mOrder.getPrice()));

        mOrderView = (TextView)v.findViewById(R.id.confirm_order_label);
        mOrderView.setText(mOrderView.getText()+" "+mOrder.getId());

        return v;
    }

}
