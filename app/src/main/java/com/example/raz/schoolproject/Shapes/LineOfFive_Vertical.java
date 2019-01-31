package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class LineOfFive_Vertical extends Shape {

    public LineOfFive_Vertical(){

        shapeType = ShapeType.LINE_OF_FIVE;

        shapeMatrix = new boolean[5][1];

        shapeMatrix[0][0] = true;
        shapeMatrix[1][0] = true;
        shapeMatrix[2][0] = true;
        shapeMatrix[3][0] = true;
        shapeMatrix[4][0] = true;
    }

    @Override
    public int getShapeScore() {
        return 5;
    }
}
