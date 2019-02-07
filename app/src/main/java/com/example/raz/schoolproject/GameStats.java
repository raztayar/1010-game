package com.example.raz.schoolproject;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GameStats {

    private int score;
    private int numberOfShapesPlaced;
    private Date date;
    private long timerInMillis;
    //hotzone
    private long tempTimerStartTime;
    private boolean isTimerRunning = false;

    public GameStats(){
        this.score = 0;
        this.numberOfShapesPlaced = 0;
        this.date = new Date();
        this.timerInMillis = 0;
        startTempTimer();
    }

    public int getNumberOfShapesPlaced() {
        return numberOfShapesPlaced;
    }

    public void setNumberOfShapesPlaced(int numberOfShapesPlaced) {
        this.numberOfShapesPlaced = numberOfShapesPlaced;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void startTempTimer() {
        tempTimerStartTime = System.currentTimeMillis();
        isTimerRunning = true;
    }

    public void updateTimer() {
        if (!isTimerRunning) {
            throw new RuntimeException("timer isn't running");
        }
        timerInMillis += (System.currentTimeMillis() - tempTimerStartTime);
        tempTimerStartTime = System.currentTimeMillis();
    }

    public void stopTempTimer() {
        isTimerRunning = false;
    }

    public long getTimerInMillis() {
        return timerInMillis;
    }

    @Override
    public String toString() {
        return "*** DATE: " + new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(date) + " ***" +
                "\nscore: " + score +
                "\nNumber Of Shapes Placed: " + numberOfShapesPlaced +
                "\nTime: " + Utilities.millisToString(timerInMillis);
    }
}
