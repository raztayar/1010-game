package com.example.raz.schoolproject.Objects;


import android.content.Context;
import android.graphics.Point;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.raz.schoolproject.ShapeType;
import com.example.raz.schoolproject.Shapes.Corner_Big_BottomLeft;
import com.example.raz.schoolproject.Shapes.Corner_Big_BottomRight;
import com.example.raz.schoolproject.Shapes.Corner_Big_TopLeft;
import com.example.raz.schoolproject.Shapes.Corner_Big_TopRight;
import com.example.raz.schoolproject.Shapes.Corner_Small_BottomLeft;
import com.example.raz.schoolproject.Shapes.Corner_Small_BottomRight;
import com.example.raz.schoolproject.Shapes.Corner_Small_TopLeft;
import com.example.raz.schoolproject.Shapes.Corner_Small_TopRight;
import com.example.raz.schoolproject.Shapes.LineOfFive_Horizontal;
import com.example.raz.schoolproject.Shapes.LineOfFive_Vertical;
import com.example.raz.schoolproject.Shapes.LineOfFour_Horizontal;
import com.example.raz.schoolproject.Shapes.LineOfFour_Vertical;
import com.example.raz.schoolproject.Shapes.LineOfThree_Horizontal;
import com.example.raz.schoolproject.Shapes.LineOfThree_Vertical;
import com.example.raz.schoolproject.Shapes.LineOfTwo_Horizontal;
import com.example.raz.schoolproject.Shapes.LineOfTwo_Vertical;
import com.example.raz.schoolproject.Shapes.Square_Big;
import com.example.raz.schoolproject.Shapes.Square_Medium;
import com.example.raz.schoolproject.Shapes.Square_Small;
import com.example.raz.schoolproject.Utilities;

import java.util.Random;

public abstract class Shape implements IShape {

    protected ShapeType shapeType;

    private int raisedTouchpointY = 0;

    protected boolean[][] shapeMatrix;

    public boolean[][] getShapeMatrix(){
        return shapeMatrix;
    }

    @Override
    public boolean isPlaceable(Point point, ShapeType[][] board) {

        if (point.x < 0 || point.x > board.length - shapeMatrix.length) return false;
        else if (point.y < 0 || point.y > board[0].length - shapeMatrix[0].length) return false;

        for (int i=0; i < shapeMatrix.length; i++){
            for (int j=0; j < shapeMatrix[i].length; j++){
                if (shapeMatrix[i][j]){
                    if(board[point.x + i][point.y + j] != null){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean isPlaceableSomewhere(ShapeType[][] board) {
        for (int i=0; i < board.length; i++){
            for (int j=0; j < board[i].length; j++){
                if (this.isPlaceable(new Point(i,j), board)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void placeShape(Point point, ShapeType[][] board) {
        if(this.isPlaceable(point, board)){
            for (int i=0; i < shapeMatrix.length; i++){
                for (int j=0; j < shapeMatrix[i].length; j++){
                    if (shapeMatrix[i][j]){
                        board[point.x + i][point.y + j] = shapeType;
                    }
                }
            }
        }
        else{
            throw new RuntimeException("Invalid point");
        }
    }

    @Override
    public TableLayout createShapeAsTableLayout(Context context, Theme theme) {
        TableLayout table = new TableLayout(context);
        table.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        for(int i = 0; i < shapeMatrix.length; i++){
            TableRow row = new TableRow(context);
            table.addView(row);
            for(int j = 0; j < shapeMatrix[i].length; j++){
                TextView textView = new TextView(context);
                TableRow.LayoutParams params = new TableRow.LayoutParams(Utilities.dpToPixels(context, 32), Utilities.dpToPixels(context, 32));
                params.setMargins(Utilities.dpToPixels(context, 2), Utilities.dpToPixels(context, 2), Utilities.dpToPixels(context, 2), Utilities.dpToPixels(context, 2));
                textView.setLayoutParams(params);
                row.addView(textView);
                if(shapeMatrix[i][j]){
                    textView.setBackgroundColor(theme.getColorHashMap().get(shapeType));
                }
            }
        }

        return table;
    }

    @Override
    public Point getMidPoint() {
        return new Point((shapeMatrix.length - 1) / 2, (shapeMatrix[0].length - 1) / 2);
    }

    @Override
    public int getShapeScore() {
        int score = 0;
        for(int i = 0; i < shapeMatrix.length; i++) {
            for (int j = 0; j < shapeMatrix[i].length; j++) {
                if (shapeMatrix[i][j]) {
                    score++;
                }
            }
        }
        return score;
    }

    public static IShape createRandomShape() {
        Random rnd = new Random();

        switch (rnd.nextInt(ShapeType.values().length)) {
            case 0: return new Square_Small();
            case 1: return new Square_Medium();
            case 2: return new Square_Big();
            case 3: {
                switch (rnd.nextInt(2)) {
                    case 0: return new LineOfTwo_Horizontal();
                    case 1: return new LineOfTwo_Vertical();
                }
            }
            case 4: {
                switch (rnd.nextInt(2)) {
                    case 0: return new LineOfThree_Horizontal();
                    case 1: return new LineOfThree_Vertical();
                }
            }
            case 5: {
                switch (rnd.nextInt(2)) {
                    case 0: return new LineOfFour_Horizontal();
                    case 1: return new LineOfFour_Vertical();
                }
            }
            case 6: {
                switch (rnd.nextInt(2)) {
                    case 0: return new LineOfFive_Horizontal();
                    case 1: return new LineOfFive_Vertical();
                }
            }
            case 7: {
                switch (rnd.nextInt(4)) {
                    case 0: return new Corner_Small_TopLeft();
                    case 1: return new Corner_Small_TopRight();
                    case 2: return new Corner_Small_BottomLeft();
                    case 3: return new Corner_Small_BottomRight();
                }
            }
            case 8: {
                switch (rnd.nextInt(4)) {
                    case 0: return new Corner_Big_TopLeft();
                    case 1: return new Corner_Big_TopRight();
                    case 2: return new Corner_Big_BottomLeft();
                    case 3: return new Corner_Big_BottomRight();
                }
            }
        }

        throw new RuntimeException("Randomizer failed");
    }
}
