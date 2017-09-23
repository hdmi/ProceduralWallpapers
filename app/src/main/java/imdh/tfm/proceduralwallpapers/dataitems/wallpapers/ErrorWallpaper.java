package imdh.tfm.proceduralwallpapers.dataitems.wallpapers;

import android.graphics.Paint;

import static imdh.tfm.proceduralwallpapers.utils.Constants.DEFAULT_TEXT_SIZE;

/**
 * Created by CarlosAB on 12/08/2017.
 */

public class ErrorWallpaper extends GenericWallpaper {

    String error;
    public ErrorWallpaper(String error){
        super();
        this.error = error;
        draw();
    }

    private void draw(){
        Paint mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(DEFAULT_TEXT_SIZE*1.2f);

        fillWithColor(getPalette().getC4());

        final int width = canvas.getWidth();
        final int height =  canvas.getHeight();


        mPaint.setColor(getPalette().getC0());
        int y = height/2;
        for(int i = 0; i < error.length(); i+=DEFAULT_TEXT_SIZE){
            try{
                canvas.drawText(error.substring(i, i+DEFAULT_TEXT_SIZE), 0, y+=i, mPaint);
            }catch (IndexOutOfBoundsException iof){
                canvas.drawText(error.substring(i, error.length()), 0, y+=i, mPaint);
            }
        }
        canvas.drawText(error, 0, height/2, mPaint);
    }
}
