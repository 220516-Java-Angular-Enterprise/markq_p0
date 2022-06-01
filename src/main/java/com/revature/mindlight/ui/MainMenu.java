package com.revature.mindlight.ui;


import com.revature.mindlight.models.Items;
import com.revature.mindlight.models.Order;
import com.revature.mindlight.models.User;
import com.revature.mindlight.services.ItemService;
import com.revature.mindlight.services.OrderService;
import com.revature.mindlight.services.UserService;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu implements IMenu {
    private final User user;

    private final UserService userService;
    private final ItemService itemService;

    private final OrderService orderService;


    public MainMenu(User user, UserService userService, ItemService itemService, OrderService orderService) {
        this.user = user;
        this.userService = userService;
        this.itemService = itemService;
        this.orderService = orderService;
    }

    @Override
    public void start() {
        System.out.println("\nWelcome to our main menu! " + user.getUsername());

        Scanner scan = new Scanner(System.in);
        // starting interactive display and validation
        exit:
        {
            while (true) {
                // main menu selection
                displayWelcomeMainMsg();

                System.out.println("\nEnter: ");
                String input = scan.nextLine();
                switch (input) {

                    case "1":
                        List<Items> allitems = itemService.allItems();
                        addToCart(allitems);
                        break;
                    case "2": // signup, new customer
                        viewOrders();
                        break;
                    case "3": // view account details, make changes if necessary
                        viewAccount();
                        break;
                    case "x":
                        break exit;
                    default:
                        System.out.println("\nInvalid input.");
                        break;
                }
            }
        }
    }
    private void displayWelcomeMainMsg() {
        System.out.println("Please make a selection");
        System.out.println("\n");
        System.out.println("[1] Shop Items");
        System.out.println("[2] View Orders");
        System.out.println("[3] View Account");
        System.out.println("[x] Exit");
    }
    private void itemDisplayMsg(List<Items> c, List<Items> s, List<Items> w) {
        System.out.println("|Item Name|" + "|Price|" + "|Stock|");
        System.out.println("[1] NooFocus: " + "$75.00 " + "{" + c.size() + "}");
        System.out.println("[2] Sleepytime Elixer: " + "$25.00 " +"{" + s.size() + "}");
        System.out.println("[3] Mindlight Protein Shake: " + "$35.00 " +"{" + w.size() + "}");
        System.out.println("[c] Checkout");
        System.out.println("[x] Exit");
        System.out.println("\n");
    }

    private void viewAccount() {

        Scanner scan = new Scanner(System.in);

        System.out.println("\n+------------------------+");
        System.out.println("| ...All Account Details... |");
        System.out.println("+------------------------+");

        System.out.println("Name: " + userService.viewAccountDetails(user).getUsername());
        System.out.println("Password: " + userService.viewAccountDetails(user).getPassword());
        System.out.println("Role: " + userService.viewAccountDetails(user).getRole());
        System.out.println("ID: " + userService.viewAccountDetails(user).getId() +"\n");

    }

    private void viewOrders() {

        Scanner scan = new Scanner(System.in);
        exit:
        {
            while (true) {
                List<Order> orders = orderService.allOrdersbyUserId(user);

                System.out.println("[1] Orders By Cost: Low to High");
                System.out.println("[2] Orders By Cost: High to Low");
                System.out.println("[3] Orders By Date: Most Recent Last");
                System.out.println("[4] Orders By Date: Most Recent First");
                System.out.println("[x] Return to Main Menu");

                System.out.println("\nEnter: ");
                String input = scan.nextLine();

                Comparator<Order> comparebyDate = Comparator.comparing(Order::getDate);
                Comparator<Order> comparebyPrice = Comparator.comparing(Order::getTotal);

                switch (input) {
                    case "1":
                        Collections.sort(orders, comparebyPrice);
                        printOrdersBySort(orders);
                        break exit;
                    case "2":
                        Collections.sort(orders, comparebyPrice.reversed());
                        printOrdersBySort(orders);
                        break exit;
                    case "3":
                        Collections.sort(orders, comparebyDate);
                        printOrdersBySort(orders);
                        break exit;
                    case "4":
                        Collections.sort(orders, comparebyDate.reversed());
                        printOrdersBySort(orders);
                        break exit;
                    case "x":
                        break exit;
                    default:
                        System.out.println("\nInvalid input.");
                        break;
                }
            }
        }
    }
    private void printOrdersBySort(List<Order> orders) {
        System.out.println("\n+---------------------------------------------+");
        System.out.println("| ....." + user.getUsername() + " Order Details..... |");
        System.out.println("+---------------------------------------------+");

        for (int i = 0; i < orders.size(); i++) {
            System.out.println(("[" + (i+1) + "]" +" ORDERID: " + orders.get(i).getId())
                    + ",  " + "ORDERTOTAL: $" + orders.get(i).getTotal()
                    + ",  " + "DATE FULFILLED: " + orders.get(i).getDate()
                    + ",  STOREID: |" + orders.get(i).getStoreid() + "|");
        }
        System.out.println("\n");
        System.out.println("[o] Orders ");
        System.out.println("[x] Return to Main Menu");

        Scanner scan = new Scanner(System.in);
        exit:
        {
            while (true) {

                System.out.println("\nEnter: ");
                String input = scan.nextLine();
                switch (input) {
                    case "o":
                        viewOrders();
                        break exit;
                    case "x":
                        start();
                        break exit;
                    default:
                        System.out.println("\nInvalid input.");
                        break;
                }
            }
        }
    }
    private void addToCart(List<Items> allitems) {

        List<Items> cart = new ArrayList<>();
        List<Items> cognitives = new ArrayList<>();
        List<Items> sedatives = new ArrayList<>();
        List<Items> workout = new ArrayList<>();

        for(Items i: allitems) {
            if (Objects.equals(i.getItemname(), "NooFocus")) {
                cognitives.add(i);
            }
            if (Objects.equals(i.getItemname(), "Sleepytime Elixer")) {
                sedatives.add(i);
            }
            if (Objects.equals(i.getItemname(), "Mindlight Protein Shake")) {
                workout.add(i);
            }
        }
        exit:
        {
            while(true) {
                Scanner scan = new Scanner(System.in);
                // display items menu
                System.out.print("\n");
                System.out.println("Select Item to Add");
                itemDisplayMsg(cognitives, sedatives, workout);

                System.out.println("\nEnter: ");
                String input = scan.nextLine();
                switch (input) {

                    case "1":
                        if (!cognitives.isEmpty()) {
                            cart.add(cognitives.remove(0));
                        } else {
                            System.out.println("Sorry, that item is out of stock.");
                        }
                        break;
                    case "2":
                        if (!sedatives.isEmpty()) {
                            cart.add(sedatives.remove(0));
                        } else {
                            System.out.println("Sorry, that item is out of stock.");
                        }
                        break;
                    case "3":
                        if (!workout.isEmpty()) {
                            cart.add(workout.remove(0));
                        } else {
                            System.out.println("Sorry, that item is out of stock.");
                        }
                        break;
                    case "c":
                        if(cart.size() == 0) {
                            System.out.println("*** Your cart is empty ***");
                        }
                        else {
                            createOrder(cart);
                            break exit;
                        }
                    case "x":
                        break exit;

                    default:
                        System.out.println("\nInvalid input.");
                        break;
                }
                System.out.println("\n+------------------------+");
                System.out.println("| ...Cart Details... |");
                System.out.println("+------------------------+");
                System.out.println("Current Items In Cart");

                for(Items i: cart) {
                    System.out.println(i.getItemname() +" " + "Price: $" + i.getItemcost());
                }
            }
        }
    }
    private void createOrder(List<Items> items) {
        Scanner scan = new Scanner(System.in);
        Order order = new Order();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        int total = 0;
        for (Items i : items) {
            total += i.getItemcost();
        }

        exit:
        {
            while (true) {
                System.out.println("\n+------------------------+");
                System.out.println("| ...Making new order... |");
                System.out.println("+------------------------+");

                order.setUser(user);
                order.setItemsList(items);
                order.setId(UUID.randomUUID().toString());
                order.setOrdername(user.getUsername() + "'s " + "Order");
                order.setDate(formatter.format(date));

                order.setOrderstatus("NEW");
                order.setStoreid("1");

                order.setTotal(total);

                System.out.println("\nPlease confirm order details (y/n)");
                System.out.println("\n" + "Order ID: " + order.getId() + "\n" +
                        "Ordername: " + order.getOrdername() + "\n" +
                        "Date: " + order.getDate() + "\n" +
                        "Orderstatus: " + order.getOrderstatus() + "\n" +
                        "Total: " + "$"+ order.getTotal());

                switch (scan.nextLine()) {
                    case "y":
                        orderService.makeCartOrder(order); // inserting orderid, itemid, and userid into junction table
                        orderService.makeOrder(order); // inserting unique order into order table

                        System.out.println("Congratulations " + user.getUsername() + "!!" + " Your order was successful.");
                        itemService.update(items); // updating item status to SOLD
                        break exit;
                    case "n":
                        start();
                        break;
                    default:
                        System.out.println("\nInvalid input.");
                        break;
                }
            }
        }
    }
}
