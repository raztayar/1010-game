package com.example.raz.schoolproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDAL2 extends SQLiteOpenHelper {

    private LocalDataBase currentUserDataBase;

    private static final String DATABASE_NAME = "userDatabase";
    private static final String TABLE_USER = "userTable";
    private static final int DATABASE_VERSION = 1;

    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String COINS = "coins";

    private static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " VARCHAR, " +
            PASSWORD + " VARCHAR, " + EMAIL + " VARCHAR, " + PHONE_NUMBER + " VARCHAR, " + COINS + " INTEGER);";

    private SQLiteDatabase database;
    private String[] allColumns = {ID, USERNAME, PASSWORD, EMAIL, PHONE_NUMBER, COINS};

    public UserDAL2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = this.getWritableDatabase();
        currentUserDataBase = new LocalDataBase(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE If EXISTS " + TABLE_USER);
            onCreate(db);
    }

    public User createUser(User user) {
        ContentValues values = new ContentValues();
        values.put(USERNAME, user.getUsername());
        values.put(PASSWORD, user.getPassword());
        values.put(EMAIL, user.getEmail());
        values.put(PHONE_NUMBER, user.getPhoneNumber());
        values.put(COINS, user.getCoins());

        long insertId = database.insert(TABLE_USER, null, values);
        user.setUserID(insertId);
        return user;
    }

    public User getUserByID(long rowId) {
        if (rowId == -1) {
            return new User();
        }

        Cursor cursor = database.query(TABLE_USER, allColumns, ID + "=" + rowId, null, null, null, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            long id = cursor.getLong(cursor.getColumnIndex(ID));
            String username = cursor.getString(cursor.getColumnIndex(USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(PASSWORD));
            String email = cursor.getString(cursor.getColumnIndex(EMAIL));
            String phoneNumber = cursor.getString(cursor.getColumnIndex(PHONE_NUMBER));
            int coins = cursor.getInt(cursor.getColumnIndex(COINS));

            return new User(id, username, password, email, phoneNumber, coins);
        }
        return null;
    }



    public User getUserByUsername(String name) {
        Cursor cursor = database.query(TABLE_USER, allColumns, USERNAME + "= ?", new String[]{name}, null, null, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            long id = cursor.getLong(cursor.getColumnIndex(ID));
            String username = cursor.getString(cursor.getColumnIndex(USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(PASSWORD));
            String email = cursor.getString(cursor.getColumnIndex(EMAIL));
            String phoneNumber = cursor.getString(cursor.getColumnIndex(PHONE_NUMBER));
            int coins = cursor.getInt(cursor.getColumnIndex(COINS));

            return new User(id, username, password, email, phoneNumber, coins);
        }
        return null;
    }

    public void setCurrentUser(User user) {
        currentUserDataBase.save("currentUserID", user.getUserID());
    }

    public User getCurrentUser() {
        Long currentUserID = currentUserDataBase.load("currentUserID", Long.class);
        if (currentUserID != null) {
            return getUserByID(currentUserID);
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

    public void updateUser(User updatedUser) {
        ContentValues values = new ContentValues();
        values.put(USERNAME, updatedUser.getUsername());
        values.put(PASSWORD, updatedUser.getPassword());
        values.put(EMAIL, updatedUser.getEmail());
        values.put(PHONE_NUMBER, updatedUser.getPhoneNumber());
        values.put(COINS, updatedUser.getCoins());

        database.update(TABLE_USER, values,ID + "=" + updatedUser.getUserID(), null);
    }
}
