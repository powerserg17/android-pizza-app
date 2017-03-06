//Serhii Pianykh, #300907406, Assignment1
package serhii.pianykh;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * Created by rider on 13.10.2016.
 */

public class ToppingOnCheckedChangeListener implements OnCheckedChangeListener {

    private final Order mOrder;
    private final double mPrice;

    public ToppingOnCheckedChangeListener(Order order, double price) {
        this.mOrder = order;
        this.mPrice = price;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            mOrder.addTopping(getNameWithoutPrice(buttonView.getText().toString()), mPrice);
            //System.out.println(mOrder.getToppings());
            //System.out.println(mOrder.getPrice());
        } else {
            mOrder.removeTopping(getNameWithoutPrice(buttonView.getText().toString()), mPrice);
            //System.out.println(mOrder.getToppings());
            //System.out.println(mOrder.getPrice());
        }
    }

    private String getNameWithoutPrice(String name) {
        String new_name ="";
        int index = name.indexOf(" ");
        new_name = name.substring(0,index);
        return new_name;
    }
}
