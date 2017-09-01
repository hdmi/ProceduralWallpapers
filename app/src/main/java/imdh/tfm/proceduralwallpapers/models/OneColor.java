package imdh.tfm.proceduralwallpapers.models;

/**
 * Created by CarlosAB on 06/08/2017.
 */

public class OneColor extends android.graphics.Color {
    private int color;

    public OneColor(int color){
        super(); this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
