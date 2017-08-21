package imdh.tfm.proceduralwallpapers;

import android.graphics.Paint;

public class LinesWallpaper extends GenericWallpaper {

    private OneColor backgroundColor;
    private int lineThickness;

    UtilsWallpaper utilsWallpaper;

    public LinesWallpaper(Palette newPalette) {
        super();
        setPalette(newPalette);

        if (newPalette == null) newPalette = new Palette();

        //Variables initialization
        lineThickness = 15;
        backgroundColor = new OneColor(0xFFFFFFFF);
        utilsWallpaper = utilsWallpaper.getInstance();

        fillWithColor(backgroundColor.getColor());
        Paint mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(lineThickness);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        int deviation = canvas.getWidth();
        for (int i = -deviation; i < deviation; i = i + lineThickness) {
            mPaint.setColor(newPalette.randomColor());
            canvas.drawLine(i, 0, i+deviation, canvas.getHeight(), mPaint);
        }

    }

}
