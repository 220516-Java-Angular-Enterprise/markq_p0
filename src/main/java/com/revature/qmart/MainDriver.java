package com.revature.qmart;

import com.revature.qmart.dao.UserDAO;
import com.revature.qmart.services.UserService;
import com.revature.qmart.ui.StartMenu;
import com.revature.qmart.util.database.DatabaseConnection;

import java.io.FileNotFoundException;

/* class purpose to start application, like a key in the ignition */
public class MainDriver {
    public static void main(String[] args) throws FileNotFoundException {
//        UserService userService = new UserService();
//        UserDAO userDAO = new UserDAO();

        // anonymous function, will disappear after the start method executes...
        new StartMenu(new UserService(new UserDAO())).start();
//        System.out.println(DatabaseConnection.getCon());


    }
}
