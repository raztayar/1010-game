package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class Corner_Big_BottomRight extends Shape {

    public Corner_Big_BottomRight(){

        shapeType = ShapeType.CORNER_BIG;

        shapeMatrix = new boolean[3][3];

        shapeMatrix[0][2] = true;
        shapeMatrix[1][2] = true;
        shapeMatrix[2][0] = true;
        shapeMatrix[2][1] = true;
        shapeMatrix[2][2] = true;
    }
}
