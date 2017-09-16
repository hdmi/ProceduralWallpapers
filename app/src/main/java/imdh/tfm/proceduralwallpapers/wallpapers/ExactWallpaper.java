package imdh.tfm.proceduralwallpapers.wallpapers;

import imdh.tfm.proceduralwallpapers.Constants;

import static imdh.tfm.proceduralwallpapers.Constants.W_ARCS;
import static imdh.tfm.proceduralwallpapers.Constants.W_ARCS_2;
import static imdh.tfm.proceduralwallpapers.Constants.W_LINES;
import static imdh.tfm.proceduralwallpapers.Constants.W_PIXELIZATED;
import static imdh.tfm.proceduralwallpapers.Constants.W_SQUARE_INCEPTION;
import static imdh.tfm.proceduralwallpapers.Constants.W_SQUARE_INCEPTION_2;

/**
 * Created by CarlosAB on 21/08/2017.
 */

public class ExactWallpaper extends GenericWallpaper {

    public ExactWallpaper(String wallpaperName){
        super();
        System.out.println("Generating exactly: "+wallpaperName);
        switch (wallpaperName){
            case W_ARCS:
                setBitmap(new ArcsWallpaper(getPalette()).getBitmap());
                break;
            case W_LINES:
                setBitmap(new LinesWallpaper(getPalette()).getBitmap());
                break;
            case W_PIXELIZATED:
                setBitmap(new PixelizatedWallpaper(getPalette(), Constants.DEFAULT_SQUARE_SIZE).getBitmap());
                break;
            case W_SQUARE_INCEPTION:
                setBitmap(new SquareInceptionWallpaper(getPalette()).getBitmap());
                break;
            case W_SQUARE_INCEPTION_2:
                setBitmap(new SquareInceptionWallpaper2(getPalette()).getBitmap());
                break;
            case W_ARCS_2:
                setBitmap(new ArcsWallpaper2(getPalette()).getBitmap());
                break;
            default:
                System.out.println("Random wallpaper case out of bounds");
                this.fillWithColor(0xfff);
                break;
        }
    }

}
