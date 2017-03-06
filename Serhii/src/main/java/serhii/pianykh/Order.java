//Serhii Pianykh, #300907406, Assignment1
package serhii.pianykh;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by rider on 12.10.2016.
 */
//Serhii Pianykh, #300907406, Assignment1

public class Order implements Serializable {
    private final Pizza mPizza;
    private UUID mId;
    private String size;
    private String dough;
    private boolean wheat;
    private Set<String> order_toppings = new HashSet<>();
    private double price;
    private String mFullName;
    private String mPhone;
    private String mCardNumber;
    private String mExpiryDate;
    private String mAddress;
    private String mCVV;

    public Order(Pizza pizza, String dough, String size, boolean wheat) {
        this.mId = UUID.randomUUID();
        this.mPizza = pizza;
        this.dough = dough;
        this.size = size;
        this.wheat = false;
        for (String s: mPizza.getToppings()) {
            order_toppings.add(s);
        }
        this.price = mPizza.getPrice();
    }

    public Pizza getPizza() {return this.mPizza;}

    private void changePrice(double amount) {
        this.price = this.price + amount;
    }

    public String getName() {
        return this.mPizza.getName();
    }

    public Set<String> getInitialToppings() {
        //System.out.println(this.mPizza.getToppings());
        return this.mPizza.getToppings();
    }

    public Set<String> getToppings() {
        return this.order_toppings;
    }

    public void addTopping(String topping, double amount) {
        this.order_toppings.add(topping);
        changePrice(amount);
    }

    public void removeTopping(String topping, double amount) {
        this.order_toppings.remove(topping);
        changePrice(-amount);
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size, String[] list) {
        String prev_size = this.size;
        this.size = size;
        double prev_price = 0;
        double new_price = 0;
        double[] price_list = new double[] {0, 0.99, 2.99, 3.99};
        for (int i = 0; i < list.length; i++) {
            if (prev_size.equals(list[i])) {
                prev_price = price_list[i];
                break;
            }
        }
        for (int i = 0; i < list.length; i++) {
            if (size.equals(list[i])) {
                new_price = price_list[i];
                break;
            }
        }
        double change = new_price - prev_price;
        changePrice(change);
    }

    public String getDough() {
        return this.dough;
    }

    public void setDough(String dough) {
        this.dough = dough;
    }

    public boolean isWheat() {
        return this.wheat;
    }

    public void setWheat(boolean wheat) {
        this.wheat = wheat;
        if (isWheat()) {
            changePrice(1.99);
        } else {
            changePrice(-1.99);
        }
    }

    public UUID getId() {
        return mId;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPic() {
        return this.mPizza.getPic();
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getCardNumber() {
        return mCardNumber;
    }

    public void setCardNumber(String cardNumber) {
        mCardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return mExpiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        mExpiryDate = expiryDate;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getCVV() {
        return mCVV;
    }

    public void setCVV(String CVV) {
        mCVV = CVV;
    }
}
