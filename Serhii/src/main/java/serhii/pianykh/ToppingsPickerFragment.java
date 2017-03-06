//Serhii Pianykh, #300907406, Assignment1
package serhii.pianykh;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rider on 13.10.2016.
 */

public class ToppingsPickerFragment extends AppCompatDialogFragment {

    private static final String ARG_ORDER="order_object";
    public static final String EXTRA_ORDER="serhii.pianykh.order";

    private LinearLayout mLinearLayout;
    private String[] mToppings;
    private Order mOrder;
    private final double[] mToppingPriceList = new double[] {0.99, 1.99, 1.99, 0.99, 1.99, 0.99, 1.99, 1.99, 0.99, 2.99, 2.99, 2.99,
    2.99, 3.99, 2.99};

    static ToppingsPickerFragment newInstance(Order order) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_ORDER, order);

        ToppingsPickerFragment fragment = new ToppingsPickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOrder = (Order) getArguments().getSerializable(ARG_ORDER);
        System.out.println(mOrder.toString());
        System.out.println(mOrder.getDough());
        Resources res = getResources();
        mToppings = res.getStringArray(R.array.topping_list);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_toppings, null);

        mLinearLayout = (LinearLayout)v.findViewById(R.id.dialog_toppings);

        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        CheckBox[] mCheckBoxes = new CheckBox[mToppings.length];
        //System.out.println(mOrder.getInitialToppings());
        for (int i = 0; i < mToppings.length; i++)
        {
            CheckBox checkBox = new CheckBox(getActivity());
            checkBox.setLayoutParams(lparams);
            checkBox.setText(mToppings[i]+" (+$"+Double.toString(mToppingPriceList[i])+")");
            if(mOrder.getToppings().contains(mToppings[i])) {
                checkBox.setChecked(true);
                if (mOrder.getInitialToppings().contains(mToppings[i])) {
                    checkBox.setEnabled(false);
                }

            }
            mLinearLayout.addView(checkBox);
            mCheckBoxes[i] = checkBox;
            checkBox.setOnCheckedChangeListener(new ToppingOnCheckedChangeListener(mOrder,mToppingPriceList[i]));
        }


        //

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.toppings_picker_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendResult(Activity.RESULT_OK, mOrder);
                            }
                        })
                .create();
    }


    private void sendResult(int resultCode, Order order) {
        System.out.println(getTargetFragment());
        if(getTargetFragment() == null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_ORDER, mOrder);

        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
        System.out.println("Sending result");
    }
}
