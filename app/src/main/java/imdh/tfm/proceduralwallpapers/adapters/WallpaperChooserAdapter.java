package imdh.tfm.proceduralwallpapers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;

import imdh.tfm.proceduralwallpapers.R;
import imdh.tfm.proceduralwallpapers.utils.UtilsWallpaper;
import imdh.tfm.proceduralwallpapers.wallpapers.RandomWallpaper;

import static imdh.tfm.proceduralwallpapers.Constants.WALLPAPERS_NAMES;

/**
 * Created by CarlosAB on 09/08/2017.
 */

public class WallpaperChooserAdapter extends BaseAdapter {

    private Context mContext;
    private final LayoutInflater mInflater;

    private ArrayList<String> enabledWallpapers;
    private ArrayList<String> disabedWallpapers;

    public WallpaperChooserAdapter(Context c) {
        mContext = c;
        mInflater = LayoutInflater.from(c);
        enabledWallpapers = new ArrayList<String>(Arrays.asList(WALLPAPERS_NAMES));
        //Get classes from package
    }


    @Override
    public int getCount() {
        return 30;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ImageView picture;

        if (v == null) {
            v = mInflater.inflate(R.layout.item_wallpaper_chooser, parent, false);
        }
        picture = (ImageView) v.findViewById(R.id.grid_image);

        if(UtilsWallpaper.randomBetween(0,2)%2 == 0) picture.setBackgroundColor(0xff00ff);
        Glide.with(mContext)
                .load(new RandomWallpaper().getBitmap())
                .into(picture);

        return v;
    }

//    private ArrayList<String> getClassesOfPackage(String packageName) {
//        ArrayList<String> classes = new ArrayList<String>();
//        try {
//            String packageCodePath = mContext.getApplicationContext().getPackageCodePath();
//            System.out.println("PackageCodePath: "+ packageCodePath);
//
//            DexFile df = new DexFile(packageCodePath);
//            for (Enumeration<String> iter = df.entries(); iter.hasMoreElements(); ) {
//                String className = iter.nextElement();
//                System.out.println("Class: "+className);
//                if (className.contains(packageName)) {
//                    classes.add(className.substring(className.lastIndexOf(".") + 1, className.length()));
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return classes;
//    }

}

