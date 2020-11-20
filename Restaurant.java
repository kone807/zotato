package com.zotatob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Restaurant implements User{

        private final String name;
        private String category;
        private ArrayList<Food> food;
        private final String address;
        private double discount;
        private double points;
        private double revenue;
        private final int ID;
        private double deliveryCharge;
        private static int count=1;
        private HashMap<Integer, Food> foodHash;

        Scanner sc=new Scanner(System.in).useDelimiter("\n");

        public Restaurant(String name, String address)
        {
            this.name=name;

            this.food = new ArrayList<>();
            this.address=address;
            this.discount=0;
            this.ID=count++;
            this.revenue=0;
            this.points=0;
            this.deliveryCharge=0;
            this.foodHash = new HashMap<>();
        }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void addFood()
        {
            System.out.println("Enter Food Item details\nFood Name:");
            String foodName=sc.next();
            System.out.println("Item Price:");
            double price = sc.nextDouble();
            System.out.println("Item Quantity:");
            int quantity = sc.nextInt();
            System.out.println("Item Category:");
            String category = sc.next();
            System.out.println("Offer:");
            double offer = sc.nextDouble();

            food.add(new Food(foodName, price, quantity, offer, category, name));

            Food curr = food.get(food.size()-1);
            foodHash.put(curr.getID(), curr);
            System.out.println(curr.getID()+" "+curr.getName()+" "+curr.getPrice()+" "+curr.getQuantity()+" "+curr.getOffer()+"% off"+" "+curr.getCategory());
        }

        public void editFood()
        {
            System.out.println("Choose Item by Code:");
            printFoods();

            int code = sc.nextInt();
            System.out.println("Choose as attribute to edit:");
            System.out.println("1. Name");
            System.out.println("2. Price");
            System.out.println("3. Quantity");
            System.out.println("4. Category");
            System.out.println("5. Offer");

            int attribute = sc.nextInt();

            switch(attribute)
            {
                case 1:
                {
                    System.out.print("Enter the new name - ");
                    String newName = sc.next();
                    foodHash.get(code).setName(newName);
                    food.get(code-1).setName(newName);
                    break;
                }

                case 2:
                {
                    System.out.print("Enter the new price - ");
                    double newPrice = sc.nextDouble();
                    foodHash.get(code).setPrice(newPrice);
                    food.get(code-1).setPrice(newPrice);
                    break;
                }

                case 3:
                {
                    System.out.print("Enter the new quantity - ");
                    int newQty = sc.nextInt();
                    foodHash.get(code).setQuantity(newQty);
                    food.get(code-1).setQuantity(newQty);
                    break;
                }

                case 4:
                {
                    System.out.print("Enter the new category - ");
                    String newCategory = sc.next();
                    foodHash.get(code).setCategory(newCategory);
                    food.get(code-1).setCategory(newCategory);
                    break;
                }

                case 5:
                {
                    System.out.print("Enter the new offer - ");
                    double newOffer = sc.nextDouble();
                    foodHash.get(code).setOffer(newOffer);
                    food.get(code-1).setOffer(newOffer);
                    break;
                }
            }
            Food f = food.get(code-1);
            System.out.println(f.getID()+" "+foodHash.get(code).getName()+" "+foodHash.get(code).getPrice()+" "+foodHash.get(code).getQuantity()+" "+foodHash.get(code).getOffer()+"% off "+foodHash.get(code).getCategory());
        }
        public void printRewards()
        {
            System.out.println("Reward Points: "+this.points);
        }

        public void displayExpenditure()
        {
            System.out.println(revenue);
        }
        public double getDiscount()
        {
            return this.discount;
        }

        public void setDiscount(double discount)
        {
            this.discount=discount;
        }
        public String getName()
        {
            return name;
        }

        public void printFoods()
        {
            for(Food f : food)
            {
                System.out.println(f.getID()+" "+this.getName()+" - "+f.getName()+" "+f.getPrice()+" "+f.getQuantity()+" "+f.getOffer()+"% off "+f.getCategory());
            }
        }

        public ArrayList<Food> getFood() {
            return food;
        }

        public String displayName()
        {
            return this.name+" ("+this.category+")";
        }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public int getID() {
        return ID;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public HashMap<Integer, Food> getFoodHash() {
        return foodHash;
    }

    public void setFoodHash(HashMap<Integer, Food> foodHash) {
        this.foodHash = foodHash;
    }
}


