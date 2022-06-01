package com.revature.mindlight.ui;

import com.revature.mindlight.models.Items;
import com.revature.mindlight.models.Mindlight;
import com.revature.mindlight.models.Order;
import com.revature.mindlight.models.User;
import com.revature.mindlight.services.ItemService;
import com.revature.mindlight.services.MindlightService;
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

    private final MindlightService mindlightService;

    public AdminMenu(User user, UserService userService, ItemService itemService, OrderService orderService, MindlightService mindlightService) {
        this.user = user;
        this.userService = userService;
        this.itemService = itemService;
        this.orderService = orderService;
        this.mindlightService = mindlightService;
    }


    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit:{
            while(true) {
                displayAdminMsg();

                System.out.println("\nEnter: ");
                String input = scan.nextLine();

                switch (input) {

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
                        break exit; // for some reason, looping through this section twice

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

    private void itemDisplayMsg(List<Items> c, List<Items> s, List<Items> w) {
        System.out.println("|Item Name|" + "|Price|" + "|Stock|");
        System.out.println("NooFocus: " + "$75.00 " + "{" + c.size() + "}");
        System.out.println("Sleepytime Elixer: " + "$25.00 " +"{" + s.size() + "}");
        System.out.println("Mindlight Protein Shake: " + "$35.00 " +"{" + w.size() + "}");
        System.out.println("\n");
    }
    private void itemShopDisplayMsg(List<Items> c, List<Items> s, List<Items> w) {
        System.out.println("|Item Name|" + "|Price|" + "|Stock|");
        System.out.println("[1] NooFocus: " + "$75.00 " + "{" + c.size() + "}");
        System.out.println("[2] Sleepytime Elixer: " + "$25.00 " +"{" + s.size() + "}");
        System.out.println("[3] Mindlight Protein Shake: " + "$35.00 " +"{" + w.size() + "}");
        System.out.println("[c] Checkout");
        System.out.println("[x] Exit");
        System.out.println("\n");
    }

    private void viewAccount() {
        System.out.println("\n+------------------------+");
        System.out.println("| ...All Account Details... |");
        System.out.println("+------------------------+");

        System.out.println("Name: " + userService.viewAccountDetails(user).getUsername());
        System.out.println("Password: " + userService.viewAccountDetails(user).getPassword());
        System.out.println("Role: " + userService.viewAccountDetails(user).getRole());
        System.out.println("ID: " + userService.viewAccountDetails(user).getId() +"\n");

    }
    private void viewOrders() {

        exit:
        {
            while (true) {
                System.out.println("[1] All Store Orders");
                System.out.println("[2] Personal Orders");
                System.out.println("[x] Admin Menu ");

                Scanner scan = new Scanner(System.in);
                System.out.println("\nEnter: ");
                String input = scan.nextLine();

                switch (input) {
                    case "1":
                        List<Order> o = orderService.allOrders();
                        allOrders(o);
                        break exit;
                    case "2":
                        List<Order> opers = orderService.allOrdersbyUserId(user);
                        allOrders(opers);
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
    public void viewItems() {
        System.out.println("\n+------------------------+");
        System.out.println("| ...All Store Items... |");
        System.out.println("+------------------------+");

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

        itemDisplayMsg(cognitives, sedatives, workout);
        exit:
        {
            System.out.println("[1] Create New");
//            System.out.println("[2] Update"); // need to add update and delete item features
//            System.out.println("[3] Delete");
            System.out.println("[2] Shop Items");
            System.out.println("[x] Admin Menu");

            System.out.print("\nEnter: ");

            switch (scan.nextLine()) {
                case "1":
                    createItem();
                    break;
                case "2":
                    addToCart(cognitives, sedatives, workout);
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
    private void printOrdersBySort(List<Order> orders) {
        System.out.println("\n+---------------------------------------------+");
        System.out.println("| .....Order Details..... |");
        System.out.println("+---------------------------------------------+");

        for (int i = 0; i < orders.size(); i++) {
            System.out.println(("[" + (i+1) + "]" +" ORDERID: " + orders.get(i).getId())
                    + ",  " + "ORDERTOTAL: $" + orders.get(i).getTotal()
                    + ",  " + "DATE FULFILLED: " + orders.get(i).getDate()
                    + ",  STOREID: |" + orders.get(i).getStoreid() + "|");
        }
        System.out.println("\n");
        System.out.println("[o] Orders ");
        System.out.println("[x] Return to Admin Menu");
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
                        break exit;
                    default:
                        System.out.println("\nInvalid input.");
                        break;
                }
            }
        }
    }
    private void allOrders(List<Order> orders) {

        exit:
        {
            while (true) {

                Scanner scan = new Scanner(System.in);

                System.out.println("[1] Orders By Cost: Low to High");
                System.out.println("[2] Orders By Cost: High to Low");
                System.out.println("[3] Orders By Date: Most Recent Last");
                System.out.println("[4] Orders By Date: Most Recent First");
                System.out.println("[x] Orders Menu");

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
                        viewOrders();
                        break exit;
                    default:
                        System.out.println("\nInvalid input.");
                        break;
                }
            }
        }
    }
    private void viewStores() {
        List<Mindlight> store = mindlightService.allStores();
        for (int i=0; i<store.size(); i++) {
            System.out.println("Name: " + store.get(i).getName());
            System.out.println("ID: " + store.get(i).getId());
            System.out.println("City: " + store.get(i).getCity());
            System.out.println("State: " + store.get(i).getState() +"\n");
        }
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
                    users.get(i).getPassword() + "\n") ;
        }
    }
    private void addToCart(List<Items> cognitives, List<Items> sedatives, List<Items> workout) {
        List<Items> cart = new ArrayList<>();

        exit:
        {
            while (true) {
                Scanner scan = new Scanner(System.in);
                // display items menu
                System.out.print("\n");
                System.out.println("Select Item to Add");

                itemShopDisplayMsg(cognitives, sedatives, workout);

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
                        if (cart.size() == 0) {
                            System.out.println("*** Your cart is empty ***");
                        } else {
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

                System.out.println("Select Item To Restock");
                System.out.println("[1] NooFocus");
                System.out.println("[2] Sleepytime Elixer");
                System.out.println("[3] Mindlight Protein Shake");

                System.out.print("\nEnter: ");
                String input = scan.nextLine();

                item.setItemid((UUID.randomUUID().toString()));
                item.setStatus("STOCK");

                switch(input) {
                    case "1":
                        item.setItemname("NooFocus");
                        item.setItemcost(75.00);
                        item.setType("Cognitive");
                        break;
                    case "2":
                        item.setItemname("Sleepytime Elixer");
                        item.setItemcost(25.00);
                        item.setType("Sedative");
                        break;
                    case "3":
                        item.setItemname("Mindlight Protein Shake");
                        item.setItemcost(35.00);
                        item.setType("Workout");
                }

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
                        System.out.println("\n");

                        itemService.update(items); // updating item status to SOLD

                        start();
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

    // needs to be finished, as well as delete items
    private void updateItem() {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("\n+-------------------------------------------+");
            System.out.println("| Please select an item to update price |");
            System.out.println("+-------------------------------------------+");
            itemService.allItems();

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
