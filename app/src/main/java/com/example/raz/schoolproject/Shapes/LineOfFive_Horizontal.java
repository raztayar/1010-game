package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class LineOfFive_Horizontal extends Shape {

    public LineOfFive_Horizontal(){

        shapeType = ShapeType.LINE_OF_FIVE;

        shapeMatrix = new boolean[1][5];

        shapeMatrix[0][0] = true;
        shapeMatrix[0][1] = true;
        shapeMatrix[0][2] = true;
        shapeMatrix[0][3] = true;
        shapeMatrix[0][4] = true;
    }

    @Override
    public int getShapeScore() {
        return 5;
    }
}
