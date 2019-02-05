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

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class Utilities {

    public static int dpToPixels(Context context, float dp){
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }

    public static String millisToString(long millis) {
        long seconds = millis / 1000;
        long s = seconds % 60;
        long m = (seconds / 60) % 60;
        long h = (seconds / (60 * 60)) % 24;
        return String.format(Locale.getDefault(), "%dh:%02dm:%02ds", h,m,s);
    }

    public static IShape createRandomShape() {
        Random rnd = new Random();

        switch (rnd.nextInt(ShapeType.values().length)) {
            case 0: return new Square_Small();
            case 1: return new Square_Medium();
            case 2: return new Square_Big();
            case 3: {
                switch (rnd.nextInt(2)) {
                    case 0: return new LineOfTwo_Horizontal();
                    case 1: return new LineOfTwo_Vertical();
                }
            }
            case 4: {
                switch (rnd.nextInt(2)) {
                    case 0: return new LineOfThree_Horizontal();
                    case 1: return new LineOfThree_Vertical();
                }
            }
            case 5: {
                switch (rnd.nextInt(2)) {
                    case 0: return new LineOfFour_Horizontal();
                    case 1: return new LineOfFour_Vertical();
                }
            }
            case 6: {
                switch (rnd.nextInt(2)) {
                    case 0: return new LineOfFive_Horizontal();
                    case 1: return new LineOfFive_Vertical();
                }
            }
            case 7: {
                switch (rnd.nextInt(4)) {
                    case 0: return new Corner_Small_TopLeft();
                    case 1: return new Corner_Small_TopRight();
                    case 2: return new Corner_Small_BottomLeft();
                    case 3: return new Corner_Small_BottomRight();
                }
            }
            case 8: {
                switch (rnd.nextInt(4)) {
                    case 0: return new Corner_Big_TopLeft();
                    case 1: return new Corner_Big_TopRight();
                    case 2: return new Corner_Big_BottomLeft();
                    case 3: return new Corner_Big_BottomRight();
                }
            }
        }

        throw new RuntimeException("Randomizer failed");

    }

    public static long[] addToLongArray(long[] array, long value) {
        if (array[array.length - 1] != 0) {
            array = Arrays.copyOf(array, array.length * 2);
            array[array.length / 2] = value;
        }
        else {
            for(int i = 0; i < array.length; i++) {
                if (array[i] == 0) {
                    array[i] = value;
                    return array;
                }
            }
        }
        return array;
    }
}
