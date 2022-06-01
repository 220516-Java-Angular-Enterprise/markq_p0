package com.revature.mindlight.ui;

import com.revature.mindlight.dao.*;
import com.revature.mindlight.models.User;
import com.revature.mindlight.services.ItemService;
import com.revature.mindlight.services.MindlightService;
import com.revature.mindlight.services.OrderService;
import com.revature.mindlight.services.UserService;
import com.revature.mindlight.util.annotations.Inject;
import com.revature.mindlight.util.custom_exception.InvalidUserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

/* class purpose is to ask user to login, signup, or exit. */
public class StartMenu implements IMenu {
    String path = "src/main/resources/database/user.txt";
    /*
    * example of dependency injection
    * relies on the UserService class to retrieve data from the database, like a waiter at a restaurant
    * validates username, password, inventory, order history, store locations, etc.
    */
    @Inject
    private final UserService userService; // dependency injection
    @Inject
    public StartMenu(UserService userService) { // start menu constructor that takes an object of the UserService class
        
        this.userService = userService;
    }

    @Override
    public void start() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        // starting interactive display and validation
        exit:{
            while (true) {
                displayWelcomeMsg();

                System.out.println("\nEnter: ");
                String input = scan.nextLine();

                switch (input) {

                    case "1": // login, returning customer
                        login();
                        break;
                    case "2": // signup, new customer
                        signup();
                        break;
                    case "x":
                        System.out.println("\nGoodbye from Mindlight!");
                        break exit;
                    default:
                        System.out.println("\nInvalid input.");
                        break;

                }
            }
        }
    }

    private void displayWelcomeMsg() {
        System.out.println("#####################################################");
        System.out.println("#########" + " Thank you for choosing Mindlight! " + "#########");
        System.out.println("#####################################################");
        System.out.println("[1] Are you a returning Customer? Login");
        System.out.println("[2] New Customer? Signup");
        System.out.println("[x] Exit");
    }

    private void login() {
        String username;
        String password;
        Scanner scan = new Scanner(System.in);

        while(true) {
            System.out.println("\nLogging into account");
            System.out.println("\nEnter username: ");
            username = scan.nextLine();

            System.out.println("\nPassword: ");
            password = scan.nextLine();

            try {
                User user = userService.login(username, password);
                if (user.getRole().equals("ADMIN")) new AdminMenu(user, new UserService(new UserDAO()), new ItemService(new ItemDAO()), new OrderService(new OrderDAO(), new CartDAO()), new MindlightService(new StoreDAO())).start();
                else new MainMenu(user, new UserService(new UserDAO()), new ItemService(new ItemDAO()), new OrderService(new OrderDAO(), new CartDAO())).start();
                break;
            } catch (InvalidUserException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void signup() throws FileNotFoundException {
        String username;
        String password;
        Scanner scan = new Scanner(System.in);

        completeExit: {
            while (true) {
                System.out.println("Let's go ahead and create your new account!");


                while(true) {
                    // ask user to enter an appropriate username
                    System.out.println("\nPlease enter a username: ");
                    username = scan.nextLine();

                    // need to find out if they are the first user
                    // maybe check database

                    // if username invalid, then break out of loop and re-prompt username
                    try {
                        if (userService.isValidUsername(username)) {
                            if (userService.isNotDuplicateUsername(username)) break;
                        }
                    } catch (InvalidUserException e) {
                        System.out.println(e.getMessage());

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                while(true) {
                    System.out.println("\nPlease enter a password for your account: ");
                    password = scan.nextLine();
                    /* If the password is valid confirm the password again. Else re-enter password. */
                    try {
                        if (userService.isValidPassword(password)) {
                            /* Asking user to enter in password again. */
                            System.out.print("\nRe enter password again: ");
                            String confirm = scan.nextLine();

                            /* If the two password equals each other, break. Else re-enter password. */
                            if (password.equals(confirm)) break;
                            else System.out.println("Password does not match :(");
                        }
                    } catch (InvalidUserException e) {
                        System.out.println(e.getMessage());
                    }
                }

                confirmExit: {
                    while (true) {
                        /* Asking user to confirm username and password. */
                        System.out.println("\nPlease confirm your credentials (y/n)");
                        System.out.println("\nUsername: " + username);
                        System.out.println("Password: " + password);

                        System.out.print("\nEnter: ");
                        String input = scan.nextLine();

                        /* Switch statement for user input. Basically yes or no. */
                        switch (input) {
                            case "y": /* Break out of the entire loop. */
                                /* If yes, we instantiate a User object to store all the information into it. */
                                User user = new User(UUID.randomUUID().toString(), username, password, "DEFAULT", "NULL", "NULL", "NULL");

                                userService.register(user);

                                /* **** Starting Main Menu **** */
                                /* Calling the anonymous class MainMenu.start() to navigate to the main menu screen. */
                                /* We are also passing in a user object, so we know who is logged in. */
                                new MainMenu(user, new UserService(new UserDAO()), new ItemService(new ItemDAO()), new OrderService(new OrderDAO(), new CartDAO())).start();

                                break completeExit;

                            case "n":
                                /* Re-enter in credentials again, exit out of credential loop */
                                break confirmExit;
                            default:
                                System.out.println("Invalid Input.");
                                break;
                        }

                    }

                }

            }

        }

    }

}
