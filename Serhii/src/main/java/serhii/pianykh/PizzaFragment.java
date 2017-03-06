//Serhii Pianykh, #300907406, Assignment1
package serhii.pianykh;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by rider on 12.10.2016.
 */
//Serhii Pianykh, #300907406, Assignment1

public class PizzaFragment extends Fragment{

    private static final String ARG_PIZZA_ID = "pizza_id";
    private static final String DIALOG_TOPPINGS = "DialogToppings";
    private static final int REQUEST_ORDER = 0;

    private Pizza mPizza;
    private TextView mNameField;
    private ImageView mPizzaOrderPic;
    private Spinner mSizeSpinner;
    private Spinner mDoughSpinner;
    private Button mToppingsButton;
    private CheckBox mWholeWheat;
    private TextView mToppingsList;
    private TextView mPriceField;
    private Button mCheckoutButton;

    private Order mOrder;

    private String[] mDoughList;
    private String[] mSizeList;
    private String[] mToppingList;

    public static PizzaFragment newInstance(int pizza_id) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PIZZA_ID, pizza_id);

        PizzaFragment fragment = new PizzaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources res = getResources();
        mDoughList = res.getStringArray(R.array.dough_list);
        mSizeList = res.getStringArray(R.array.size_list);
        mToppingList = res.getStringArray(R.array.topping_list);

        int id = (int) getArguments().getSerializable(ARG_PIZZA_ID);
        mPizza = Menu.get(getActivity()).getPizza(id);
        mOrder = new Order(mPizza, mDoughList[0], mSizeList[0], false);


    }

    public void onResume() {
        super.onResume();
        updateUI();
        //System.out.println(mOrder.getToppings());
        //System.out.println(mOrder.getPrice());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pizza, container, false);



        mNameField = (TextView)v.findViewById(R.id.pizza_name);
        mNameField.setText(mOrder.getName());

        mToppingsList = (TextView)v.findViewById(R.id.toppings_order_text_view);
        mPriceField = (TextView)v.findViewById(R.id.price_order_text_view);
        updateUI();

        mPizzaOrderPic = (ImageView)v.findViewById(R.id.pizza_order_pic);
        String pic = mOrder.getPic();

        mPizzaOrderPic.setImageResource(getResources().getIdentifier(pic, "drawable", "serhii.pianykh"));

        mSizeSpinner = (Spinner)v.findViewById(R.id.size_spinner);
        //mSizeSpinner.setOnItemSelectedListener(new SizeOnItemSelectedListener(mOrder, mSizeList));
        mSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mOrder.setSize(parent.getSelectedItem().toString(), mSizeList);
                updateUI();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mOrder.setSize(parent.getItemAtPosition(0).toString(), mSizeList);
                updateUI();
            }
        });

        mDoughSpinner = (Spinner)v.findViewById(R.id.dough_spinner);
        mDoughSpinner.setOnItemSelectedListener(new DoughOnItemSelectedListener(mOrder));

        ArrayAdapter<CharSequence> size_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.size_list, android.R.layout.simple_spinner_item);
        size_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSizeSpinner.setAdapter(size_adapter);

        ArrayAdapter<CharSequence> dough_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.dough_list, android.R.layout.simple_spinner_item);
        dough_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDoughSpinner.setAdapter(dough_adapter);

        mWholeWheat = (CheckBox)v.findViewById(R.id.wheat_checkbox);
        mWholeWheat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mOrder.setWheat(isChecked);
                updateUI();
            }
        });

        //System.out.println(mOrder.getToppings());
        //System.out.println(mOrder.getInitialToppings());
        //System.out.println(mOrder.getPrice());

        mToppingsButton = (Button)v.findViewById(R.id.select_toppings_button);
        mToppingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                ToppingsPickerFragment dialog = ToppingsPickerFragment.newInstance(mOrder);
                dialog.setTargetFragment(PizzaFragment.this,REQUEST_ORDER);
                dialog.show(manager, DIALOG_TOPPINGS);
            }
        });

        mCheckoutButton = (Button)v.findViewById(R.id.checkout_button);
        mCheckoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CheckoutActivity.newIntent(getActivity(), mOrder);
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            System.out.println("HERE");
            return;
        }

        if (requestCode == REQUEST_ORDER) {
            Order updatedOrder = (Order) data.getSerializableExtra(ToppingsPickerFragment.EXTRA_ORDER);
            mOrder = updatedOrder;
            System.out.println(mOrder.getInitialToppings());
            System.out.println("THERE");
            updateUI();
        }
    }

    public void updateUI() {
        String toppings = "";
        for (String s : mOrder.getToppings()) {
            toppings = toppings + s + ",";
        }
        toppings=toppings.substring(0,toppings.length()-1);
        mToppingsList.setText(toppings);
        mPriceField.setText("$"+mOrder.getPrice());
    }



}
