package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class LineOfFive_Vertical extends Shape {

    public LineOfFive_Vertical(){

        shapeMatrix = new ShapeType[5][1];

        shapeMatrix[0][0] = ShapeType.LINE_OF_FIVE;
        shapeMatrix[1][0] = ShapeType.LINE_OF_FIVE;
        shapeMatrix[2][0] = ShapeType.LINE_OF_FIVE;
        shapeMatrix[3][0] = ShapeType.LINE_OF_FIVE;
        shapeMatrix[4][0] = ShapeType.LINE_OF_FIVE;
    }
}
