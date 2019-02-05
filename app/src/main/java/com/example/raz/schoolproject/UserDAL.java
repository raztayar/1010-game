package com.example.raz.schoolproject;

import android.content.Context;

import java.util.ArrayList;

public class UserDAL {

    private LocalDataBase userDataBase;

    public UserDAL(Context context) {
        userDataBase = new LocalDataBase(context);
    }

    public User getUserByUsername(String username) {
        long[] userIDs = userDataBase.load("userIDs", long[].class);
        if (userIDs != null) {
            for (long id : userIDs) {
                User user = userDataBase.load("user:" + String.valueOf(id), User.class);
                if(user != null && user.getUsername().equals(username)) {
                    return user;
                }
            }
        }
        return null;
    }

    public User getUserByUserID(long id) {
        return userDataBase.load("user:" + String.valueOf(id), User.class);
    }

    public void saveUser(User user) {
        userDataBase.save("user:" + String.valueOf(user.getUserID()), user);

        long[] userIDs = userDataBase.load("userIDs", long[].class);
        if (userIDs == null) {
            userIDs = new long[10];
        }
        userIDs = Utilities.addToLongArray(userIDs, user.getUserID());
        userDataBase.save("userIDs", userIDs);
    }

    public boolean idExists(long id) {
        long[] userIDs = userDataBase.load("userIDs", long[].class);
        if (userIDs != null) {
            for (long idInArray : userIDs) {
                if (id == idInArray) return true;
            }
        }
        return false;
    }
}
