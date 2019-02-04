package com.example.raz.schoolproject;

import android.graphics.Color;

import java.util.HashMap;

public class Theme {

    private HashMap<ShapeType, Integer> colorHashMap;

    private int backgroundColor;
    private int secondaryColor;
    private int emptySquareColor;
    private int scoreColor;

    public Theme() {
        this.colorHashMap = new HashMap<>();
        colorHashMap.put(ShapeType.SQUARE_SMALL, Color.parseColor("#47369E"));
        colorHashMap.put(ShapeType.SQUARE_MEDIUM, Color.parseColor("#01F31A"));
        colorHashMap.put(ShapeType.SQUARE_BIG, Color.parseColor("#0EDE7D"));
        colorHashMap.put(ShapeType.LINE_OF_TWO, Color.parseColor("#FFBB00"));
        colorHashMap.put(ShapeType.LINE_OF_THREE, Color.parseColor("#FF7300"));
        colorHashMap.put(ShapeType.LINE_OF_FOUR, Color.parseColor("#FF3F4F"));
        colorHashMap.put(ShapeType.LINE_OF_FIVE, Color.parseColor("#FF2D1A"));
        colorHashMap.put(ShapeType.CORNER_SMALL, Color.parseColor("#01912A"));
        colorHashMap.put(ShapeType.CORNER_BIG, Color.parseColor("#2A8CF5"));
        backgroundColor = Color.WHITE;
        emptySquareColor = Color.parseColor("#BEB8B8");
        scoreColor = R.color.Orange;

    }

    public Theme(HashMap<ShapeType, Integer> colorHashMap, int backgroundColor, int secondaryColor) {
        this.colorHashMap = colorHashMap;
        this.backgroundColor = backgroundColor;
        this.secondaryColor = secondaryColor;
    }

    public HashMap<ShapeType, Integer> getColorHashMap() {
        return colorHashMap;
    }

    public void setColorHashMap(HashMap<ShapeType, Integer> colorHashMap) {
        this.colorHashMap = colorHashMap;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getEmptySquareColor() {
        return emptySquareColor;
    }

    public int getScoreColor() {
        return scoreColor;
    }
}
