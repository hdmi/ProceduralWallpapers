package imdh.tfm.proceduralwallpapers.dataitems.wallpapers;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

import imdh.tfm.proceduralwallpapers.utils.Constants;
import imdh.tfm.proceduralwallpapers.dataitems.Palette;
import imdh.tfm.proceduralwallpapers.utils.UtilsWallpaper;

import static imdh.tfm.proceduralwallpapers.utils.Constants.DEFAULT_LINE_THICKNESS;


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

        strokePaint.setStrokeWidth(DEFAULT_LINE_THICKNESS);
        strokePaint.setColor(Color.BLACK);
        strokePaint.setStyle(Paint.Style.STROKE);

        final int width = canvas.getWidth();
        final int height =  canvas.getHeight();

        int[] rotations = {0,45};
        int currentRotation = rotations[UtilsWallpaper.randomBetween(0,rotations.length)];
        canvas.rotate(currentRotation, width/2, height/2);
        boolean stroke = new Random().nextBoolean();
        for (int x = (int)(width*-.5); x < (width*1.5); x+=squareSize) {
            for(int y = (int)(height*-.5); y < height; y+=squareSize){
                mPaint.setColor(palette.randomColor());
                canvas.drawRect(x, y, x+squareSize, y+squareSize, mPaint);
                if(stroke){
                    canvas.drawRect(x, y, x+squareSize, y+squareSize, strokePaint);
                }
            }
        }

    }
}
