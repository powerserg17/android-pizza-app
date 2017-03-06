//Serhii Pianykh, #300907406, Assignment1
package serhii.pianykh;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

/**
 * Created by rider on 12.10.2016.
 */

public class SizeOnItemSelectedListener implements OnItemSelectedListener {

    private final Order mOrder;
    private final String[] size_list;

    public SizeOnItemSelectedListener(final Order order, final String[] list) {
        this.mOrder = order;
        this.size_list = list;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        mOrder.setSize(parent.getSelectedItem().toString(), size_list);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mOrder.setSize(parent.getItemAtPosition(0).toString(), size_list);
    }
}
