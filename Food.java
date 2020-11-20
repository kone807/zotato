package com.zotatob;

public class Food {
    private String name;
    private double price;
    private int quantity;
    private double offer;
    private String category;
    private String restaurantName;
    private final int ID;
    private static int count=1;

    public Food(String name, double price, int quantity, double offer, String category, String restaurantName)
    {
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.offer=offer;
        this.category=category;
        this.ID=count++;
        this.restaurantName=restaurantName;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getOffer() {
        return offer;
    }

    public String getCategory() {
        return category;
    }

    public int getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setOffer(double offer) {
        this.offer = offer;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRestaurantName() {
        return restaurantName;
    }
}
