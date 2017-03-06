//Serhii Pianykh, #300907406, Assignment1
package serhii.pianykh;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * Created by rider on 12.10.2016.
 */

public class DoughOnItemSelectedListener implements OnItemSelectedListener {

    private final Order mOrder;

    public DoughOnItemSelectedListener (final Order order) {
        this.mOrder = order;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        mOrder.setDough(parent.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mOrder.setDough(parent.getItemAtPosition(0).toString());
    }
}
