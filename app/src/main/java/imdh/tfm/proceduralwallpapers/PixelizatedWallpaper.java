package imdh.tfm.proceduralwallpapers;

import android.graphics.Paint;

/**
 * Created by CarlosAB on 12/08/2017.
 */

public class PixelizatedWallpaper extends GenericWallpaper {

    int squareSize;
    Palette palette;

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

        final int width = canvas.getWidth();
        final int height = canvas.getHeight();
        for (int x = 0; x < width; x+=squareSize) {
            for(int y = 0; y < height; y+=squareSize){
                mPaint.setColor(palette.randomColor());
                canvas.drawRect(x, y, x+squareSize, y+squareSize, mPaint);
            }
        }
    }
}
