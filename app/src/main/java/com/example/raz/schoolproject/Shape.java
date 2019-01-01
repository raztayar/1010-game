package com.example.raz.schoolproject;


import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public abstract class Shape implements IShape {

    protected ShapeType[][] shapeMatrix;

    public ShapeType[][] getShapeMatrix(){
        return shapeMatrix;
    }

    @Override
    public boolean isPlaceable(Point point, ShapeType[][] board) {
        if (point.getX() > board.length - shapeMatrix.length) return false;
        else if (point.getY() > board[0].length - shapeMatrix[0].length) return false;

        for (int i=0; i < shapeMatrix.length; i++){
            for (int j=0; j < shapeMatrix[i].length; j++){
                if (shapeMatrix[i][j] != null){
                    if(board[point.getX() + i][point.getY() + j] != null){
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
                if (!this.isPlaceable(new Point(i,j), board)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void placeShape(Point point, ShapeType[][] board) {
        if(this.isPlaceable(point, board)){
            for (int i=0; i < shapeMatrix.length; i++){
                for (int j=0; j < shapeMatrix[i].length; j++){
                    if (shapeMatrix[i][j] != null){
                        board[point.getX() + i][point.getY() + j] = shapeMatrix[i][j];
                    }
                }
            }
        }
        else{
            throw new RuntimeException("Invalid point");
        }
    }

    @Override
    public TableLayout createShapeAsTableLayout(Context context) {
        TableLayout table = new TableLayout(context);
        table.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        for(int i = 0; i < shapeMatrix.length; i++){
            TableRow row = new TableRow(context);
            table.addView(row);
            for(int j = 0; j < shapeMatrix[i].length; j++){
                TextView textView = new TextView(context);
                row.addView(textView, Utilities.dpToPixels(context, 32), Utilities.dpToPixels(context, 32));
                if(shapeMatrix[i][j] != null){
                    textView.setBackgroundColor(Color.BLACK);
                }
            }
        }

        return table;
    }
}
