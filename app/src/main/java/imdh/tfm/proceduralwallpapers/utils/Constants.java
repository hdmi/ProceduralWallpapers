package imdh.tfm.proceduralwallpapers.utils;

import imdh.tfm.proceduralwallpapers.R;

public class Constants {

    public static final int DEFAULT_WIDTH = 1080;
    public static final int DEFAULT_HEIGHT = 1920;

    public static final int DEFAULT_LINE_THICKNESS = 3;

    public static final int PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    public static final int PALETTES_SAMPLE_100 = R.raw.cien;
    public static final int PALETTES_SAMPLE_200 = R.raw.doscientas;
    public static final int PALETTES_SAMPLE_500 = R.raw.quinientas;
    public static final int PALETTES_SAMPLE_1000 = R.raw.mil;

    //PixelatedWallpaper
    public static final int DEFAULT_SQUARE_SIZE = 200;

    //CircleArrayWallpaper
    public static final int DEFAULT_W_CICLES_NUM_OF_CIRCLES = 15;

    //ErrorWallpaper
    public static final int DEFAULT_TEXT_SIZE = 50;

    public static final int ANIMATION_DURATION_1_SEC = 1000;

    //Jobs
    public static final long MIN_PERIODIC_INTERVAL = 900_000L;
    public static final long PERIODIC_INTERVAL_THRESHOLD = 600000;

    //Wallpapers
    public static final String W_ARCS = "ArcsWallpaper";
    public static final String W_ARCS_2= "ArcsWallpaper2";
    public static final String W_LINES = "LinesWallpaper";
    public static final String W_PIXELIZATED = "PixelizatedWallpaper";
    public static final String W_SQUARE_INCEPTION = "SquareInceptionWallpaper";
    public static final String W_SQUARE_INCEPTION_2 = "SquareInceptionWallpaper2";
    public static final String W_CIRCLES_ARRAY = "CirclesArrayWallpaper";
    public static final String W_ARROW_HEAD = "ArrowHeadWallpaper";
    public static final String W_FIVE_FLAG = "FiveLinesFlagWallpaper";

    public static final String[] WALLPAPERS_NAMES = {
            W_ARCS,
            W_ARCS_2,
            W_LINES,
            W_PIXELIZATED,
            W_SQUARE_INCEPTION,
            W_SQUARE_INCEPTION_2,
            W_CIRCLES_ARRAY,
            W_ARROW_HEAD,
            W_FIVE_FLAG
    };

    public static final String ENABLED_WALLPAPERS_PREFERENCE_COUNTER_NAME = "numOfEnabledWallpapers";
    public static final int ERROR_INT_PREFERENCES = -100;

    public static final int DOWNSCALE_RATIO = 4;

}
