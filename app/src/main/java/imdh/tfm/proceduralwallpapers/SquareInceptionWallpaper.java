package imdh.tfm.proceduralwallpapers;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by CarlosAB on 12/08/2017.
 */

public class SquareInceptionWallpaper extends GenericWallpaper {

    Palette palette;
    Paint strokePaint = new Paint();

    public SquareInceptionWallpaper(){
        super();
        this.palette = new Palette();
        draw();
    }

    public SquareInceptionWallpaper(Palette newPalette){
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

        final int centerX = width/2;
        final int centerY = height/2;

        final int distance = 100;
        canvas.rotate(45, centerX, centerY);
        //canvas.rotate(UtilsWallpaper.randomBetween(30,50));
        for (int i = 10; i > 0 ; i--) {
            mPaint.setColor(palette.getColorNumber(i%5));
            canvas.drawRect(centerX-(distance*i), centerY-(distance*i), centerX+(distance*i), centerY+(distance*i), mPaint);
        }
    }
}
