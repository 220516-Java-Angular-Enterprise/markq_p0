package com.revature.qmart.dao;

import com.revature.qmart.models.User;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class UserDAO implements CrudDAO<User> {
    String path = "src/main/resources/database/user.txt";
    @Override
    public void save(User obj) {
        try {
            File file  = new File(path);
            // checked exception, have to handle immediately
            FileWriter fw = new FileWriter(file, true);
            fw.write(obj.toFileString());
            fw.close();

        }  catch (IOException e) {
            throw new RuntimeException("An error has occurred when writing to a file.");
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
        return null;
    }

    public List<String> getAllUsernames() throws IOException {
        StringBuilder userData = new StringBuilder();
        ArrayList<String> usernames = new ArrayList<>();

        // to read filepath
        try (BufferedReader buffer = new BufferedReader(
                new FileReader(path))) {

            // Condition check via buffer.readLine() method
            // holding true up to that the while loop runs
            String str;
            while ((str = buffer.readLine()) != null) {
                str = str.replaceAll(Pattern.quote(":DEFAULT"), ":DEFAULT:");
                userData.append(str);

                String[] userArr = userData.toString().split(":");
//                System.out.println(userArr.length);

                String username = userArr[userArr.length - 3];
                if (!usernames.contains(username)) {
                    usernames.add(username);
//                    System.out.println(usernames);
                }
                // need to stop from looping an extra time...
//                System.out.println("Out");

            }
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
