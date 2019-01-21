package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class Square_Big extends Shape {

    public Square_Big(){

        shapeType = ShapeType.SQUARE_BIG;

        shapeMatrix = new boolean[3][3];

        shapeMatrix[0][1] = true;
        shapeMatrix[0][0] = true;
        shapeMatrix[0][2] = true;
        shapeMatrix[1][0] = true;
        shapeMatrix[1][1] = true;
        shapeMatrix[1][2] = true;
        shapeMatrix[2][2] = true;
        shapeMatrix[2][0] = true;
        shapeMatrix[2][1] = true;
    }
}
