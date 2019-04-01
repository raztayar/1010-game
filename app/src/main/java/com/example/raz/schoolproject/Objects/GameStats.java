package com.example.raz.schoolproject.Objects;

import com.example.raz.schoolproject.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GameStats {

    private int score;
    private int shapesPlacedCount;
    private Date date;
    private long timeCountInMillis;
    private long tempTimerStartTime;
    private boolean isTimerRunning = false;

    public GameStats(){
        this.score = 0;
        this.shapesPlacedCount = 0;
        this.date = new Date();
        this.timeCountInMillis = 0;
        startTempTimer();
    }

    public GameStats(int score, int shapesPlacedCount, Date date, long timeCountInMillis) {
        this.score = score;
        this.shapesPlacedCount = shapesPlacedCount;
        this.date = date;
        this.timeCountInMillis = timeCountInMillis;
    }

    public int getShapesPlacedCount() {
        return shapesPlacedCount;
    }

    public void setShapesPlacedCount(int shapesPlacedCount) {
        this.shapesPlacedCount = shapesPlacedCount;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTimeCountInMillis(long timeCountInMillis) {
        this.timeCountInMillis = timeCountInMillis;
    }

    public void startTempTimer() {
        tempTimerStartTime = System.currentTimeMillis();
        isTimerRunning = true;
    }

    public void updateTimer() {
        if (!isTimerRunning) {
            throw new RuntimeException("timer isn't running");
        }
        timeCountInMillis += (System.currentTimeMillis() - tempTimerStartTime);
        tempTimerStartTime = System.currentTimeMillis();
    }

    public void stopTempTimer() {
        isTimerRunning = false;
    }

    public long getTimeCountInMillis() {
        return timeCountInMillis;
    }

    @Override
    public String toString() {
        return "*** DATE: " + new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(date) + " ***" +
                "\nscore: " + score +
                "\nNumber Of Shapes Placed: " + shapesPlacedCount +
                "\nTime: " + Utilities.millisToString(timeCountInMillis);
    }
}
