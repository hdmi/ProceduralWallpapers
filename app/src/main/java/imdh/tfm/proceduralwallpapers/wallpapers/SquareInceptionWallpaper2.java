package imdh.tfm.proceduralwallpapers.wallpapers;

import android.graphics.Color;
import android.graphics.Paint;

import imdh.tfm.proceduralwallpapers.Constants;
import imdh.tfm.proceduralwallpapers.models.Palette;
import imdh.tfm.proceduralwallpapers.utils.UtilsWallpaper;

/**
 * Created by CarlosAB on 12/08/2017.
 */

public class SquareInceptionWallpaper2 extends GenericWallpaper {

    Palette palette;
    Paint strokePaint = new Paint();

    public SquareInceptionWallpaper2(){
        super();
        this.palette = new Palette();
        draw();
    }

    public SquareInceptionWallpaper2(Palette newPalette){
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

        final int distance = 200;
        final int centerX;
        final int centerY;

        if(UtilsWallpaper.randomBetween(0,2)%2 == 0) {
            if(UtilsWallpaper.randomBetween(0,2)%2 == 0){
                centerX = width + (int) Math.sqrt(2*distance*distance);
            }
            else{
                centerX = (int) - Math.sqrt(2*distance*distance);
            }
            centerY = height/2;
        }
        else{
            if(UtilsWallpaper.randomBetween(0,2)%2 == 0){
                centerY = height + (int) Math.sqrt(2*distance*distance);
            }
            else{
                centerY = (int) - Math.sqrt(2*distance*distance);
            }
            centerX = width/2;
        }

        canvas.rotate(45, centerX, centerY);
        for (int i = 10; i > 0 ; i--) {
            mPaint.setColor(palette.getColorNumber(i%5));
            canvas.drawRect(centerX-(distance*i), centerY-(distance*i), centerX+(distance*i), centerY+(distance*i), mPaint);
        }
    }
}
