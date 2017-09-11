package imdh.tfm.proceduralwallpapers.wallpapers;

import android.content.Context;

import imdh.tfm.proceduralwallpapers.Constants;
import imdh.tfm.proceduralwallpapers.models.Palette;
import imdh.tfm.proceduralwallpapers.PaletteDatabase;

import static imdh.tfm.proceduralwallpapers.utils.UtilsWallpaper.randomBetween;

/**
 * Created by CarlosAB on 21/08/2017.
 */

public class RandomWallpaper extends GenericWallpaper {

    private Context context;

    public RandomWallpaper(){
        super();
    }

    public RandomWallpaper(Palette palette, Context context){
        super();
        this.context = context;
        if(palette == null){setPalette(PaletteDatabase.getInstance(context).getRandomPalette());}
        else{setPalette(palette);}
        draw(palette);
    }

    public RandomWallpaper(boolean fixed){
        super();
        setPalette(PaletteDatabase.getInstance(context).getRandomPalette());
        setBitmap(new SquareInceptionWallpaper(getPalette()).getBitmap());
    }

    private void draw(Palette palette){
        int random = randomBetween(0,6);
        switch (random){
            case 0:
                setBitmap(new ArcsWallpaper(getPalette()).getBitmap());
                break;
            case 1:
                setBitmap(new LinesWallpaper(getPalette()).getBitmap());
                break;
            case 2:
                setBitmap(new PixelizatedWallpaper(getPalette(), Constants.DEFAULT_SQUARE_SIZE).getBitmap());
                break;
            case 3:
                setBitmap(new SquareInceptionWallpaper(getPalette()).getBitmap());
                break;
            case 4:
                setBitmap(new SquareInceptionWallpaper2(getPalette()).getBitmap());
                break;
            case 5:
                setBitmap(new ArcsWallpaper2(getPalette()).getBitmap());
                break;
            default:
                System.out.println("Random wallpaper case out of bounds");
                break;
        }
    }
}
