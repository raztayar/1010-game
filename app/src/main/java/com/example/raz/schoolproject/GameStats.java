package com.example.raz.schoolproject;

import java.util.Date;

public class GameStats {

    private int score;
    private int numberOfShapesPlaced;
    private Date date;
    private long timerInMillis;
    //hotzone
    private long timerStartTime;
    private boolean isTimerRunning;


    public GameStats(){
        this.score = 0;
        this.numberOfShapesPlaced = 0;
        this.date = new Date();
        this.timerInMillis = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void startTimer() {
        timerStartTime = System.currentTimeMillis();
        isTimerRunning = true;
    }

    public void updateTimer() {
        if (!isTimerRunning) {
            throw new RuntimeException("timer isn't running");
        }
        timerInMillis += System.currentTimeMillis() - timerStartTime;
        timerStartTime = System.currentTimeMillis();
    }

    public void pauseTimer() {
        isTimerRunning = false;
    }
}
