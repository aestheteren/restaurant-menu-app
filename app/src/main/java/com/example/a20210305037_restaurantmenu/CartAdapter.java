package com.example.a20210305037_restaurantmenu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    public interface OnCartChangeListener {
        void onCartUpdated();
    }

    private final List<OrderMenu> cartItems;
    private final OnCartChangeListener listener;

    public CartAdapter(List<OrderMenu> cartItems, OnCartChangeListener listener) {
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        OrderMenu item = cartItems.get(position);

        holder.txtItemInfo.setText(
                item.getName() + " (" + item.getQuantity() + ")  -  $" + item.getPrice()
        );

        holder.btnRemove.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.decreaseQuantity();
            } else {
                cartItems.remove(position);
            }

            notifyDataSetChanged();

            if (listener != null) {
                listener.onCartUpdated();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView txtItemInfo;
        Button btnRemove;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemInfo = itemView.findViewById(R.id.txtItemInfo);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}
