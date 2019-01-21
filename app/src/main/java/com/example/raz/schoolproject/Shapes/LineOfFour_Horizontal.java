package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class LineOfFour_Horizontal extends Shape {

    public LineOfFour_Horizontal(){

        shapeType = ShapeType.LINE_OF_FOUR;

        shapeMatrix = new boolean[1][4];

        shapeMatrix[0][0] = true;
        shapeMatrix[0][1] = true;
        shapeMatrix[0][2] = true;
        shapeMatrix[0][3] = true;
    }
}
