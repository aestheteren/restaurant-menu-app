package com.example.a20210305037_restaurantmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private final List<MenuItem> menuList;
    private final FirebaseAnalytics mFirebaseAnalytics;

    public MenuAdapter(List<MenuItem> menuList, FirebaseAnalytics firebaseAnalytics) {
        this.menuList = menuList;
        this.mFirebaseAnalytics = firebaseAnalytics;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        final MenuItem item = menuList.get(position);

        holder.txtName.setText(item.getName());

        String priceText = String.format(
                holder.itemView.getContext().getString(R.string.price_format),
                item.getPrice()
        );
        holder.txtPrice.setText(priceText);

        holder.imgMenu.setImageResource(item.getImageResId());

        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, item.getName());
            bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "Menu");
            bundle.putDouble(FirebaseAnalytics.Param.PRICE, item.getPrice());
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

            Intent intent = new Intent(v.getContext(), MenuDetailActivity.class);
            intent.putExtra("name", item.getName());
            intent.putExtra("imageResId", item.getImageResId());
            intent.putExtra("price", item.getPrice());
            intent.putExtra("description", item.getDescription());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtPrice;
        ImageView imgMenu;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtItemName);
            txtPrice = itemView.findViewById(R.id.txtItemPrice);
            imgMenu = itemView.findViewById(R.id.imgMenu);
        }
    }
}
