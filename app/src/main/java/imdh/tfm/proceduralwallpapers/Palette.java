package imdh.tfm.proceduralwallpapers;

import android.graphics.Color;

import java.io.Serializable;

/**
 * Created by CarlosAB on 06/08/2017.
 */

public class Palette implements Serializable{

    private int c0;
    private int c1;
    private int c2;
    private int c3;
    private int c4;

    public Palette(){
        c0 = 0xFFE63946;
        c1 = 0xFFF1FAEE;
        c2 = 0xFFA8DADC;
        c3 = 0xFF457B9D;
        c4 = 0xFF1D3557;
    }

    public Palette(int c0, int c1, int c2, int c3, int c4){
        this.c0 = c0;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
    }

    public Palette(String c0, String c1, String c2, String c3, String c4){
        this.c0 = Color.parseColor(c0);
        this.c1 = Color.parseColor(c1);
        this.c2 = Color.parseColor(c2);
        this.c3 = Color.parseColor(c3);
        this.c4 = Color.parseColor(c4);
    }

    public int randomColor(){
        switch (UtilsWallpaper.randomBetween(0,5)){
            case 0: return c0;
            case 1: return c1;
            case 2: return c2;
            case 3: return c3;
            case 4: return c4;
            default: return 0;
        }
    }

    @Override
    public String toString() {
        return "[-> " + c0 + ", " + c1 + ", " + c2 + ", " + c3 + ", " + c4 + " <-]";
    }

    public int getC0() {
        return c0;
    }

    public void setC0(int c0) {
        this.c0 = c0;
    }

    public int getC1() {
        return c1;
    }

    public void setC1(int c1) {
        this.c1 = c1;
    }

    public int getC2() {
        return c2;
    }

    public void setC2(int c2) {
        this.c2 = c2;
    }

    public int getC3() {
        return c3;
    }

    public void setC3(int c3) {
        this.c3 = c3;
    }

    public int getC4() {
        return c4;
    }

    public void setC4(int c4) {
        this.c4 = c4;
    }
}
