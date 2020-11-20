package com.zotatob;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Food> food;
    private double totalPrice;
    private Restaurant restaurant;
    private double deliveryCharge;

    public Cart(ArrayList f, double price, Restaurant r, double deliveryCharge)
    {
        this.food = f;
        this.totalPrice=price;
        this.restaurant=r;
        this.deliveryCharge=deliveryCharge;
    }

    public ArrayList<Food> getFood() {
        return food;
    }

    public void setFood(ArrayList<Food> food) {
        this.food = food;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public double getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }
}
