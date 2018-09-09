package imdh.tfm.proceduralwallpapers.dataitems.wallpapers;

import android.graphics.Paint;

import imdh.tfm.proceduralwallpapers.dataitems.Palette;


public class FiveLinesFlagWallpaper extends GenericWallpaper {

    Palette palette;

    public FiveLinesFlagWallpaper(Palette newPalette){
        super();
        this.palette = newPalette != null ? newPalette : new Palette();
        draw();
    }

    private void draw(){
        Paint mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        fillWithColor(palette.getC4());

        final int width = canvas.getWidth();
        final int height =  canvas.getHeight();

        final int heightThird = height/3;
        final int distance = 100;
        final int thickness = 40;



//        canvas.rotate(45, centerX, centerY);
        for (int i = 0; i < 5 ; i++) {
            mPaint.setColor(palette.getColorNumber(i%5));
            canvas.drawRect(0, heightThird+(heightThird*i), width, (heightThird+(heightThird*i))+distance+thickness, mPaint);
        }
    }
}
