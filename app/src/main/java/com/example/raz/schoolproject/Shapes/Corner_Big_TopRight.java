package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class Corner_Big_TopRight extends Shape {

    public Corner_Big_TopRight(){

        shapeMatrix = new ShapeType[3][3];

        shapeMatrix[0][0] = ShapeType.CORNER_BIG;
        shapeMatrix[0][1] = ShapeType.CORNER_BIG;
        shapeMatrix[0][2] = ShapeType.CORNER_BIG;
        shapeMatrix[1][2] = ShapeType.CORNER_BIG;
        shapeMatrix[2][2] = ShapeType.CORNER_BIG;
    }

    static {
        Shape.registerShapeClass(Corner_Big_TopRight.class);
    }
}
