//Serhii Pianykh, #300907406, Assignment1
package serhii.pianykh;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rider on 12.10.2016.
 */

public class Menu {
    private static Menu sMenu;

    private List<Pizza> mPizzas;

    public static Menu get(Context context) {
        if (sMenu == null) {
            sMenu = new Menu(context);
        }
        return sMenu;
    }

    private Menu(Context context) {
        mPizzas = new ArrayList<>();
        mPizzas=getPizzasList(context);
    }

    public List<Pizza> getPizzas() {
        return mPizzas;
    }

    public Pizza getPizza(int id) {
        for (Pizza pizza : mPizzas) {
            if (pizza.getId() == id) {
                return pizza;
            }
        }
        return null;
    }

    private Set<String> getSetOfToppings(Context context, ArrayList<Integer> toppings_ids) {
        Set<String> toppings = new HashSet<>();
        Resources res = context.getResources();
        String[] toppingList = res.getStringArray(R.array.topping_list);
        for (int i = 0; i < toppings_ids.size(); i++) {
            toppings.add(toppingList[toppings_ids.get(i)]);
        }
        return toppings;
    }

    private List<Pizza> getPizzasList(Context context)
    {
        List<Pizza> pizzas = new ArrayList<>();
        Resources res = context.getResources();
        String[] pizzaNames = res.getStringArray(R.array.pizza_list);
        double[] prices = new double[] {12.99, 11.99, 9.99, 10.49, 10.99, 8.99};
        for (int i = 0; i<pizzaNames.length; i++) {
            pizzas.add(new Pizza(i,pizzaNames[i],getSetOfToppings(context,getToppingsById(i)),prices[i]));
        }
        return pizzas;
    }

    private ArrayList<Integer> getToppingsById (int id)
    {
        ArrayList<Integer> toppings = new ArrayList<>();
        switch (id) {
            case 0: {
                toppings.add(0);
                toppings.add(1);
                toppings.add(10);
                toppings.add(11);
                break;
            }
            case 1: {
                toppings.add(0);
                toppings.add(12);
                toppings.add(13);
                toppings.add(11);
                break;
            }
            case 2: {
                toppings.add(0);
                toppings.add(2);
                toppings.add(10);
                break;
            }
            case 3: {
                toppings.add(0);
                toppings.add(3);
                toppings.add(14);
                break;
            }
            case 4: {
                toppings.add(0);
                toppings.add(6);
                toppings.add(8);
                toppings.add(9);
                break;
            }
            case 5: {
                toppings.add(8);
                toppings.add(3);
                toppings.add(7);
                toppings.add(6);
                break;
            }
        }
        return toppings;
    }
}
