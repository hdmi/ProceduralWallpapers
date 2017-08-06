package imdh.tfm.proceduralwallpapers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CarlosAB on 06/08/2017.
 */

public class Palette {
    private List<OneColor> colorsList;

    public Palette(){
        colorsList = new ArrayList<OneColor>(5);
    }

    public void addColor(int color){
        colorsList.add(new OneColor(color));
    }

    public List<OneColor> getColorsList() {
        return colorsList;
    }

    public void setColorsList(List<OneColor> colorsList) {
        this.colorsList = colorsList;
    }
}
