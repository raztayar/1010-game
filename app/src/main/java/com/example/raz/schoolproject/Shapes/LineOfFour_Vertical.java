package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class LineOfFour_Vertical extends Shape {

    public LineOfFour_Vertical(){

        shapeType = ShapeType.LINE_OF_FOUR;

        shapeMatrix = new boolean[4][1];

        shapeMatrix[0][0] = true;
        shapeMatrix[1][0] = true;
        shapeMatrix[2][0] = true;
        shapeMatrix[3][0] = true;
    }
}
