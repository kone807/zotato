package com.zotatob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Manage {
    private ArrayList<Restaurant> restaurant;
    private ArrayList<Customer> customer;
    Scanner sc = new Scanner(System.in);

    public Manage()
    {
        restaurant = new ArrayList<>();
        customer = new ArrayList<>();
    }

    // function to get things going
    public void run()
    {
        // hard code data
        hardCodeData();

        //display main menu
        displayMainMenu();
    }

    // function to hard code data of restaurants and customers
    public void hardCodeData()
    {
        // add restaurants
        Authentic Shah = new Authentic("Shah", "Pitampura");
        Regular Ravi = new Regular("Ravi's", "Okhla");
        Authentic Chinese = new Authentic("The Chinese", "Rohini");
        FastFood Wang = new FastFood("Wang's", "Shalimar Bagh");
        Regular Paradise = new Regular("Paradise", "Yamuna Vihar");
        restaurant.add(Shah); restaurant.add(Ravi); restaurant.add(Chinese);
        restaurant.add(Wang); restaurant.add(Paradise);

        // add customers
        EliteCustomer Ram = new EliteCustomer("Ram", "Shalimar Bagh");
        EliteCustomer Sam = new EliteCustomer("Sam", "Yamuna Vihar");
        SpecialCustomer Tim = new SpecialCustomer("Tim", "Okhla");
        RegularCustomer Kim = new RegularCustomer("Kim", "Rohini");
        RegularCustomer Jim = new RegularCustomer("Jim", "Pitampura");
        customer.add(Ram); customer.add(Sam); customer.add(Tim);
        customer.add(Kim); customer.add(Jim);
    }

    // display main menu
    void displayMainMenu()
    {
        System.out.println("Welcome to Zotato:");
        System.out.println("1. Enter as Restaurant Owner");
        System.out.println("2. Enter as Customer");
        System.out.println("3. Check User Details");
        System.out.println("4. Company Account Details");
        System.out.println("5. Exit");

        int query = sc.nextInt();
        switch(query)
        {
            case 1:
                displayRestaurant();
                break;
            case 2:
                displayCustomer();
                break;
            case 3:
                checkUserDetails();
                break;
            case 4:
                checkCompanyDetails();
                break;
            case 5:
                exitMain();
                break;
            default:
                System.out.println("Invalid Query!");
        }
    }

    // helper to print restaurant names
    public void printRestaurantNames()
    {
        System.out.println("Choose a Restaurant: ");
        // print all restaurants
        int i=1;
        for(Restaurant r : restaurant)
        {
            String suff = r.displayName();
            System.out.println(""+i+". "+suff);
            i++;
        }
    }

    // helper to print customer names
    public void printCustomerNames()
    {
        // print all customers
        int i=1;
        for(Customer c : customer)
        {
            String type = "" + c.getClass();
            type = type.substring(18, type.length());

            if(type=="RegularCustomer")
                type="";
            else
                type = "(" + type + ")";

            String suff=c.displayName();
            System.out.println(""+i+". "+suff+" "+type);
            i++;
        }
    }
    // MAIN MENU OPTION 1
    public void displayRestaurant()
    {

        printRestaurantNames();
        int query = sc.nextInt();

        displayRestaurantOptions(restaurant.get(query-1));
    }

    // helper
    public void displayRestaurantOptions(Restaurant h)
    {
        System.out.println("Welcome "+h.getName());
        System.out.println("1. Add Item");
        System.out.println("2. Edit Item");
        System.out.println("3. Print Rewards");
        System.out.println("4. Discount on Bill Value");
        System.out.println("5. Exit");

        int query = sc.nextInt();

        switch(query)
        {
            case 1:
                h.addFood();
                break;
            case 2:
                h.editFood();
                break;
            case 3:
                h.printRewards();
                break;
            case 4:
            {
                System.out.println("Enter the discount value: ");
                double disc = sc.nextDouble();
                h.setDiscount(disc);
                break;
            }
            case 5:
                exitRestaurant();
                break;
            default:
                System.out.println("Invalid Query!");
        }
        displayRestaurantOptions(h);

    }

    // exitRestaurant
    public void exitRestaurant()
    {
        displayMainMenu();
    }

    // MAIN MENU OPTION 2
    public void displayCustomer()
    {
        printCustomerNames();

        int query = sc.nextInt();

        displayCustomerOptions(customer.get(query-1));
    }

    // helper
    public void displayCustomerOptions(Customer c)
    {
        System.out.println("Welcome "+c.getName());
        System.out.println("Customer Menu");
        System.out.println("1. Select Restaurant");
        System.out.println("2. Checkout Cart");
        System.out.println("3. Reward Won");
        System.out.println("4. Print the Recent Orders");
        System.out.println("5. Exit");

        int query = sc.nextInt();
        switch(query)
        {
            case 1:
            {
                printRestaurantNames();
                int q=sc.nextInt();
                Restaurant r = restaurant.get(q-1);
                r.printFoods();
                System.out.println("Choose food item");
                int foodChoice = sc.nextInt();
                System.out.println("Enter Quantity");
                int qty = sc.nextInt();

                Food f = getFood(foodChoice, r.getFoodHash(), qty);
                f.setQuantity(qty);
                double price = c.placeOrder(f);


                c.getFood().add(f);

                if(c.getCart()==null)
                    c.setCart(new Cart(c.getFood(), price, r, 0));
                else
                {
                    c.getCart().setFood(c.getFood());
                    c.getCart().setTotalPrice(c.getCart().getTotalPrice()+price);
                }

                System.out.println("Item added to cart");
                break;
            }
            case 2:
            {
                // apply restaurant discount
                double discount = c.getCart().getRestaurant().getDiscount();
                double currPrice = c.getCart().getTotalPrice();
                currPrice-=discount*currPrice/100;
                if(currPrice>100 && c.getCart().getRestaurant().getCategory()=="Authentic")
                    currPrice-=50;
//                c.getCart().setTotalPrice(currPrice);

                double deliveryCharges = c.getDeliveryCharges();
                currPrice = c.customerDiscount(currPrice);
                c.getCart().setTotalPrice(currPrice);
                c.getCart().setDeliveryCharge(deliveryCharges);

                c.setWallet(currPrice+deliveryCharges);

                double reward = c.setRewardPoints(currPrice);

                Restaurant curr = c.getCart().getRestaurant();
                for(Restaurant r : restaurant)
                {
                    if(curr.getName()==r.getName())
                    {
                        r.setPoints(r.getPoints()+reward);
                        r.setRevenue(r.getRevenue()+currPrice);
                        r.setDeliveryCharge(r.getDeliveryCharge()+deliveryCharges);
                        break;
                    }
                }

                if(currPrice+deliveryCharges>c.getWallet()+c.getRewardPoints())
                    System.exit(0);

                c.printOrder();
                System.out.println("Delivery Charges: "+deliveryCharges);
                System.out.println("Total order value: "+(currPrice+deliveryCharges));
                System.out.println("1. Proceed to Checkout");
                int q = sc.nextInt();
                if(q!=1)
                {
                    System.out.println("Invalid Message");
                    System.exit(0);
                }
                else
                {
                    System.out.println(c.getCart().getFood().size()+" items bought for (incl. delivery charges) "+(currPrice+deliveryCharges));
                }
                c.getOrder().add(c.getCart());
                c.setCart(null);
                break;
            }
            case 3:
                c.rewardWon();
                break;
            case 4:
               c.printRecentOrder();
                break;
            case 5:
                exitCustomer();
                break;
            default:
                System.out.println("Invalid Query!");
        }
        displayCustomerOptions(c);
    }

    // helper to add food to cart
    public Food getFood(int ID, HashMap<Integer, Food> foodHash, int quantity)
    {
        Food currFood = foodHash.get(ID);
        if(currFood.getQuantity()<quantity)
        {
            System.out.println("Insufficient Quantity");
        }
        return currFood;
    }

    //exitCustomer
    public void exitCustomer()
    {
        displayMainMenu();
    }

    // MAIN MENU OPTION 3
    public void checkUserDetails()
    {
        System.out.println("1. Check Customer Details");
        System.out.println("2. Check Restaurant Details");

        int query = sc.nextInt();

        if(query==1)
        {
            printCustomerNames();
            int code = sc.nextInt();
            Customer c = customer.get(code-1);
            String type = "" + c.getClass();
            type = type.substring(18, type.length());

            if(type=="RegularCustomer")
                type="";
            else
                type = "(" + type + ")";

            System.out.println(c.getName()+", "+type+", "+c.getAddress()+", "+c.getWallet());
        }

        else if(query==2)
        {
            printRestaurantNames();
            int code = sc.nextInt();
            Restaurant r = restaurant.get(code-1);

        }

        else
        {
            System.out.println("Invalid Query");
        }

        displayMainMenu();
    }

    // MAIN MENU OPTION 4
    public void checkCompanyDetails()
    {
        double tax=0, dc=0;
        for(Restaurant r : restaurant)
        {
            tax+=0.01*r.getRevenue();
            dc+=0.01*r.getDeliveryCharge();
        }

        System.out.println("Total Company Balance - INR "+tax+" /-");
        System.out.println("Total Delivery Charges collected - INR "+dc+" /-");
        displayMainMenu();
    }

    // MAIN MENU OPTION 5
    public void exitMain()
    {
        System.exit(0);
    }
}
