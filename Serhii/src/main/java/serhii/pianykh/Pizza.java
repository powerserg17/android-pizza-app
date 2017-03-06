//Serhii Pianykh, #300907406, Assignment1
package serhii.pianykh;


import java.io.Serializable;
import java.util.Set;

/**
 * Created by rider on 12.10.2016.
 */
//Serhii Pianykh, #300907406, Assignment1

public final class Pizza implements Serializable {

    private int mId;
    private String mName;
    private String mPic;
    private Set<String> toppings;
    private double mPrice;

    public Pizza(String name) {
        this.mName = name;
    }

    public Pizza(int id, String name, Set<String> toppings, double price) {
        this.mId = id;
        this.mName = name;
        this.toppings = toppings;
        this.mPrice = price;
        this.mPic = "pizza_"+id;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return this.mName;
    }

    public Set<String> getToppings() {
        System.out.println(this.toppings);
        return this.toppings;
    }

    public double getPrice() {
        return this.mPrice;
    }

    public String getPic() { return this.mPic; };
}
