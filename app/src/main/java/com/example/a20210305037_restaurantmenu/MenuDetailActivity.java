package com.example.a20210305037_restaurantmenu;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

public class MenuDetailActivity extends AppCompatActivity {

    TextView txtDetailName, txtDetailDescription, txtDetailPrice;
    ImageView imgDetailMenu;
    Button btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Menu Detail");
        }

        txtDetailName = findViewById(R.id.txtDetailName);
        txtDetailDescription = findViewById(R.id.txtDetailDescription);
        txtDetailPrice = findViewById(R.id.txtDetailPrice);
        imgDetailMenu = findViewById(R.id.imgDetailMenu);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");
        double price = getIntent().getDoubleExtra("price", 0.0);
        int imageResId = getIntent().getIntExtra("imageResId", 0);

        txtDetailName.setText(name);
        txtDetailDescription.setText(description);
        imgDetailMenu.setImageResource(imageResId);

        String priceText = String.format(
                Locale.getDefault(),
                getString(R.string.price_format),
                price
        );
        txtDetailPrice.setText(priceText);

        // ✅ ADD TO CART (GARANTİLİ)
        btnAddToCart.setOnClickListener(v -> {
            MenuItem menuItem = new MenuItem(
                    name,
                    imageResId,
                    price,
                    description
            );

            MyApplication app = (MyApplication) getApplication();

            boolean found = false;
            for (OrderMenu item : app.getCartItems()) {
                if (item.getName().equals(menuItem.getName())) {
                    item.increaseQuantity();
                    found = true;
                    break;
                }
            }

            if (!found) {
                app.getCartItems().add(
                        new OrderMenu(
                                menuItem.getName(),
                                menuItem.getPrice(),
                                1
                        )
                );
            }


            Toast.makeText(
                    MenuDetailActivity.this,
                    "Added to cart",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
