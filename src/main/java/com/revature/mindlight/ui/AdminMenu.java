package com.revature.mindlight.ui;

import com.revature.mindlight.models.Items;
import com.revature.mindlight.models.Order;
import com.revature.mindlight.models.User;
import com.revature.mindlight.services.ItemService;
import com.revature.mindlight.services.OrderService;
import com.revature.mindlight.services.UserService;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class AdminMenu implements IMenu {

    private final User user;
    private final UserService userService;
    private final ItemService itemService;

    private final OrderService orderService;


    public AdminMenu(User user, UserService userService, ItemService itemService, OrderService orderService) {
        this.user = user;
        this.userService = userService;
        this.itemService = itemService;
        this.orderService = orderService;
    }


    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        completeExit:{
            while(true) {
                displayAdminMsg();

                System.out.println("\nEnter: ");
                String input = scan.nextLine();

                switch (input) { // accounts

                    case "1":
                        viewAccount();
                        break;
                    case "2":
                        viewOrders();
                        break;
                    case "3":
                        viewItems();
                        break;
                    case "4":
                        viewStores();
                        break;
                    case "5":
                        viewUsers();
                        break;
                    case "x":
                        break completeExit; // for some reason, looping through this section twice

                }
            }
        }
    }
    private void displayAdminMsg() {
        System.out.println("Good Day " + user.getUsername() + "." + " What would you like to do?");
        System.out.println("[1] Account");
        System.out.println("[2] Orders");
        System.out.println("[3] Items");
        System.out.println("[4] Store Details");
        System.out.println("[5] Users");
        System.out.println("[x] Exit");
    }

    private void viewAccount() {

        Scanner scan = new Scanner(System.in);

        System.out.println("\n+------------------------+");
        System.out.println("| ...All Account Details... |");
        System.out.println("+------------------------+");
        userService.viewAccount(user);

        System.out.print("\nEnter: ");
        String input = scan.nextLine();
            // need to add to view account, for now works fine with displaying

    }
    private void viewOrders() {

        exit:
        {
            while (true) {
                System.out.println("[1] All Store Orders");
                System.out.println("[2] Personal Orders");
                System.out.println("[x] Return to Admin Menu");

                Scanner scan = new Scanner(System.in);
                System.out.println("\nEnter: ");
                String input = scan.nextLine();

                switch (input) {
                    case "1":
                        System.out.println("Show all orders");
                        break;
                    case "2":
                        System.out.println("Show personal orders");
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
    public void viewItems() {
        System.out.println("\nAll Store Items");

        Scanner scan = new Scanner(System.in);
        List<Items> allitems = itemService.allItems();

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

        System.out.println("|Item Name|" + "|Price|" + "|Stock|");

        System.out.println("NooFocus: " + "$75.00 " + "{" + cognitives.size() + "}");
        System.out.println("Sleepytime Elixer: " + "$25.00 " +"{" + sedatives.size() + "}");
        System.out.println("Mindlight Protein Shake: " + "$35.00 " +"{" + workout.size() + "}");
        System.out.println("\n");

        exit:
        {
            System.out.println("[1] Create New");
            System.out.println("[2] Update");
            System.out.println("[3] Delete");
            System.out.println("[4] Add");
            System.out.println("[x] Admin Menu");

            System.out.print("\nEnter: ");

            switch (scan.nextLine()) {
                case "1":
                    createItem();
                    break;
                case "2":
//                    updateItem();
                    break;
                case "3":
//                    deleteItem();
                    break;
                case "4":
                    addToCart(allitems);
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

    private void viewStores() {
        System.out.println("All store details");
    }

    private void viewUsers() {
        System.out.println("\n+------------------------+");
        System.out.println("| ...All User Details... |");
        System.out.println("+------------------------+");
        System.out.println("\n");
        System.out.println("ID " + "USERNAME " + " PASSWORD " + " ROLE " + " CITY " + " STATE " + " ADDRESS");

        List<User> users = userService.viewAllUsers();

        for(int i=0; i<users.size(); i++){
            System.out.println("[" + (i+1) + "]" + " " + users.get(i).getUsername() + " " +
                    users.get(i).getPassword() + " " + users.get(i).getRole() + " " +
                    users.get(i).getCity() + " " + users.get(i).getState() + " " +
                    users.get(i).getAddress());
        }
        System.out.print("\nEnter: ");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        // need to add to view account, for now works fine with displaying

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
                System.out.println("[1] Noofocus: $75.00");
                System.out.println("[2] Sleepytime Elixer: $25.00");
                System.out.println("[3] Mindlight Protein Shake: $35.00");
                System.out.println("[c] Checkout");
                System.out.println("[x] All Items Options");


                System.out.println("\nEnter: ");
                String input = scan.nextLine();
                switch (input) {

                    case "1":
                        try {
                            cart.add(cognitives.remove(0));
                        } catch (Exception e) {
                            throw new IndexOutOfBoundsException("Sorry, that item is out of stock");
                        }
                        break;
                    case "2":
                        try {
                            cart.add(sedatives.remove(0));
                        } catch (Exception e) {
                            throw new IndexOutOfBoundsException("Sorry, that item is out of stock");
                        }
                        break;
                    case "3":
                        try {
                            cart.add(workout.remove(0));
                        } catch (Exception e) {
                            throw new IndexOutOfBoundsException("Sorry, that item is out of stock");
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
                        viewItems();
                        break exit;

                    default:
                        System.out.println("\nInvalid input.");
                        break;
                }
                System.out.println("\n");
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
    private void createItem() {
        Scanner scan = new Scanner(System.in);
        Items item = new Items();

        exit:
        {
            while (true) {
                System.out.println("\n+------------------------+");
                System.out.println("| ...Creating new item... |");
                System.out.println("+------------------------+");

                item.setItemid((UUID.randomUUID().toString()));

                System.out.print("Name: ");
                item.setItemname(scan.nextLine());

                System.out.print("\nPrice: ");
                item.setItemcost(Double.parseDouble(scan.nextLine()));

                System.out.print("\nType: ");
                item.setType(scan.nextLine());

                // default status is in stock
                item.setStatus("STOCK");

                System.out.println("\nPlease confirm item (y/n)");
                System.out.println("\n" + item);

                switch (scan.nextLine()) {
                    case "y":
                        itemService.register(item);
                        break exit;
                    case "n":
                        break;
                    default:
                        System.out.println("\nInvalid input.");
                        break;
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

                        addToCart(items); // prompting user to return to add to cart menu
                        break exit;
                    case "n":
                        viewItems();
                        break;
                    default:
                        System.out.println("\nInvalid input.");
                        break;
                }
            }
        }
    }
    private void updateItem() {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("\n+-------------------------------------------+");
            System.out.println("| Please select an item to update price |");
            System.out.println("+-------------------------------------------+");
            itemService.allItems();
//            System.out.println(items);
//            for (int i = 0; i < items.getSize(); i++) {
//                System.out.println("[" + (i + 1) + "] " + restaurants.get(i).getName());
//            }

            System.out.print("\nEnter: ");
            int input = scan.nextInt() - 1;
            scan.nextLine();

//            if (input >= 0 && input < restaurants.size()) {
//                Restaurant selectedResto = restaurants.get(input);
//
//                System.out.print("\nNew name: ");
//                String name = scan.nextLine();
//
//                if (restaurantService.updateName(name, selectedResto.getId())) {
//                    System.out.println("\nUpdate was successful!");
//                    break;
//                }
//            } else System.out.println("\nInvalid restaurant selection!");
        }
    }
}
