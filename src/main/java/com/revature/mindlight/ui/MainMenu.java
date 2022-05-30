package com.revature.mindlight.ui;


import com.revature.mindlight.models.User;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainMenu implements IMenu {
    private final User user;

    public MainMenu(User user) {
        this.user = user;
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

                    case "1": // allow customer to browse all items
                       System.out.println("Here's a list of all our online items!");
                       // need to show items, cost, inventory, etc. to user
                        // show menu
                        // getItemsFromRestaurantDAO
                        break;
                    case "2": // signup, new customer
                      System.out.println("Order History");
                        break;
                    case "3": // view account details, make changes if necessary
                        System.out.println("View and edit account");
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
        System.out.println("\n");
        System.out.println("[1] View Store Items");
        System.out.println("[2] Order History");
        System.out.println("[3] View Account");
        System.out.println("[x] Save & Exit");
    }

}
