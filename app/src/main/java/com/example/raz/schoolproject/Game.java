package com.example.raz.schoolproject;

import android.widget.LinearLayout;

import com.example.raz.schoolproject.Activities.GameActivity;
import com.example.raz.schoolproject.Shapes.Corner_Big_BottomRight;

public class Game {

    private final static int BOARD_HEIGHT = 10, BOARD_WIDTH = 10;

    private IShape[] shapeQueue; // maybe use tags

    private GameActivity gameActivity;

    private ShapeType[][] board;
    private GameStats gameStats;

    public Game(GameStats gameStats, GameActivity gameActivity){
        this.board = new ShapeType[BOARD_WIDTH][BOARD_HEIGHT];
        this.gameStats = gameStats;
        this.shapeQueue = new IShape[3];
        this.gameActivity = gameActivity;
    }

    public Game(GameActivity gameActivity){
        this.board = new ShapeType[BOARD_WIDTH][BOARD_HEIGHT];
        this.gameStats = new GameStats();
        this.shapeQueue = new IShape[3];
        this.gameActivity = gameActivity;
    }

    public GameStats getGameStats() {
        return gameStats;
    }

    public IShape[] getShapeQueue() { return shapeQueue; }

    public void updateTimeElapsed() {
        gameStats.setElapsedTimeInMillis(System.currentTimeMillis() - gameStats.getTimeOfStartInMillis());
    }

    public void bringShapesToQueue() {
        if(hasShapesInQueue()) throw new RuntimeException("There are still shapes in queue");
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
        IShape shape = Utilities.createRandomShape();

        gameActivity.getQueueSlotLayout(i).addView(shape.createShapeAsTableLayout(gameActivity));
        shapeQueue[i] = shape;
    }

    public void removeShapesFromQueue(){
        for(int i = 0; i < 3; i++){
            removeShapeFromSlot(i);
        }
    }

    public void removeShapeFromSlot(int i){
        LinearLayout slotLayout = gameActivity.getQueueSlotLayout(i);
        slotLayout.removeAllViews();
        gameActivity.setCurrentSlotIndex(-1);
        shapeQueue[i] = null;
    }

    public ShapeType[][] getBoard() {
        return board;
    }

    public void removeFullRowsAndColumns(){
        boolean[] filledRows = new boolean[BOARD_HEIGHT];
        for (int i = 0; i < BOARD_HEIGHT; i++){
            filledRows[i] = true;
        }

        boolean[] filledColumns = new boolean[BOARD_WIDTH];
        for (int i = 0; i < BOARD_WIDTH; i++){
            filledColumns[i] = true;
        }

        for (int i = 0; i < BOARD_HEIGHT; i++){
            for (int j = 0; j< BOARD_WIDTH; j++){
                if(board[i][j] == null){
                    filledRows[i] = false;
                    filledColumns[j] = false;
                }
            }
        }

        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (filledRows[i] || filledColumns[j]) {
                    board[i][j] = null;
                }
            }
        }
    }

    public boolean isGameOver() {
        for (int i = 0; i < 3; i++) {
            if (shapeQueue[i] != null){
                if (shapeQueue[i].isPlaceableSomewhere(board)) {
                    return false;
                }
            }
        }
        return true;
    }
}