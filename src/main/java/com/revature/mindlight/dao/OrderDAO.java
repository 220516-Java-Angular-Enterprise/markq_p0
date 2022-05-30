package com.revature.mindlight.dao;

import com.revature.mindlight.models.Order;
import com.revature.mindlight.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDAO implements CrudDAO<Order>{
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Order obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders (id, ordername, total, date, orderstatus, storeid) VALUES (?,?,?,?,?,?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getOrdername());
            ps.setDouble(3, obj.getTotal());
            ps.setString(4, obj.getDate());
            ps.setString(5, obj.getOrderstatus());
            ps.setString(6, obj.getStoreid());
            ps.executeUpdate();
        }
//        } catch (SQLException e) {
//            throw new RuntimeException("An error occurred when saving to the database.");
//        }
        catch (Throwable e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Order obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Order getById(String id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
