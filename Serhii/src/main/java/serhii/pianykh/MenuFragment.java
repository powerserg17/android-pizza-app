//Serhii Pianykh, #300907406, Assignment1
package serhii.pianykh;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rider on 13.10.2016.
 */

public class MenuFragment extends Fragment {

    private RecyclerView mPizzaRecyclerView;
    private PizzaAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);


        mPizzaRecyclerView = (RecyclerView) view.findViewById(R.id.pizza_recycler_view);
        mPizzaRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        Menu menu = Menu.get(getActivity());
        List<Pizza> pizzas = menu.getPizzas();

        mAdapter = new PizzaAdapter(pizzas);
        mPizzaRecyclerView.setAdapter(mAdapter);
    }

    private class PizzaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Pizza mPizza;
        private TextView mNameTextView;
        private ImageView mPizzaPic;
        private TextView mToppingsList;
        private TextView mPrice;

        public PizzaHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.pizza_name_text_view);
            mPizzaPic = (ImageView) itemView.findViewById(R.id.pizza_pic);
            mToppingsList = (TextView) itemView.findViewById(R.id.toppings_list_view);
            mPrice = (TextView) itemView.findViewById(R.id.price_text_view);
        }

        public void bindPizza(Pizza pizza) {
            mPizza = pizza;

            mNameTextView.setText(mPizza.getName());

            String pic = mPizza.getPic();

            mPizzaPic.setImageResource(getResources().getIdentifier(pic, "drawable", "serhii.pianykh"));

            Set<String> toppings = new HashSet<>();
            toppings = pizza.getToppings();

            ArrayList<String> topps = new ArrayList<>();

            for (String topping : toppings) {
                topps.add(topping+"\n");
            }
            String text = "";
            for (String topp : topps) {
                text = text+topp;
            }

            mToppingsList.setText(text);

            String price = Double.toString(mPizza.getPrice());

            String price_label = getResources().getString(R.string.from_price_label) + price;

            mPrice.setText(price_label);
        }

        @Override
        public void onClick(View v) {
            Intent intent = SerhiiActivity.newIntent(getActivity(), mPizza.getId());
            startActivity(intent);
        }
    }

    private class PizzaAdapter extends RecyclerView.Adapter<PizzaHolder> {

        private List<Pizza> mPizzas;

        public PizzaAdapter(List<Pizza> pizzas) {
            mPizzas = pizzas;
        }

        @Override
        public PizzaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_pizza, parent, false);
            return new PizzaHolder(view);
        }

        @Override
        public void onBindViewHolder(PizzaHolder holder, int position) {
            Pizza pizza = mPizzas.get(position);
            holder.bindPizza(pizza);
        }

        @Override
        public int getItemCount() {
            return mPizzas.size();
        }
    }
}
