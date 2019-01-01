package com.example.raz.schoolproject;

public class User {

    private static int currentID = 0;

    private int userID;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    private int highScore;
    private int coins;
    private GameStats[] gameHistory;
    private Game currentGame;
    //hotZone

    public User(String username, String password, String email, String phoneNumber, int highScore, int coins, GameStats[] gameHistory, Game currentGame){
        this.userID = getNewUserID();
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.highScore = highScore;
        this.coins = coins;
        this.gameHistory = gameHistory;
        this.currentGame = currentGame;
    }

    public User(String username, String password, String email, String phoneNumber){
        this.userID = getNewUserID();
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.highScore = -1;
        this.coins = 0;
        this.gameHistory = new GameStats[100];
        this.currentGame = null;
    }


    private int getNewUserID(){
        int id = currentID;
        currentID++;
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public GameStats[] getGameHistory() {
        return gameHistory;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }
}
