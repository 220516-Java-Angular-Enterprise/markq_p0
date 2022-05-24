package com.revature.qmart.ui;


import com.revature.qmart.dao.StoreDAO;
import com.revature.qmart.models.Items;
import com.revature.qmart.models.QMart;
import com.revature.qmart.models.User;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class MainMenu implements IMenu {
    private final User user;

    public MainMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() throws FileNotFoundException {
        System.out.println("\nWelcome to our main menu " + user.getUsername());

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

                    case "1": // allow customer to browse all items
                       System.out.println("Here's a list of all our online items!");
                       // need to show items, cost, inventory, etc. to user
                        // show menu
                        // getItemsFromRestaurantDAO
                        browse();

                        break;
                    case "2": // signup, new customer
                      System.out.println("Order History");
                        break;
                    case "x":
                        System.out.println("\nGoodbye from main menu!");
                        break exit;
                    default:
                        System.out.println("\nInvalid input.");
                        break;

                }
            }
        }
    }
    private void displayWelcomeMainMsg() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("[1] View Store Items");
        System.out.println("[2] Order History");
        System.out.println("[x] Exit");
    }

    public void browse() throws FileNotFoundException {
        StoreDAO store = new StoreDAO();
        System.out.println(store.getAllItems());
    }
}
