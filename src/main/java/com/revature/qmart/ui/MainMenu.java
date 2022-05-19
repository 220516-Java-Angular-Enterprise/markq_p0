package com.revature.qmart.ui;


import com.revature.qmart.models.User;

public class MainMenu implements IMenu {
    private final User user;

    public MainMenu(User user) {
        this.user = user;
    }

    @Override
    public void start() {
        System.out.println("\nWelcome to the main menu " + user.getUsername());
    }
}
