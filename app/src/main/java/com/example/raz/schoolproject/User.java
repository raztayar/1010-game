package com.example.raz.schoolproject;

public class User {

    public static User currentUser;

    private long userID;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    public User() {
        userID = 0;
        username = "";
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
}
