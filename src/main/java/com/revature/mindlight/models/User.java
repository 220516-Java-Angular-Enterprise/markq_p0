package com.revature.mindlight.models;

public class User {
    private String address;
    private String city;
    private String state;
    private String id;
    private String username;
    private String password;
    private String role;
    public User() {
        super();
    }

    public User(String id, String username, String password, String role, String address, String city, String state) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.address = address;
        this.city = city;
        this.state = state;
    }
    public String getId() {return id;}
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {return username;}
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public void setAddress(String address) {this.address = address;}

    public void setCity(String city) {this.city = city;}

    public void setState(String state) {this.state = state;}

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return  "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", city=' " + city + '\'' +
                ", state=' " + state + '\'' +
                ", address=' " + address;
    }
}