package imdh.tfm.proceduralwallpapers.wallpapers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import imdh.tfm.proceduralwallpapers.Constants;
import imdh.tfm.proceduralwallpapers.PaletteDatabase;
import imdh.tfm.proceduralwallpapers.models.Palette;

import static imdh.tfm.proceduralwallpapers.utils.UtilsWallpaper.randomBetween;

/**
 * Created by CarlosAB on 21/08/2017.
 */

public class RandomWallpaper extends GenericWallpaper {

    private Context context;
    private List<String> availabes;

    public RandomWallpaper(){
        super();
    }

    public RandomWallpaper(Palette palette, Context context){
        super();
        this.context = context;
        fillAvailables();
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
        int random = randomBetween(0,availabes.size());
        switch (availabes.get(random)){
            case Constants.W_ARCS:
                setBitmap(new ArcsWallpaper(getPalette()).getBitmap());
                break;
            case Constants.W_LINES:
                setBitmap(new LinesWallpaper(getPalette()).getBitmap());
                break;
            case Constants.W_PIXELIZATED:
                setBitmap(new PixelizatedWallpaper(getPalette(), Constants.DEFAULT_SQUARE_SIZE).getBitmap());
                break;
            case Constants.W_SQUARE_INCEPTION:
                setBitmap(new SquareInceptionWallpaper(getPalette()).getBitmap());
                break;
            case Constants.W_SQUARE_INCEPTION_2:
                setBitmap(new SquareInceptionWallpaper2(getPalette()).getBitmap());
                break;
            case Constants.W_ARCS_2:
                setBitmap(new ArcsWallpaper2(getPalette()).getBitmap());
                break;
            default:
                System.out.println("Random wallpaper case out of bounds");
                break;
        }
    }

    private void fillAvailables(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        availabes = new ArrayList<String>();
        for(String wallpaperName : Constants.WALLPAPERS_NAMES){
            if(sharedPreferences.getBoolean(wallpaperName, false)){
                availabes.add(wallpaperName);
            }
        }
    }
}
