package imdh.tfm.proceduralwallpapers.utils;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;

import java.io.IOException;
import java.util.Random;

import imdh.tfm.proceduralwallpapers.models.Palette;

/**
 * Created by CarlosAB on 06/08/2017.
 */

public class UtilsWallpaper {

    private static Random random;
    private static Palette examplePalette;


    private static UtilsWallpaper singleton = new UtilsWallpaper();
    private UtilsWallpaper() {
        random = new Random();
        initExamplePalettes();
    }

    public static int randomBetween(int min, int max){
        return random.nextInt(max-min) + min;
    }

    public static UtilsWallpaper getInstance( ) {
        return singleton;
    }

    private void initExamplePalettes() {
        examplePalette = new Palette(0xFFE63946, 0xFFF1FAEE, 0xFFA8DADC, 0xFF457B9D, 0xFF1D3557);
    }

    public static Palette getExamplePalette() {
        return examplePalette;
    }

    public static void setWallpaper2Desktop(Bitmap bitmap, Context context){
        WallpaperManager myWallpaperManager = WallpaperManager.getInstance(context);
        try {
            myWallpaperManager.setBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object returnRandom(Object[] array){
        if (array.length < 0) return null;
        return array[randomBetween(0, array.length)];
    }

}
