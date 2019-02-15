package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Objects.Shape;
import com.example.raz.schoolproject.ShapeType;

public class Corner_Big_TopRight extends Shape {

    public Corner_Big_TopRight(){

        shapeType = ShapeType.CORNER_BIG;

        shapeMatrix = new boolean[3][3];

        shapeMatrix[0][0] = true;
        shapeMatrix[0][1] = true;
        shapeMatrix[0][2] = true;
        shapeMatrix[1][2] = true;
        shapeMatrix[2][2] = true;
    }

    @Override
    public int getShapeScore() {
        return 5;
    }
}
