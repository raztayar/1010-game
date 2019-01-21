package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class Square_Small extends Shape {

    public Square_Small(){

        shapeType = ShapeType.SQUARE_SMALL;

        shapeMatrix = new boolean[1][1];

        shapeMatrix[0][0] = true;
    }
}
