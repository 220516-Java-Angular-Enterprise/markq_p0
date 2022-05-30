package com.revature.mindlight;

import com.revature.mindlight.dao.UserDAO;
import com.revature.mindlight.services.UserService;
import com.revature.mindlight.ui.StartMenu;
import com.revature.mindlight.util.database.DatabaseConnection;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/* class purpose to start application, like a key in the ignition */
public class MainDriver {
    public static void main(String[] args) throws FileNotFoundException, SQLException {
//        UserService userService = new UserService();
//        UserDAO userDAO = new UserDAO();

        // anonymous function, will disappear after the start method executes...
        new StartMenu(new UserService(new UserDAO())).start();
//        System.out.println(DatabaseConnection.getCon());
//        PreparedStatement ps = con.prepareStatement("SELECT * FROM items");
//        ResultSet rs = ps.executeQuery();
//        System.out.println(rs);
    }

//    static Connection con = DatabaseConnection.getCon();
}
