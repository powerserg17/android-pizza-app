//Serhii Pianykh, #300907406, Assignment1
package serhii.pianykh;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by rider on 14.10.2016.
 */

public class CheckoutFragment extends Fragment {

    private static final String ARG_ORDER="order_object";

    private Order mOrder;
    private Button mConfirmButton;
    private ImageView mPizzaCheckoutPic;
    private TextView mToppingsList;
    private TextView mSizeView;
    private TextView mDoughView;
    private TextView mPriceView;
    private TextView mPizzaName;
    private EditText mFullName;
    private EditText mAddress;
    private EditText mPhone;
    private EditText mCard;
    private EditText mExpiryDate;
    private EditText mCVV;

    public static CheckoutFragment newInstance(Order order) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ORDER, order);

        CheckoutFragment fragment = new CheckoutFragment();
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
        View v = inflater.inflate(R.layout.fragment_checkout, container, false);

        mPizzaName = (TextView)v.findViewById(R.id.pizza_checkout_name);
        mPizzaName.setText(mOrder.getName());

        mPizzaCheckoutPic = (ImageView)v.findViewById(R.id.pizza_checkout_order_pic);
        String pic = mOrder.getPic();
        mPizzaCheckoutPic.setImageResource(getResources().getIdentifier(pic, "drawable", "serhii.pianykh"));

        mToppingsList = (TextView)v.findViewById(R.id.toppings_checkout_text_view);
        String topps = "";
        for (String s : mOrder.getToppings()) {
            topps = topps + s + ", ";
        }
        topps = topps.substring(0, topps.length()-2);
        mToppingsList.setText(topps);

        mSizeView = (TextView)v.findViewById(R.id.size_text_view);
        mSizeView.setText(mOrder.getSize());

        mDoughView = (TextView)v.findViewById(R.id.dough_text_view);
        if(mOrder.isWheat()) {
            mDoughView.setText(mOrder.getDough()+" ("+getResources().getString(R.string.wheat_label)+")");
        } else {
            mDoughView.setText(mOrder.getDough());
        }

        mPriceView = (TextView)v.findViewById(R.id.price_checkout_text_view);
        mPriceView.setText("$"+Double.toString(mOrder.getPrice()));

        mFullName = (EditText)v.findViewById(R.id.full_name_edit);
        mFullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mOrder.setFullName(mFullName.getText().toString());
            }
        });

        mAddress = (EditText)v.findViewById(R.id.delivery_address_edit);
        mAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mOrder.setAddress(mAddress.getText().toString());
            }
        });

        mPhone = (EditText)v.findViewById(R.id.phone_number_edit);
        mPhone.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                mOrder.setPhone(mPhone.getText().toString());
            }
        });


        mCard = (EditText)v.findViewById(R.id.card_number_edit);
        mCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mOrder.setCardNumber(mCard.getText().toString());
            }
        });

        mExpiryDate = (EditText)v.findViewById(R.id.expiry_date_edit);
        mExpiryDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
             }

            @Override
            public void afterTextChanged(Editable s) {

                mOrder.setExpiryDate(mExpiryDate.getText().toString());
            }
        });

        mCVV = (EditText)v.findViewById(R.id.cvv_edit);
        mCVV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mOrder.setCVV(mCVV.getText().toString());
            }
        });

        mConfirmButton = (Button)v.findViewById(R.id.confirm_button);
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPhone.getText().toString().length()==0 || mAddress.getText().toString().length()==0
                        || mExpiryDate.getText().toString().length()==0 || mExpiryDate.getText().toString().length()!=4
                        || mCard.getText().toString().length()==0 || mCard.getText().toString().length()!=16
                        || mCVV.getText().toString().length()==0 || mCVV.getText().toString().length()!=3
                        || mFullName.getText().toString().length() == 0) {
                    String message = getResources().getString(R.string.check_input);
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = ConfirmationActivity.newIntent(getActivity(), mOrder);
                    startActivity(intent);
                }

            }
        });

        return v;
    }

}
