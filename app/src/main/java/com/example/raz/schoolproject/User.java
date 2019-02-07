package com.example.raz.schoolproject;

import java.util.ArrayList;
import java.util.Random;

public class User {

    public static User currentUserID;

    private long userID;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;


    public User() {
        userID = 1;
        username = "Guest";
        password = "";
        email = "";
        phoneNumber = "";
    }

    public User(long userID, String username, String password, String email, String phoneNumber) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public long getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static long generateNewUserID(UserDAL userDAL) {
        Random rnd = new Random();
        long userID = rnd.nextInt(9998) + 2;
        while (userDAL.idExists(userID)) {
            userID = rnd.nextInt(9998) + 2;
        }
        return userID;
    }
}
