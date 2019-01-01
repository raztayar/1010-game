package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class LineOfFour_Vertical extends Shape {

    public LineOfFour_Vertical(){

        shapeMatrix = new ShapeType[1][4];

        shapeMatrix[0][0] = ShapeType.LINE_OF_FOUR;
        shapeMatrix[0][1] = ShapeType.LINE_OF_FOUR;
        shapeMatrix[0][2] = ShapeType.LINE_OF_FOUR;
        shapeMatrix[0][3] = ShapeType.LINE_OF_FOUR;
    }
}
