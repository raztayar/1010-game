package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class LineOfTwo_Horizontal extends Shape {

    public LineOfTwo_Horizontal(){

        shapeMatrix = new ShapeType[1][2];

        shapeMatrix[0][0] = ShapeType.LINE_OF_TWO;
        shapeMatrix[0][1] = ShapeType.LINE_OF_TWO;
    }
}
