package com.example.a20210305037_restaurantmenu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    RecyclerView recyclerMenu;
    Button btnOpenCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerMenu = findViewById(R.id.recyclerMenu);
        btnOpenCart = findViewById(R.id.btnOpenCart);

        recyclerMenu.setLayoutManager(new LinearLayoutManager(this));

        List<MenuItem> menuList = new ArrayList<>();
        menuList.add(new MenuItem("Cheeseburger", R.drawable.cheeseburger, 12.50, "Beef, cheddar cheese, lettuce, tomato, onion"));
        menuList.add(new MenuItem("Margherita Pizza", R.drawable.pizza, 15.00, "Tomato sauce, mozzarella cheese, basil"));
        menuList.add(new MenuItem("Caesar Salad", R.drawable.caesar, 10.00, "Lettuce, parmesan, croutons, Caesar dressing"));
        menuList.add(new MenuItem("Spaghetti Bolognese", R.drawable.spaghetti, 14.00, "Spaghetti pasta, beef Bolognese sauce, parmesan"));
        menuList.add(new MenuItem("Grilled Chicken Sandwich", R.drawable.chicken, 13.00, "Grilled chicken, lettuce, tomato, mayo, fries"));
        menuList.add(new MenuItem("Chocolate Cake", R.drawable.cake, 8.50, "Rich chocolate cake with chocolate ganache"));

        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        MenuAdapter adapter = new MenuAdapter(menuList, mFirebaseAnalytics);
        recyclerMenu.setAdapter(adapter);

        // OPEN CART
        btnOpenCart.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }
}
