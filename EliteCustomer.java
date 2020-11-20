package com.zotatob;

public class EliteCustomer extends Customer{

    public EliteCustomer(String name, String address)
    {
        super(name, address);
    }


    public double customerDiscount(double price)
    {
        if(price > 200)
            return price-50;
        return price;
    }

    @Override
    public double getDeliveryCharges() {
        return 0;
    }
}
