package com.revature.qmart.ui;

import com.revature.qmart.models.User;
import com.revature.qmart.services.UserService;
import com.revature.qmart.util.annotations.Inject;
import com.revature.qmart.util.custom_exception.InvalidUserException;

import java.util.Scanner;
import java.util.UUID;

/* class purpose is to ask user to login, signup, or exit. */
public class StartMenu implements IMenu {
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
    public void start() {
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
                        System.out.println("\nGoodbye!");
                        break exit;
                    default:
                        System.out.println("\nInvalid input.");
                        break;

                }
            }
        }
    }

    private void displayWelcomeMsg() {
        System.out.println("\nHello! Thank you for choosing Q-Mart.");
        System.out.println("\n");
        System.out.println("[1] Are you a returning Customer? Login");
        System.out.println("[2] New Customer? Signup");
        System.out.println("[x] Exit");
    }

    private void login() {
        String username;
        String password;
//        User user = new User();
        Scanner scan = new Scanner(System.in);

        while(true) {
            System.out.println("\nLogging into account");
            System.out.println("\nEnter username: ");
            username = scan.nextLine();

            System.out.println("\nPassword: ");
            password = scan.nextLine();

            try {
                User user = userService.login(username, password);

                if (user.getRole().equals("ADMIN")) new AdminMenu().start();
                else new MainMenu(user).start();
                break;
            } catch (InvalidUserException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void signup() {
        String username;
        String password;
        Scanner scan = new Scanner(System.in);

        completeExit: {
            while (true) {

                System.out.println("\nThank you for choosing Q-Mart for all your shopping needs.\"");
                System.out.println("Let's go ahead and create your new account!");

                while(true) {
                    // ask user to enter an appropriate username
                    System.out.println("\nPlease enter a username: ");
                    username = scan.nextLine();

                    // if username invalid, then break out of loop and re-prompt username
                    try {
                        if (userService.isValidUsername(username)) {
                            if (userService.isNotDuplicateUsername(username)) break;
                        }
                    } catch (InvalidUserException e) {
                        System.out.println(e.getMessage());
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
                                User user = new User(UUID.randomUUID().toString(), username, password, "DEFAULT");

                                userService.register(user);

                                /* Calling the anonymous class MainMenu.start() to navigate to the main menu screen. */
                                /* We are also passing in a user object, so we know who is logged in. */
                                new MainMenu(user).start();

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
