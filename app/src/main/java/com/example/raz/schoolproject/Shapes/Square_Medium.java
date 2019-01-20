package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class Square_Medium extends Shape {

    public Square_Medium(){

        shapeMatrix = new ShapeType[2][2];

        shapeMatrix[0][0] = ShapeType.SQUARE_MEDIUM;
        shapeMatrix[0][1] = ShapeType.SQUARE_MEDIUM;
        shapeMatrix[1][0] = ShapeType.SQUARE_MEDIUM;
        shapeMatrix[1][1] = ShapeType.SQUARE_MEDIUM;
    }
}
