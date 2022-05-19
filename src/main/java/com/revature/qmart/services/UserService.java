package com.revature.qmart.services;

import com.revature.qmart.dao.UserDAO;
import com.revature.qmart.util.custom_exception.InvalidUserException;

/* Purpose: validation ie. checks username, password, and retrieve data from our daos. */
public class UserService {
    // private final UserDAO userDAO;

    // change to not void
    public void login(String username, String password) {
        return;
    }
    public boolean isValidUsername(String username) {
        if (username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")) return true;

        throw new InvalidUserException("Invalid username. Username needs to be 8-20 characters long.");
    }

    public boolean isValidPassword(String password) {
        if (password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")) return true;

        throw new InvalidUserException("Invalid password. Minimum eight characters, at least one letter, one number and one special character.");
    }
}