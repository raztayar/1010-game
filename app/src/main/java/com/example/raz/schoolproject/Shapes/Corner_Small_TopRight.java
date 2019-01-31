package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class Corner_Small_TopRight extends Shape {

    public Corner_Small_TopRight(){

        shapeType = ShapeType.CORNER_SMALL;

        shapeMatrix = new boolean[2][2];

        shapeMatrix[0][0] = true;
        shapeMatrix[0][1] = true;
        shapeMatrix[1][1] = true;
    }

    @Override
    public int getShapeScore() {
        return 3;
    }
}
