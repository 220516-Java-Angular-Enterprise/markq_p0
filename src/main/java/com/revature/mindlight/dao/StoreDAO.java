package com.revature.mindlight.dao;

import com.revature.mindlight.models.Mindlight;
import com.revature.mindlight.models.Order;
import com.revature.mindlight.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO implements CrudDAO<Mindlight> {

    Connection con = DatabaseConnection.getCon();
    @Override
    public void save(Mindlight obj) {

    }

    @Override
    public void update(Mindlight obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Mindlight getById(String id) {
        return null;
    }

    @Override
    public List<Mindlight> getAll() {
        List<Mindlight> stores = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM stores");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Mindlight store = new Mindlight();

                store.setId(rs.getString("id"));
                store.setName(rs.getString("name"));
                store.setCity(rs.getString("city"));
                store.setState(rs.getString("state"));

                stores.add(store);

            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when trying to get data from the database.");
        }
        return stores;
    }
}
