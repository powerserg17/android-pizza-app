//Serhii Pianykh, #300907406, Assignment1
package serhii.pianykh;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;

public class SerhiiActivity extends SingleFragmentActivity {

    private static final String PIZZA = "serhii.pianykh.pizza";

    public static Intent newIntent(Context packageContext, int id) {
        Intent intent = new Intent(packageContext, SerhiiActivity.class);
        intent.putExtra(PIZZA, id);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        int id = (int) getIntent().getSerializableExtra(PIZZA);
        return PizzaFragment.newInstance(id);
    }
}
