package com.example.raz.schoolproject;

import android.content.Context;
import android.graphics.Point;
import android.widget.LinearLayout;
import android.widget.TableLayout;

public interface IShape {

    boolean isPlaceable(Point point, ShapeType[][] board);

    boolean isPlaceableSomewhere(ShapeType[][] board);

    void placeShape(Point point, ShapeType[][] board);

    TableLayout createShapeAsTableLayout(Context context, Theme theme);

    Point getMidPoint();
}
