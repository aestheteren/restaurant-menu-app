package com.example.a20210305037_restaurantmenu;
import java.util.List;

public class Order {

    private String orderId;
    private List<OrderMenu> items;
    private double totalPrice;
    private long createdAt;

    public Order(String orderId, List<OrderMenu> items) {
        this.orderId = orderId;
        this.items = items;
        this.createdAt = System.currentTimeMillis();
        calculateTotal();
    }

    private void calculateTotal() {
        totalPrice = 0;
        for (OrderMenu item : items) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
    }

    public List<OrderMenu> getItems() { return items; }
    public double getTotalPrice() { return totalPrice; }
    public long getCreatedAt() { return createdAt; }
}
