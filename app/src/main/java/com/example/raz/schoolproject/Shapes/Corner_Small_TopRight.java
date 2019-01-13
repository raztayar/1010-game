package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class Corner_Small_TopRight extends Shape {

    public Corner_Small_TopRight(){

        shapeMatrix = new ShapeType[2][2];

        shapeMatrix[0][0] = ShapeType.CORNER_SMALL;
        shapeMatrix[0][1] = ShapeType.CORNER_SMALL;
        shapeMatrix[1][1] = ShapeType.CORNER_SMALL;
    }
}
