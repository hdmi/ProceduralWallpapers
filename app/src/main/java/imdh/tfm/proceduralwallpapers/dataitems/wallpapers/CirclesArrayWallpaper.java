package imdh.tfm.proceduralwallpapers.dataitems.wallpapers;

import android.graphics.Paint;

import imdh.tfm.proceduralwallpapers.dataitems.Palette;
import imdh.tfm.proceduralwallpapers.utils.UtilsWallpaper;

import static imdh.tfm.proceduralwallpapers.Constants.DEFAULT_W_CICLES_NUM_OF_CIRCLES;

/**
 * Created by CarlosAB on 12/08/2017.
 */

public class CirclesArrayWallpaper extends GenericWallpaper {

    Palette palette;
    public CirclesArrayWallpaper(){
        super();
        this.palette = new Palette();
        draw();
    }

    public CirclesArrayWallpaper(Palette newPalette){
        super();
        this.palette = newPalette != null ? newPalette : new Palette();
        draw();
    }

    private void draw(){
        Paint mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        fillWithColor(palette.randomColor());

        final int width = canvas.getWidth();
        final int height =  canvas.getHeight();
        final int diameter;

        if(UtilsWallpaper.randomBetween(0,2)%2 == 0){
            diameter = (width*2)/DEFAULT_W_CICLES_NUM_OF_CIRCLES;
        }
        else{
            diameter = (height*2)/DEFAULT_W_CICLES_NUM_OF_CIRCLES;
        }

        int[] rotations = {0,45};
        int currentRotation = rotations[UtilsWallpaper.randomBetween(0,rotations.length)];
        canvas.rotate(currentRotation, width/2, height/2);
        for (int x = diameter*-2; x < height+diameter; x+=diameter){
            for (int y = diameter*-2; y < height+diameter; y+=diameter){
                mPaint.setColor(palette.randomColor());
                canvas.drawCircle(x, y, diameter/2, mPaint);
            }

        }
    }
}
