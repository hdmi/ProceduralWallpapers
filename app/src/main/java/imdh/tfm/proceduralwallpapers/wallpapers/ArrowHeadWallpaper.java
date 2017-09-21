package imdh.tfm.proceduralwallpapers.wallpapers;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

import imdh.tfm.proceduralwallpapers.models.Palette;
import imdh.tfm.proceduralwallpapers.utils.UtilsWallpaper;

/**
 * Created by CarlosAB on 12/08/2017.
 */

public class ArrowHeadWallpaper extends GenericWallpaper {

    public ArrowHeadWallpaper(){
        super();
        draw();
    }

    public ArrowHeadWallpaper(Palette newPalette){
        super();
        setPalette(newPalette);
        draw();
    }

    private void draw(){
        Paint mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        fillWithColor(getPalette().randomColor());

        final int width = canvas.getWidth();
        final int height =  canvas.getHeight();

//        final int diameter;
//
//        if(UtilsWallpaper.randomBetween(0,2)%2 == 0){
//            diameter = (width*2)/DEFAULT_W_CICLES_NUM_OF_CIRCLES;
//        }
//        else{
//            diameter = (height*2)/DEFAULT_W_CICLES_NUM_OF_CIRCLES;
//        }

//        int[] rotations = {0,45};
//        int currentRotation = rotations[UtilsWallpaper.randomBetween(0,rotations.length)];
//        canvas.rotate(currentRotation, width/2, height/2);
//        for (int x = diameter*-2; x < height+diameter; x+=diameter){
//            for (int y = diameter*-2; y < height+diameter; y+=diameter){
//                mPaint.setColor(palette.randomColor());
//                canvas.drawCircle(x, y, diameter/2, mPaint);
//            }

//        }

        int ancho = 100;
        int alto = 120;

        List<Integer> availablePalette = paletteToList(getPalette());
        ArrayList<Integer> sideColor = new ArrayList<>();
        int quitado = 1;
        for(int x = 0; x < width; x+=ancho){
            for(int y = 0; y < height; y+=alto){
                int currentColor = availablePalette.get(UtilsWallpaper.randomBetween(0, availablePalette.size()));
                if(availablePalette.size() < 5){
                    availablePalette.add(quitado);
                }
                mPaint.setColor(currentColor);
                canvas.drawPath(obtainArrowHead(new Point(x,y), ancho, alto, 50), mPaint);
                availablePalette.remove(availablePalette.indexOf(currentColor));
                quitado = currentColor;




            }
            if(sideColor.size() >= height/alto){
                sideColor.clear();
            }
            availablePalette = paletteToList(getPalette());
        }
    }

    private Path obtainArrowHead(Point p, int ancho, int alto, int triangleHeight){
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(p.x, p.y);
        path.lineTo(p.x, p.y+alto);
        path.lineTo(p.x+(ancho/2), p.y+alto+triangleHeight);
        path.lineTo(p.x+ancho, p.y+alto);
        path.lineTo(p.x+ancho, p.y);
        path.lineTo(p.x+(ancho/2), p.y+triangleHeight);
        path.lineTo(p.x, p.y);
        path.close();
        return path;
    }

    private List<Integer> paletteToList(Palette palette){
        ArrayList<Integer> palettes = new ArrayList<>();
        palettes.add(palette.getC0());
        palettes.add(palette.getC1());
        palettes.add(palette.getC2());
        palettes.add(palette.getC3());
        palettes.add(palette.getC4());
        return palettes;
    }
}
