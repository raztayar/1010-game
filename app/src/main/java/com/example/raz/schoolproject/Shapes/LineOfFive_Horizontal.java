package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class LineOfFive_Horizontal extends Shape {

    public LineOfFive_Horizontal(){

        shapeMatrix = new ShapeType[1][5];

        shapeMatrix[0][0] = ShapeType.LINE_OF_FIVE;
        shapeMatrix[0][1] = ShapeType.LINE_OF_FIVE;
        shapeMatrix[0][2] = ShapeType.LINE_OF_FIVE;
        shapeMatrix[0][3] = ShapeType.LINE_OF_FIVE;
        shapeMatrix[0][4] = ShapeType.LINE_OF_FIVE;
    }
}
