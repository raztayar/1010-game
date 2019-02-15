package com.example.raz.schoolproject.Objects;

import android.content.Context;
import android.graphics.Point;
import android.widget.TableLayout;

import com.example.raz.schoolproject.Objects.Theme;
import com.example.raz.schoolproject.ShapeType;

public interface IShape {

    boolean isPlaceable(Point point, ShapeType[][] board);

    boolean isPlaceableSomewhere(ShapeType[][] board);

    void placeShape(Point point, ShapeType[][] board);

    TableLayout createShapeAsTableLayout(Context context, Theme theme);

    Point getMidPoint();

    int getShapeScore();
}
