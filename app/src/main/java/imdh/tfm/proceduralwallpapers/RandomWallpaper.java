package imdh.tfm.proceduralwallpapers;

import android.content.Context;

import static imdh.tfm.proceduralwallpapers.UtilsWallpaper.randomBetween;

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
        switch (randomBetween(0, 4)){
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
            default:
                System.out.println("Random wallpaper case out of bounds");
                break;
        }
    }
}
