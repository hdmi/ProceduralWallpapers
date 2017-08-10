package imdh.tfm.proceduralwallpapers;

import java.util.Random;

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

    public Palette getExamplePalette() {
        return examplePalette;
    }
}
