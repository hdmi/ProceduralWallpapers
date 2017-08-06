package imdh.tfm.proceduralwallpapers;

import android.graphics.Paint;

public class LinesWallpaper extends GenericWallpaper {

    private OneColor backgroundColor;
    private int lineThickness;

    UtilsWallpaper utilsWallpaper;

    public LinesWallpaper() {
        super();

        //Variables initialization
        lineThickness = Constants.DEFAULT_LINE_THICKNESS;
        backgroundColor = new OneColor(0xFFFFFFFF);
        utilsWallpaper = utilsWallpaper.getInstance();

        fillWithColor(backgroundColor.getColor());
        Paint mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(lineThickness);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        Palette examplePalette = utilsWallpaper.getExamplePalette();

        int deviation = canvas.getWidth();
        for (int i = -deviation; i < deviation; i = i + 1) {
            mPaint.setColor(examplePalette.getColorsList().get(utilsWallpaper.randomBetween(0,5)).getColor());
            canvas.drawLine(i, 0, i+deviation, canvas.getHeight(), mPaint);
        }


    }

}
