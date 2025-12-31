package com.example.a20210305037_restaurantmenu;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.UUID;

public class CartActivity extends AppCompatActivity {

    private TextView txtTotalPrice;
    private List<OrderMenu> cartItems;
    private MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarCart);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Your Cart");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerCart);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        Button btnPlaceOrder = findViewById(R.id.btnPlaceOrder);

        // Cart data
        app = (MyApplication) getApplication();
        cartItems = app.getCartItems();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CartAdapter adapter = new CartAdapter(cartItems, this::updateTotalPrice);
        recyclerView.setAdapter(adapter);

        updateTotalPrice();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        btnPlaceOrder.setOnClickListener(v -> {
            if (cartItems.isEmpty()) {
                Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show();
                return;
            }

            Order order = new Order(
                    UUID.randomUUID().toString(),
                    cartItems
            );

            db.collection("orders")
                    .add(order)
                    .addOnSuccessListener(doc -> {
                        app.clearCart();
                        updateTotalPrice();
                        Toast.makeText(this, "Order saved!", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Order failed", Toast.LENGTH_SHORT).show();
                    });
        });
    }

    private void updateTotalPrice() {
        double total = 0;
        for (OrderMenu item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        txtTotalPrice.setText("Total: $" + total);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
