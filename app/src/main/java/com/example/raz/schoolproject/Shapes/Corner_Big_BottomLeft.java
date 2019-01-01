package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class Corner_Big_BottomLeft extends Shape {

    public Corner_Big_BottomLeft(){

        shapeMatrix = new ShapeType[3][3];

        shapeMatrix[0][0] = ShapeType.CORNER_BIG;
        shapeMatrix[0][1] = ShapeType.CORNER_BIG;
        shapeMatrix[0][2] = ShapeType.CORNER_BIG;
        shapeMatrix[1][2] = ShapeType.CORNER_BIG;
        shapeMatrix[2][2] = ShapeType.CORNER_BIG;
    }
}
