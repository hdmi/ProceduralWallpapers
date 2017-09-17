package imdh.tfm.proceduralwallpapers.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;

import imdh.tfm.proceduralwallpapers.Constants;
import imdh.tfm.proceduralwallpapers.R;
import imdh.tfm.proceduralwallpapers.wallpapers.ExactWallpaper;

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
        return enabledWallpapers.size();
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
        ImageButton picture;

        if (v == null) {
            v = mInflater.inflate(R.layout.item_wallpaper_chooser, parent, false);
        }
        picture = (ImageButton) v.findViewById(R.id.grid_image);

        ExactWallpaper g = new ExactWallpaper(Constants.W_ARCS);
//        Drawable a = new BitmapDrawable(g.getBitmap());
//        String p = Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES).getAbsolutePath()+ File.separator + mContext.getString(R.string.app_name);
//        new BitmapStorageExport(g.getBitmap(), v, p).execute();

//        picture.setImageDrawable(g.getBitmap());
        Bitmap bitmap = new ExactWallpaper(enabledWallpapers.get(position)).getBitmap();
        Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/10, bitmap.getHeight()/10, false);
        picture.setImageBitmap(bitmap);

        return v;
    }


}

