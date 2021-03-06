package com.example.raz.schoolproject.Objects;

import com.example.raz.schoolproject.Activities.GameActivity;
import com.example.raz.schoolproject.DAL.StatsDAL;
import com.example.raz.schoolproject.LocalDataBase;
import com.example.raz.schoolproject.ShapeType;
import com.example.raz.schoolproject.Utilities;

import java.sql.Array;
import java.util.Arrays;

public class Game {

    public final static int BOARD_HEIGHT = 10, BOARD_WIDTH = 10;

    private GameActivity gameActivity;

    private LocalDataBase gameDataBase;

    private IShape[] shapeQueue;
    private ShapeType[][] board;
    private GameStats gameStats;

    private boolean[] fullRows = new boolean[BOARD_WIDTH];
    private boolean[] fullColumns = new boolean[BOARD_HEIGHT];

    public Game(GameActivity gameActivity){
        this.board = new ShapeType[BOARD_WIDTH][BOARD_HEIGHT];
        this.gameStats = new GameStats();
        this.shapeQueue = new IShape[3];
        this.gameActivity = gameActivity;
        gameDataBase = new LocalDataBase(gameActivity);
    }

    public GameStats getGameStats() {
        return gameStats;
    }

    public IShape[] getShapeQueue() { return shapeQueue; }

    public void setBoard(ShapeType[][] board) {
        this.board = board;
    }

    public void setShapeQueueAt(int i, IShape shape) {
        if (i < 0 || i > 2) throw new RuntimeException("invalid queue index");

        shapeQueue[i] = shape;
    }

    public void setGameStats(GameStats gameStats) {
        this.gameStats = gameStats;
    }

    public void bringShapesToQueue() {
        if(hasShapesInQueue()) {
            throw new RuntimeException("There are still shapes in queue");
        }
        for(int i = 0; i<3; i++){
            bringShapeToSlot(i);
        }
    }

    public boolean hasShapesInQueue() {
        for (IShape slot : shapeQueue) {
            if(slot != null) return true;
        }
        return false;
    }

    private void bringShapeToSlot(final int i) {
        shapeQueue[i] = Shape.createRandomShape();
    }

    public void removeShapesFromQueue(){
        for(int i = 0; i < 3; i++){
            removeShapeFromSlot(i);
        }
    }

    public void removeShapeFromSlot(int i){
        gameActivity.setCurrentSlotIndex(-1);
        shapeQueue[i] = null;
    }

    public ShapeType[][] getBoard() {
        return board;
    }

    public void updateFullLines(){
        boolean[] fullRows = new boolean[BOARD_HEIGHT];
        Arrays.fill(fullRows, true);
        boolean[] fullColumns = new boolean[BOARD_WIDTH];
        Arrays.fill(fullColumns, true);

        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (board[i][j] == null) {
                    fullRows[i] = false;
                    fullColumns[j] = false;
                }
            }
        }

        this.fullRows = fullRows;
        this.fullColumns = fullColumns;
    }

    public void removeFullLines() {
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (fullRows[i] || fullColumns[j]) {
                    board[i][j] = null;
                }
            }
        }
    }

    public int getNumOfFullLines() {
        int count = 0;

        for (boolean fullRow : fullRows) {
            if (fullRow) count++;
        }
        for (boolean fullColumn : fullColumns) {
            if (fullColumn) count++;
        }

        return count;
    }

    public boolean isGameOver() {
        for (int i = 0; i < 3; i++) {
            if (shapeQueue[i] != null && shapeQueue[i].isPlaceableSomewhere(board)) {
                return false;
            }
        }
        return true;
    }

    public void saveStatsToHistory(long userID) {
        new StatsDAL(gameActivity).createStats(gameStats, userID);
    }

    public void updateHighScore(long userID) {
        Integer currentHighScore = gameDataBase.load("highScoreGameStats:" + String.valueOf(userID), GameStats.class).getScore();
        if(currentHighScore == null || gameStats.getScore() > currentHighScore) {
            gameDataBase.save("highScoreGameStats:" + String.valueOf(userID), gameStats);
        }
    }

    public void addScore(int scoreToAdd) {
        gameStats.setScore(gameStats.getScore() + scoreToAdd);
    }

    public void pauseAndUpdateTimer() {
        gameStats.updateTimer();
        gameStats.stopTempTimer();
    }

    public void resumeTimer() {
        gameStats.startTempTimer();
    }

    public void raiseShapesPlacedCountByOne() {
        gameStats.setShapesPlacedCount(gameStats.getShapesPlacedCount()+1);
    }

    public boolean[] getFullRows() {
        return fullRows;
    }

    public boolean[] getFullColumns() {
        return fullColumns;
    }
}