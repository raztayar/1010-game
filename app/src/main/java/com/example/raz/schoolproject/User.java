package com.example.raz.schoolproject;

import java.util.ArrayList;
import java.util.Random;

public class User {

    private long userID;
    private int coins;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;


    public User() {
        userID = -1;
        username = "Guest";
        password = "";
        email = "";
        phoneNumber = "";
        coins = 0;
    }

    public User(long userID, String username, String password, String email, String phoneNumber, int coins) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.coins = coins;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addCoins(int coins) {
        setCoins(getCoins() + coins);
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
