package com.revature.qmart.dao;

import com.revature.qmart.models.Items;
import com.revature.qmart.models.QMart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StoreDAO implements CrudDAO<QMart> {
    String path = "src/main/resources/database/items.txt";
    @Override
    public void save(QMart obj) {

    }

    @Override
    public void update(QMart obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public QMart getById(String id) {
        return null;
    }

    @Override
    public List<QMart> getAll() {

        return null;
    }

    public List getAllItems() throws FileNotFoundException {
        // not being used
        Items items = new Items();

        StringBuilder itemData = new StringBuilder();
        ArrayList<String> allItemsList = new ArrayList<>();

        // reads items, checks if file is not null
        try (BufferedReader buffer = new BufferedReader(
                new FileReader(path))) {
            String str;
            while ((str = buffer.readLine()) != null) {
                str = str.replaceAll(Pattern.quote(":DEFAULT"), ":DEFAULT:");
                itemData.append(str);

                String[] itemArr = itemData.toString().split(":");

                String id = itemArr[itemArr.length - 5];
                String itemname = itemArr[itemArr.length - 4];
                String itemcost = itemArr[itemArr.length -3];
                String stock = itemArr[itemArr.length -2];
                String location = itemArr[itemArr.length -1];

                allItemsList.add(id);
                allItemsList.add(itemname);
                allItemsList.add(itemcost);
                allItemsList.add(stock);
                allItemsList.add(location);

            }
        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }

        return allItemsList;
    }
}
