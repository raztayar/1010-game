package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class LineOfThree_Vertical extends Shape {

    public LineOfThree_Vertical(){

        shapeMatrix = new ShapeType[3][1];

        shapeMatrix[0][0] = ShapeType.LINE_OF_THREE;
        shapeMatrix[1][0] = ShapeType.LINE_OF_THREE;
        shapeMatrix[2][0] = ShapeType.LINE_OF_THREE;
    }
}
