package com.revature.mindlight.dao;

import com.revature.mindlight.models.User;
import com.revature.mindlight.util.database.DatabaseConnection;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserDAO implements CrudDAO<User> {
    String path = "src/main/resources/database/user.txt";
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(User obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, username, password, role, address, city, state) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getPassword());
            ps.setString(4, obj.getRole());
            ps.setString(5, "NULL");
            ps.setString(6, "NULL");
            ps.setString(7, "NULL");
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when saving to the database.");
        }

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(User obj) {

    }

    @Override
    public User getById(String id) {
        User user = new User();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users where id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = new User(rs.getString("id"),
                        rs.getString ("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("state"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return user;
    }


    @Override
    public List getAll() {
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setAddress(rs.getString("address"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));

                users.add(user);

            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when trying to get data from the database.");
        }
        return users;
    }

    public List<String> getAllUsernames() throws IOException {
        List<String> usernames = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT username FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usernames.add(rs.getString("username").toLowerCase());
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when trying to get data from the database.");
        }
        return usernames;

    }
}
