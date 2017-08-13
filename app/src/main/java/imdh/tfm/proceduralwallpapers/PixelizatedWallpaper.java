package imdh.tfm.proceduralwallpapers;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by CarlosAB on 12/08/2017.
 */

public class PixelizatedWallpaper extends GenericWallpaper {

    int squareSize;
    Palette palette;
    Paint strokePaint = new Paint();

    public PixelizatedWallpaper(){
        super();
        this.squareSize = Constants.DEFAULT_SQUARE_SIZE;
        this.palette = new Palette();
        draw();
    }

    public PixelizatedWallpaper(Palette newPalette, int squareSize){
        super();
        this.squareSize = squareSize;
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

        final int width = canvas.getWidth();
        final int height =  canvas.getHeight();
        canvas.rotate(-45, width/2, height/2);
        for (int x = (int)(width*-.5); x < (width*1.5); x+=squareSize) {
            for(int y = (int)(height*-.5); y < height; y+=squareSize){
                mPaint.setColor(palette.randomColor());
                canvas.drawRect(x, y, x+squareSize, y+squareSize, mPaint);
                canvas.drawRect(x, y, x+squareSize, y+squareSize, strokePaint);
            }
        }

    }
}
