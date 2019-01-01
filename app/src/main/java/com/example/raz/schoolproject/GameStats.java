package com.example.raz.schoolproject;

import java.time.LocalDateTime;
import java.util.Date;

public class GameStats {

    private int score;
    private int numberOfShapesPlaced;
    private long timeOfStartInMillis;
    private long elapsedTimeInMillis;
    //hotzone
    private Date date;

    public GameStats(int score, int numberOfShapesPlaced, long timeOfStartInMillis, long elapsedTimeInMillis, Date date){
        this.score = score;
        this.numberOfShapesPlaced = numberOfShapesPlaced;
        this.timeOfStartInMillis = timeOfStartInMillis;
        this.elapsedTimeInMillis = elapsedTimeInMillis;
        this.date = date;
    }
    public GameStats(){
        this.score = 0;
        this.numberOfShapesPlaced = 0;
        this.timeOfStartInMillis = System.currentTimeMillis();
        this.elapsedTimeInMillis = 0;
        this.date = new Date();
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public int getNumberOfShapesPlaced() {
        return numberOfShapesPlaced;
    }

    public void setNumberOfShapesPlaced(int numberOfShapesPlaced) {
        this.numberOfShapesPlaced = numberOfShapesPlaced;
    }

    public long getTimeOfStartInMillis() {
        return timeOfStartInMillis;
    }
    public void setTimeOfStartInMillis(long timeOfStartInMillis) {
        this.timeOfStartInMillis = timeOfStartInMillis;
    }

    public long getElapsedTimeInMillis() {
        return elapsedTimeInMillis;
    }

    public void setElapsedTimeInMillis(long elapsedTimeInMillis) {
        this.elapsedTimeInMillis = elapsedTimeInMillis;
    }
}
