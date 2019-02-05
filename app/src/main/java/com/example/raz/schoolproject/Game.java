package com.example.raz.schoolproject;

import com.example.raz.schoolproject.Activities.GameActivity;

public class Game {

    public final static int BOARD_HEIGHT = 10, BOARD_WIDTH = 10;

    private IShape[] shapeQueue;

    private GameActivity gameActivity;

    private LocalDataBase gameDataBase;

    private ShapeType[][] board;
    private GameStats gameStats;

    public Game(GameActivity gameActivity, GameStats gameStats){
        this.board = new ShapeType[BOARD_WIDTH][BOARD_HEIGHT];
        this.gameStats = gameStats;
        this.shapeQueue = new IShape[3];
        this.gameActivity = gameActivity;
        gameDataBase = new LocalDataBase(gameActivity);
    }

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
        shapeQueue[i] = Utilities.createRandomShape();
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

    private void removeRow(int row) {
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            board[row][i] = null;
        }
    }

    private void removeColumn(int column) {
        for (int i = 0; i < BOARD_WIDTH; i++) {
            board[i][column] = null;
        }
    }

    private boolean isRowFull(int row) {
        for (int i = 0; i < BOARD_WIDTH; i++) {
            if (board[row][i] == null) return false;
        }
        return true;
    }

    private boolean isColumnFull(int column) {
        for (int i = 0; i < BOARD_WIDTH; i++) {
            if (board[i][column] == null) return false;
        }
        return true;
    }

    public int getNumOfFullRowsAndColumns() {
        int num = 0;
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            if (isRowFull(i)) num++;
        }
        for (int i = 0; i < BOARD_WIDTH; i++) {
            if (isColumnFull(i)) num++;
        }
        return num;
    }

    public void removeFullRowsAndColumns(){
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            if(isRowFull(i)) {
                removeRow(i);
            }
        }

        for (int i = 0; i < BOARD_WIDTH; i++) {
            if (isColumnFull(i)) {
                removeColumn(i);
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

    public void saveToDataBase(String userID) {

        gameDataBase.save("board:" + userID, board);

        String[] shapeTypes = new String[3];
        for(int i = 0; i < shapeQueue.length; i++) {
            gameDataBase.save("queueSlot" + i + ":" + userID, shapeQueue[i]);
            if (shapeQueue[i] != null) {
                shapeTypes[i] = shapeQueue[i].getClass().getName();
            }
        }
        gameDataBase.save("shapeTypes:" + userID, shapeTypes);

        gameDataBase.save("gameStats:" + userID, gameStats);
    }

    public void loadFromDataBase(String userID) {
        ShapeType[][] boardLoad = gameDataBase.load("board:" + userID, ShapeType[][].class);
        if (boardLoad != null) {
            board = boardLoad;
        }

        String[] shapeTypes = gameDataBase.load("shapeTypes:" + userID, String[].class);
        if(shapeTypes != null) {
            for (int i = 0; i < shapeTypes.length; i++) {
                if (shapeTypes[i] != null) {
                    try {
                        shapeQueue[i] = (IShape) gameDataBase.load("queueSlot" + i + ":" + userID, Class.forName(shapeTypes[i]));
                    } catch (ClassNotFoundException e) {
                        shapeQueue[i] = null;
                    }
                }
            }
        }

        GameStats gameStatsLoad = gameDataBase.load("gameStats:" + userID, GameStats.class);
        if (gameStatsLoad != null) {
            gameStats = gameStatsLoad;
        }
    }

    public void updateHighScore(String userID) {
        int currentHighScore = gameDataBase.load("highScoreGameStats:" + userID, GameStats.class).getScore();
        if(gameStats.getScore() > currentHighScore) {
            gameDataBase.save("highScoreGameStats:" + userID, gameStats);
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
}