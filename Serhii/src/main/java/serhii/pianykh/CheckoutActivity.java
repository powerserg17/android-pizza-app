//Serhii Pianykh, #300907406, Assignment1
package serhii.pianykh;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by rider on 14.10.2016.
 */

public class CheckoutActivity extends SingleFragmentActivity {
    private static final String ORDER_CHECKOUT = "serhii.pianykh.order_to_checkout";

    public static Intent newIntent(Context packageContext, Order order) {
        Intent intent = new Intent(packageContext, CheckoutActivity.class);
        intent.putExtra(ORDER_CHECKOUT, order);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        Order order = (Order) getIntent().getSerializableExtra(ORDER_CHECKOUT);
        return CheckoutFragment.newInstance(order);
    }
}
