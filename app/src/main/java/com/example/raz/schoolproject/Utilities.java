package com.example.raz.schoolproject;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.raz.schoolproject.Objects.GameStats;
import com.example.raz.schoolproject.Objects.IShape;
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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public static String md5(String input) {
        String md5 = null;

        if(input == null) return null;

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");

            digest.update(input.getBytes(), 0, input.length());

            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }
}
