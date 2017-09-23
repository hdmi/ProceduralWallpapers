package imdh.tfm.proceduralwallpapers.dataitems.wallpapers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import imdh.tfm.proceduralwallpapers.utils.Constants;
import imdh.tfm.proceduralwallpapers.dataitems.Palette;


public class GenericWallpaper {

    protected Canvas canvas;
    private Bitmap bitmap;
    private Palette palette;

    public GenericWallpaper() {
        bitmap = Bitmap.createBitmap(Constants.DEFAULT_WIDTH, Constants.DEFAULT_HEIGHT, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        palette = new Palette();
    }

    public GenericWallpaper(int width, int height) {
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        palette = new Palette();
    }
    //TODO: canvas and palette not initialized
    public GenericWallpaper(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    public void fillWithColor(int color) {
        Paint mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), mPaint);
    }

    public Canvas getCanvas() {
        return canvas;
    }
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Palette getPalette() {
        return palette;
    }

    public void setPalette(Palette palette) {
        this.palette = palette;
    }
}
