package com.revature.mindlight.models;

import java.util.List;

public class Order {
    private String id;
    private String ordername;
    private float total;
    private String date;
    private String orderstatus;
    private String storeid;

    private List<Items> itemsList;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Items> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Items> itemsList) {
        this.itemsList = itemsList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public Order() { super(); }
    public Order(String id, String ordername, float total, String date, String orderstatus, String storeid,
                 List<Items> itemsList, User user) {
        this.id = id;
        this.ordername = ordername;
        this.total = total;
        this.date = date;
        this.orderstatus = orderstatus;
        this.storeid = storeid;
        this.itemsList = itemsList;
        this.user = user;
    }
}
