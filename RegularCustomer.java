package com.zotatob;

public class RegularCustomer extends Customer{

    public RegularCustomer(String name, String address)
    {
       super(name, address);
    }

    public double customerDiscount(double price)
    {
        return price;
    }
}
