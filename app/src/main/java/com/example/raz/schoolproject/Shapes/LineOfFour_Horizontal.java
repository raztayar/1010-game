package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class LineOfFour_Horizontal extends Shape {

    public LineOfFour_Horizontal(){

        shapeMatrix = new ShapeType[4][1];

        shapeMatrix[0][0] = ShapeType.LINE_OF_FOUR;
        shapeMatrix[1][0] = ShapeType.LINE_OF_FOUR;
        shapeMatrix[2][0] = ShapeType.LINE_OF_FOUR;
        shapeMatrix[3][0] = ShapeType.LINE_OF_FOUR;
    }
}
