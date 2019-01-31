package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class LineOfTwo_Horizontal extends Shape {

    public LineOfTwo_Horizontal(){

        shapeType = ShapeType.LINE_OF_TWO;

        shapeMatrix = new boolean[1][2];

        shapeMatrix[0][0] = true;
        shapeMatrix[0][1] = true;
    }

    @Override
    public int getShapeScore() {
        return 2;
    }
}
