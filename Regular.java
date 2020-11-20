package com.zotatob;

public class Regular extends Restaurant{

    public Regular(String name, String address)
    {
        super(name, address);
        super.setCategory("Regular");
    }

    @Override
    public double getDiscount() {
        return 0;
    }

    @Override
    public void setDiscount(double discount) {

    }
}
