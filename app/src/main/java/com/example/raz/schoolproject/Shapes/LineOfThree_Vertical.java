package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Objects.Shape;
import com.example.raz.schoolproject.ShapeType;

public class LineOfThree_Vertical extends Shape {

    public LineOfThree_Vertical(){

        shapeType = ShapeType.LINE_OF_THREE;

        shapeMatrix = new boolean[3][1];

        shapeMatrix[0][0] = true;
        shapeMatrix[1][0] = true;
        shapeMatrix[2][0] = true;
    }

    @Override
    public int getShapeScore() {
        return 3;
    }
}
