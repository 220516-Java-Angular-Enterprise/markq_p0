package com.revature.qmart.models;

public class Items {
    private String itemid;

    private String itemname;

    private double itemcost;

    private int stock;
    private String location;

    public String getLocation() {
        return location;
    }

    public Items() { super(); }

    public Items(String itemid, String itemname, double itemcost, int stock) {
        this.itemid = itemid;
        this.itemname = itemname;
        this.itemcost = itemcost;
        this.stock = stock;

    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public double getItemcost() {
        return itemcost;
    }

    public void setItemcost(double itemcost) {
        this.itemcost = itemcost;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Items{" +
                "itemid='" + itemid + '\'' +
                ", itemname='" + itemname + '\'' +
                ", itemcost=" + itemcost +
                ", stock=" + stock +
                ", location='" + location + '\'' +
                '}';
    }
}
