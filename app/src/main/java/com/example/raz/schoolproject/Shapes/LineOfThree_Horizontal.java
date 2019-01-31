package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class LineOfThree_Horizontal extends Shape {

    public LineOfThree_Horizontal(){

        shapeType = ShapeType.LINE_OF_THREE;

        shapeMatrix = new boolean[1][3];

        shapeMatrix[0][0] = true;
        shapeMatrix[0][1] = true;
        shapeMatrix[0][2] = true;
    }

    @Override
    public int getShapeScore() {
        return 3;
    }
}
