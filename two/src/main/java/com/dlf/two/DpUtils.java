package com.dlf.two;

import android.content.Context;

public class DpUtils {

    public static float dpToPx(Context context,int dp){
        float density = context.getResources().getDisplayMetrics().density;
        return density*dp+0.5f;
    }

}
