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
                User user = getUserByUserID(id);
                if(user != null && user.getUsername().equals(username)) {
                    return user;
                }
            }
        }
        return null;
    }

    private User getUserByUserID(long id) {
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

    public void updateCurrentUser(User user) {
        userDataBase.save("currentUserID", user.getUserID());
    }

    public User getCurrentUser() {
        Long currentUserID = userDataBase.load("currentUserID", long.class);
        if(currentUserID != null) {
            User user = getUserByUserID(currentUserID);
            if (user != null) {
                return user;
            }
        }
        return new User();
    }

    public boolean isLoginValid(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }
}
