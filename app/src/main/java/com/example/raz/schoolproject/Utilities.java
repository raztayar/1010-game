package com.example.raz.schoolproject;

import android.content.Context;

public class Utilities {

    public static int dpToPixels(Context context, float dp){
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }
}
