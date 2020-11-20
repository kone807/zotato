package com.zotatob;

public class SpecialCustomer extends Customer{

    public SpecialCustomer(String name, String address)
    {
        super(name, address);
    }


    public double customerDiscount(double price)
    {
        if(price>200)
            return price-25;
        return price;
    }

    @Override
    public double getDeliveryCharges() {
        return 20;
    }
}
