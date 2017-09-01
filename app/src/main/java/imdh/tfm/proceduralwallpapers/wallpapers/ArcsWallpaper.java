package imdh.tfm.proceduralwallpapers.wallpapers;

import android.graphics.Color;
import android.graphics.Paint;

import imdh.tfm.proceduralwallpapers.Constants;
import imdh.tfm.proceduralwallpapers.models.Palette;

/**
 * Created by CarlosAB on 12/08/2017.
 */

public class ArcsWallpaper extends GenericWallpaper {

    Palette palette;
    Paint strokePaint = new Paint();

    public ArcsWallpaper(){
        super();
        this.palette = new Palette();
        draw();
    }

    public ArcsWallpaper(Palette newPalette){
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

        final int distance = (int)(width * .09);
        for (int i = 10; i > 0 ; i--) {
            mPaint.setColor(palette.getColorNumber(i%5));
            canvas.drawRoundRect(centerX-(distance*i), (int)(centerY*.9)-(distance*i), centerX+(distance*i), height*2, width, width, mPaint);
            canvas.drawRoundRect(centerX-(distance*i), (int)(centerY*.9)-(distance*i), centerX+(distance*i), height*2, width, width, strokePaint);
        }
    }
}
