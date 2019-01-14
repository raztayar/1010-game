package com.example.raz.schoolproject;

import android.content.Context;

import com.example.raz.schoolproject.Shapes.Corner_Big_BottomLeft;
import com.example.raz.schoolproject.Shapes.Corner_Big_BottomRight;
import com.example.raz.schoolproject.Shapes.Corner_Big_TopLeft;
import com.example.raz.schoolproject.Shapes.Corner_Big_TopRight;
import com.example.raz.schoolproject.Shapes.Corner_Small_BottomLeft;
import com.example.raz.schoolproject.Shapes.Corner_Small_BottomRight;
import com.example.raz.schoolproject.Shapes.Corner_Small_TopLeft;
import com.example.raz.schoolproject.Shapes.Corner_Small_TopRight;
import com.example.raz.schoolproject.Shapes.LineOfFive_Horizontal;
import com.example.raz.schoolproject.Shapes.LineOfFive_Vertical;
import com.example.raz.schoolproject.Shapes.LineOfFour_Horizontal;
import com.example.raz.schoolproject.Shapes.LineOfFour_Vertical;
import com.example.raz.schoolproject.Shapes.LineOfThree_Horizontal;
import com.example.raz.schoolproject.Shapes.LineOfThree_Vertical;
import com.example.raz.schoolproject.Shapes.LineOfTwo_Horizontal;
import com.example.raz.schoolproject.Shapes.LineOfTwo_Vertical;
import com.example.raz.schoolproject.Shapes.Square_Big;
import com.example.raz.schoolproject.Shapes.Square_Medium;
import com.example.raz.schoolproject.Shapes.Square_Small;

import java.util.Random;

public class Utilities {

    static int dpToPixels(Context context, float dp){
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }

    static IShape createRandomShape() {
        Random rand = new Random();
        int value = rand.nextInt(19);

        switch (value) {
            case 0: return new Corner_Big_BottomLeft();
            case 1: return new Corner_Big_BottomRight();
            case 2: return new Corner_Big_TopLeft();
            case 3: return new Corner_Big_TopRight();
            case 4: return new Corner_Small_BottomLeft();
            case 5: return new Corner_Small_BottomRight();
            case 6: return new Corner_Small_TopLeft();
            case 7: return new Corner_Small_TopRight();
            case 8: return new LineOfFive_Horizontal();
            case 9: return new LineOfFive_Vertical();
            case 10: return new LineOfFour_Horizontal();
            case 11: return new LineOfFour_Vertical();
            case 12: return new LineOfThree_Horizontal();
            case 13: return new LineOfThree_Vertical();
            case 14: return new LineOfTwo_Horizontal();
            case 15: return new LineOfTwo_Vertical();
            case 16: return new Square_Big();
            case 17: return new Square_Medium();
            case 18: return new Square_Small();
        }

        throw new RuntimeException("Randomizer failed");
    }
}
