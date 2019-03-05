package com.example.raz.schoolproject.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.raz.schoolproject.LocalDataBase;
import com.example.raz.schoolproject.Objects.GameStats;

import java.util.ArrayList;
import java.util.Date;

public class StatsDAL extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "statsDatabase";
    private static final String TABLE_STATS = "statsTable";
    private static final int DATABASE_VERSION = 1;

    private static final String USER_ID = "userID";
    private static final String SCORE = "score";
    private static final String SHAPES_PLACED_COUNT = "shapesPlacedCount";
    private static final String DATE = "date";
    private static final String TIME_COUNT_IN_MILLIS = "timeCountInMillis";

    private static final String CREATE_TABLE_STATS = "CREATE TABLE IF NOT EXISTS " + TABLE_STATS + "(" + USER_ID + " INTEGER, " + SCORE + " INTEGER, " +
            SHAPES_PLACED_COUNT + " INTEGER, " + DATE + " INTEGER, " + TIME_COUNT_IN_MILLIS + " INTEGER);";

    private SQLiteDatabase database;
    private String[] allColumns = {USER_ID, SCORE, SHAPES_PLACED_COUNT, DATE, TIME_COUNT_IN_MILLIS};

    public StatsDAL(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STATS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE If EXISTS " + TABLE_STATS);
        onCreate(db);
    }

    public void createStats(GameStats stats, long userID) {
        ContentValues values = new ContentValues();
        values.put(USER_ID, userID);
        values.put(SCORE, stats.getScore());
        values.put(SHAPES_PLACED_COUNT, stats.getShapesPlacedCount());
        values.put(DATE, stats.getDate().getTime());
        values.put(TIME_COUNT_IN_MILLIS, stats.getTimeCountInMillis());

        database.insert(TABLE_STATS, null, values);
    }

    public ArrayList<GameStats> getAllStatsByUserID(long userID) {
        ArrayList<GameStats> allStats = new ArrayList<>();
        Cursor cursor = database.query(TABLE_STATS, allColumns, USER_ID + "= ?", new String[]{String.valueOf(userID)}, null, null, DATE);

        while (cursor.moveToNext()) {
            int score = cursor.getInt(cursor.getColumnIndex(SCORE));
            int shapesPlacedCount = cursor.getInt(cursor.getColumnIndex(SHAPES_PLACED_COUNT));
            long dateInMillis = cursor.getLong(cursor.getColumnIndex(DATE));
            long timeCountInMillis = cursor.getLong(cursor.getColumnIndex(TIME_COUNT_IN_MILLIS));

            allStats.add(new GameStats(score, shapesPlacedCount, new Date(dateInMillis), timeCountInMillis));
        }

        return allStats;
    }
}
