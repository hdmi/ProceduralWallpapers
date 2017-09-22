package imdh.tfm.proceduralwallpapers.dataitems.wallpapers;

import android.graphics.Color;
import android.graphics.Paint;

import imdh.tfm.proceduralwallpapers.Constants;
import imdh.tfm.proceduralwallpapers.dataitems.Palette;
import imdh.tfm.proceduralwallpapers.utils.UtilsWallpaper;

/**
 * Created by CarlosAB on 12/08/2017.
 */

public class ArcsWallpaper2 extends GenericWallpaper {

    Palette palette;
    Paint strokePaint = new Paint();

    public ArcsWallpaper2(){
        super();
        this.palette = new Palette();
        draw();
    }

    public ArcsWallpaper2(Palette newPalette){
        super();
        this.palette = newPalette != null ? newPalette : new Palette();
        draw();
    }

    private void draw(){
        Paint mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        strokePaint.setStrokeWidth(Constants.DEFAULT_LINE_THICKNESS);
        strokePaint.setColor(Color.BLACK);
        strokePaint.setStyle(Paint.Style.STROKE);

        fillWithColor(palette.getC4());

        final int width = canvas.getWidth();
        final int height =  canvas.getHeight();

        final int distance = (int)(width * .2);
        final int centerX;
        final int centerY = (int) (height/1.3);

        if(UtilsWallpaper.randomBetween(0,2)%2 == 0){
          centerX = width+distance;
        }
        else{
          centerX = -distance;
        }

        for (int i = 10; i > 0 ; i--) {
            mPaint.setColor(palette.getColorNumber(i%5));
            canvas.drawRoundRect(centerX-(distance*i), (int)(centerY*.9)-(distance*i), centerX+(distance*i), height*2, height, height, mPaint);
            //canvas.drawRoundRect(centerX-(distance*i), (int)(centerY*.9)-(distance*i), centerX+(distance*i), height*2, width, width, strokePaint);
        }
    }
}
