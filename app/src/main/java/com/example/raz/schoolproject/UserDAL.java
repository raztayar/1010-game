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

    public void updateUser(User user) {
        if (user.getUserID() != 1) {
            userDataBase.save("user:" + String.valueOf(user.getUserID()), user);
        }
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

    public void updateCurrentUser(User user) {
        userDataBase.save("currentUserID", user.getUserID());
    }

    public boolean isLoginValid(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }

    public GameStats[] getStatsHistoryByUserID(long userID) {
        GameStats[] statsHistory = userDataBase.load("statsHistory:" + String.valueOf(userID), GameStats[].class);
        if (statsHistory != null) {
            return statsHistory;
        }
        return new GameStats[0];
    }
}
