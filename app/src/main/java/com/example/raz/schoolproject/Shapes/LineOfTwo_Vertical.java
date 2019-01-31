package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class LineOfTwo_Vertical extends Shape {

    public LineOfTwo_Vertical(){

        shapeType = ShapeType.LINE_OF_TWO;

        shapeMatrix = new boolean[2][1];

        shapeMatrix[0][0] = true;
        shapeMatrix[1][0] = true;
    }

    @Override
    public int getShapeScore() {
        return 2;
    }
}
