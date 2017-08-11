package com.android.cuentacuentosinteractivo.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.android.cuentacuentosinteractivo.R;

/**
 * Created by cgj on 11/08/2017.
 */

public class UtilsCustom {

    public static Drawable getDrawable(int id, Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            return context.getResources().getDrawable(id, null);
        }
        else {

            return context.getResources().getDrawable(id);
        }
    }
}
