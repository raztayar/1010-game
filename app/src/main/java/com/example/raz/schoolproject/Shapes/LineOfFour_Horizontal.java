package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class LineOfFour_Horizontal extends Shape {

    public LineOfFour_Horizontal(){

        shapeMatrix = new ShapeType[1][4];

        shapeMatrix[0][0] = ShapeType.LINE_OF_FOUR;
        shapeMatrix[0][1] = ShapeType.LINE_OF_FOUR;
        shapeMatrix[0][2] = ShapeType.LINE_OF_FOUR;
        shapeMatrix[0][3] = ShapeType.LINE_OF_FOUR;
    }

    static {
        Shape.registerShapeClass(LineOfFour_Horizontal.class);
    }
}
