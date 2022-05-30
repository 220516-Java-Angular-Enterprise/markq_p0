package com.revature.mindlight.dao;

import com.revature.mindlight.models.Items;
import com.revature.mindlight.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO implements CrudDAO<Items> {
    Connection con = DatabaseConnection.getCon();
    @Override
    public void save(Items obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO items (id, itemname, price, itemtype, itemstatus) VALUES (?,?,?,?,?)");
            ps.setString(1, obj.getItemid());
            ps.setString(2, obj.getItemname());
            ps.setDouble (3, obj.getItemcost());
            ps.setString(4, obj.getType());
            ps.setString(5, obj.getStatus());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when saving to the database.");
        }
    }

    @Override
    public void update(Items obj) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE items set itemstatus = 'SOLD' WHERE id = ?");
            ps.setString(1, obj.getItemid());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when saving to the database.");
        }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Items getById(String id) {

        return null;
    }

    public List<Items> getActiveItems() {
        List<Items> items = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("select itemname, price, count(itemname) as \"stock\"\n" +
                    "FROM items \n" +
                    "group by itemtype, itemname, price\n");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Items item = new Items();
                item.setItemname(rs.getString("itemname"));
                item.setItemcost(rs.getInt("price"));
                item.setType(rs.getString("stock"));

                items.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when trying to get data from the database.");
        }
        return items;
    }

    @Override
    public List<Items> getAll() {
        List<Items> items = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM items WHERE itemstatus = 'STOCK'");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Items item = new Items();
                item.setItemid(rs.getString("id"));
                item.setItemname(rs.getString("itemname"));
                item.setItemcost(rs.getInt("price"));
                item.setType(rs.getString("itemtype"));

                items.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when trying to get data from the database.");
        }
        return items;
    }
}
