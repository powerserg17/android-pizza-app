//Serhii Pianykh, #300907406, Assignment1
package serhii.pianykh;

import android.support.v4.app.Fragment;

/**
 * Created by rider on 13.10.2016.
 */

public class MenuActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new MenuFragment();
    }
}
