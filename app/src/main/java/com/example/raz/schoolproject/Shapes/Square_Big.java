package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class Square_Big extends Shape {

    public Square_Big(){

        shapeMatrix = new ShapeType[3][3];

        shapeMatrix[0][1] = ShapeType.SQUARE_BIG;
        shapeMatrix[0][0] = ShapeType.SQUARE_BIG;
        shapeMatrix[0][2] = ShapeType.SQUARE_BIG;
        shapeMatrix[1][0] = ShapeType.SQUARE_BIG;
        shapeMatrix[1][1] = ShapeType.SQUARE_BIG;
        shapeMatrix[1][2] = ShapeType.SQUARE_BIG;
        shapeMatrix[2][2] = ShapeType.SQUARE_BIG;
        shapeMatrix[2][0] = ShapeType.SQUARE_BIG;
        shapeMatrix[2][1] = ShapeType.SQUARE_BIG;
    }
}
