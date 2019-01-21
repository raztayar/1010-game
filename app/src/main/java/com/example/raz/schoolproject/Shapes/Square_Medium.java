package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class Square_Medium extends Shape {

    public Square_Medium(){

        shapeType = ShapeType.SQUARE_MEDIUM;

        shapeMatrix = new boolean[2][2];

        shapeMatrix[0][0] = true;
        shapeMatrix[0][1] = true;
        shapeMatrix[1][0] = true;
        shapeMatrix[1][1] = true;
    }
}
