package com.zotatob;

import java.util.ArrayList;

public class Customer implements User{
    private final String name;
    private double rewardPoints;
    private double wallet;
    private final String address;
    private ArrayList<Food> food;
    private Cart cart;
    private ArrayList<Cart> order;


    //  private ArrayList<Orders> orders;

    public Customer(String name, String address)
    {
        this.name=name;
        rewardPoints=0;
        wallet=1000;
        this.address=address;
        food = new ArrayList<>();
        order = new ArrayList<>();
        cart=null;

    }

    public String getName() {
        return name;
    }

    public double getRewardPoints() {

        return this.rewardPoints;
    }

    public double setRewardPoints(double price) {
        this.rewardPoints+=5*(int)(price/100);
        return this.rewardPoints;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double price)
    {
        price = price-Math.min(price, this.rewardPoints);
        this.wallet -=price;
        this.rewardPoints-=Math.min(price, this.rewardPoints);
    }

    public String getAddress() {
        return address;
    }

    public double placeOrder(Food f)
    {
        double price = f.getPrice()*f.getQuantity();
        price -= price*f.getOffer()/100;
        return price;
    }

    public ArrayList<Food> getFood() {
        return food;
    }

    public void printRewards()
    {
        System.out.println(this.rewardPoints);
    }
    public void setFood(ArrayList<Food> food) {
        this.food = food;
    }

    public String displayName()
    {
        return this.name+" ";
    }

    public Cart getCart() {
        return cart;
    }

    public void displayExpenditure()
    {
        System.out.println(this.wallet);
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public double customerDiscount(double price)
    {
        return price;
    }

    public void rewardWon()
    {
        System.out.println("Total reward points won by the customer are: "+this.rewardPoints);
    }

    public double getDeliveryCharges()
    {
        return 40;
    }

    public void printOrder()
    {
        System.out.println("Items in Cart-");

        for(int i=0; i<cart.getFood().size(); i++)
        {
            System.out.println(cart.getRestaurant().getID()+" "+cart.getRestaurant().getName()+" "+cart.getFood().get(i).getName()+" - "+cart.getFood().get(i).getPrice()+" - "+cart.getFood().get(i).getQuantity()+" - "+cart.getFood().get(i).getOffer()+"% off");
        }
    }

    public ArrayList<Cart> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<Cart> order) {
        this.order = order;
    }

    public void printRecentOrder()
    {
        for(Cart c : order)
        {
            for(Food f : c.getFood())
            System.out.println("Bought Item: "+f.getName()+", quantity: "+f.getQuantity()+", for Rs "+c.getTotalPrice()+" from Restaurant "+f.getRestaurantName()+" and delivery charge "+c.getDeliveryCharge());
        }
    }
}
