package pro.vylgin.yandexmapkittexttask.helper;

import android.content.Context;
import android.graphics.Typeface;

public class TypefaceHelper {

    public static Typeface getRobotoRegular(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "RobotoTTF/Roboto-Regular.ttf");
    }

    public static Typeface getRobotoMedium(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "RobotoTTF/Roboto-Medium.ttf");
    }

}
