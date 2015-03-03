package com.practiceapps.donal.rottentomato.logging;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by donal on 24/02/2015.
 */
public class L {
    public static void mV (Context context, String message){
        Log.v(context.getClass().getName(), message);

    }

    public static void tL (Context context, String toast){
        Toast.makeText(context, toast, Toast.LENGTH_LONG).show();
    }

    public static void tS (Context context, String toast){
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }
}
