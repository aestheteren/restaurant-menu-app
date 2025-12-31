package com.example.a20210305037_restaurantmenu;

import android.app.Application;
import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    private final List<OrderMenu> cartItems = new ArrayList<>();

    public List<OrderMenu> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }
}
