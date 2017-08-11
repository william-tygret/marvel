package com.williamtygret.marvelminviable;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

/**
 * Created by williamtygret on 8/10/17.
 */
public class ReplaceFont {

    public static void replaceDefaultFont(Context context, String nameOfFontBeingReplaced, String nameOfFontInAssets){
        Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(), nameOfFontInAssets);
        replaceFont(nameOfFontBeingReplaced,customFontTypeface);
    }

    private static void replaceFont(String nameOfFontBeingReplaced, Typeface customFontTypeface) {
        try {
            Field myField = Typeface.class.getDeclaredField(nameOfFontBeingReplaced);
            myField.setAccessible(true);
            myField.set(null, customFontTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
