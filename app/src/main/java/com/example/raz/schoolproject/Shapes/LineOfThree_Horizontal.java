package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class LineOfThree_Horizontal extends Shape {

    public LineOfThree_Horizontal(){

        shapeMatrix = new ShapeType[1][3];

        shapeMatrix[0][0] = ShapeType.LINE_OF_THREE;
        shapeMatrix[0][1] = ShapeType.LINE_OF_THREE;
        shapeMatrix[0][2] = ShapeType.LINE_OF_THREE;
    }

    static {
        Shape.registerShapeClass(LineOfThree_Horizontal.class);
    }
}
