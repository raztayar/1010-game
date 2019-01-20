package com.example.raz.schoolproject.Shapes;

import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.ShapeType;

public class Square_Small extends Shape {

    public Square_Small(){

        shapeMatrix = new ShapeType[1][1];

        shapeMatrix[0][0] = ShapeType.SQUARE_SMALL;
    }
}
