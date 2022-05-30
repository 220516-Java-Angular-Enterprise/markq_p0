package com.revature.mindlight.models;

public class Items {
    private String itemid;

    private String itemname;

    private double itemcost;

    private String type;

    private String status;


    public Items() { super(); }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Items(String itemid, String itemname, double itemcost, String type, String status) {
        this.itemid = itemid;
        this.itemname = itemname;
        this.itemcost = itemcost;
        this.type = type;
        this.status = status;

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
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Items{" +
                "itemid='" + itemid + '\'' +
                ", itemname='" + itemname + '\'' +
                ", itemcost=" + itemcost +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
