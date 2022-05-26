package com.revature.qmart.dao;

import com.revature.qmart.models.User;
import com.revature.qmart.util.database.DatabaseConnection;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

public class UserDAO implements CrudDAO<User> {
    String path = "src/main/resources/database/user.txt";
    Connection con = DatabaseConnection.getCon();
    @Override
    public void save(User obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, username, password, role, address, city, state) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1,obj.getId());
            ps.setString(2,obj.getUsername());
            ps.setString(3,obj.getPassword());
            ps.setString(4,obj.getRole());
            ps.setString(5,"NULL");
            ps.setString(6,"NULL");
            ps.setString(7,"NULL");
            ps.executeUpdate();

        } catch (SQLException e){
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
        return null;
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
                user.setId(rs.getString("username"));
                user.setId(rs.getString("password"));
                user.setId(rs.getString("role"));

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
                usernames.add(rs.getString("username"));
            }
        } catch (SQLException e) {
//            throw new RuntimeException("An error occurred when trying to get data from the database.");
            new Exception().printStackTrace();
        }
        return usernames;

    }
    public User getUserByUsernameAndPassword(String un, String pw) {
        User user = new User();
        StringBuilder userData = new StringBuilder();
        ArrayList<String> userDataList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String str;
            while ((str = br.readLine()) != null) {
                str = str.replaceAll(Pattern.quote(":DEFAULT"), ":DEFAULT:");
                userData.append(str);

                String[] userArr = userData.toString().split(":");

                String id = userArr[userArr.length - 4];
                String username = userArr[userArr.length - 3];
                String password = userArr[userArr.length - 2];
                String role = userArr[userArr.length -1];

                // adding [id, username, password, role] individually, need to optimize this. Get help
                userDataList.add(id);
                userDataList.add(username);
                userDataList.add(password);
                userDataList.add(role);
//                System.out.println(userDataList);

                if (un.equals(username)) {
                    user.setId(id);
                    user.setUsername(username);
                    user.setRole(role);

                    if (pw.equals(password)) user.setPassword(password);
                    else break;
                } else if (pw.equals(password)) user.setPassword(password);
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException("An error occurred when trying to access the file.");
        } catch (IOException e) {
            throw new RuntimeException("An error occurred when trying to access the file information.");
        }

        return user;
    }

}
