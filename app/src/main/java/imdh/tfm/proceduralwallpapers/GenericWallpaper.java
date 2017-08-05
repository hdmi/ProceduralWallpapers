package imdh.tfm.proceduralwallpapers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;


public class GenericWallpaper {

  private Canvas canvas;

  public GenericWallpaper() {
    canvas = new Canvas(Bitmap.createBitmap(Constants.DEFAULT_WIDTH, Constants.DEFAULT_HEIGHT, Bitmap.Config.ARGB_8888));
  }

  public GenericWallpaper(int width, int height) {
    canvas = new Canvas(Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888));
  }

  public Canvas getCanvas() {
    return canvas;
  }

  public void setCanvas(Canvas canvas) {
    this.canvas = canvas;
  }

  public void fillWithColor(int color) {
    Paint mPaint = new Paint();
      mPaint.setStyle(Paint.Style.FILL);
      mPaint.setColor(color);
      canvas.drawRect(0,0,canvas.getWidth(), canvas.getHeight(), mPaint);
  }
}
