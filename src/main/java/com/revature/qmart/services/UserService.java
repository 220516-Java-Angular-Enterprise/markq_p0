package com.revature.qmart.services;

import com.revature.qmart.dao.UserDAO;
import com.revature.qmart.models.User;
import com.revature.qmart.util.annotations.Inject;
import com.revature.qmart.util.custom_exception.InvalidUserException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/* Purpose: validation ie. checks username, password, and retrieve data from our daos. */
public class UserService {
    @Inject
     private final UserDAO userDAO;
    @Inject
    public UserService(UserDAO userDAO) {

        this.userDAO = userDAO;
    }
    public User login(String username, String password) {

        User user = userDAO.getUserByUsernameAndPassword(username, password);

        if (isValidCredentials(user)) return user;

        return null;
    }
    public void register(User user) {

        userDAO.save(user);
    }
    public boolean isValidUsername(String username) {
        if (username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")) return true;

        throw new InvalidUserException("Invalid username. Username needs to be 8-20 characters long.");
    }

    // need to validate first user

    public boolean isNotDuplicateUsername(String username) throws IOException {

        List usernames = userDAO.getAllUsernames();

        if (usernames.contains(username)) throw new InvalidUserException("Username is already taken :(");

        return true;
    }
    public boolean isValidPassword(String password) {
        if (password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")) return true;

        throw new InvalidUserException("Invalid password. Minimum eight characters, at least one letter, one number and one special character.");
    }
    private boolean isValidCredentials(User user) {

        if (user.getUsername() == null && user.getPassword() == null) throw new InvalidUserException("Incorrect username and password.");
        else if (user.getUsername() == null) throw new InvalidUserException("Incorrect username.");
        else if (user.getPassword() == null) throw new InvalidUserException("Incorrect password.");

        return true;
    }


}