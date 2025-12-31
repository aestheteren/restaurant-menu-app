package com.example.a20210305037_restaurantmenu;

public class MenuItem {
    private final String name;
    private final int imageResId;
    private final double price;
    private final String description;

    public MenuItem(String name, int imageResId, double price, String description) {
        this.name = name;
        this.imageResId = imageResId;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
