package com.example.raz.schoolproject;

import android.util.Log;

import java.util.Date;

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
}
