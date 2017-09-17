package imdh.tfm.proceduralwallpapers.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import imdh.tfm.proceduralwallpapers.R;
import imdh.tfm.proceduralwallpapers.utils.BitmapStorageExport;
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
    private ArrayList<Bitmap> temporalWallpapers;

    private ArrayList<File> cachedFiles;
    private final String CACHE_STORAGE_DIR;


    public WallpaperChooserAdapter(Context c) {
        mContext = c;
        mInflater = LayoutInflater.from(c);
        enabledWallpapers = new ArrayList<String>(Arrays.asList(WALLPAPERS_NAMES));

        CACHE_STORAGE_DIR = c.getCacheDir().getAbsolutePath() + File.separator + "wallpaperchooser";
        cachedFiles = new ArrayList<File>();
        try{
            File dir = new File(CACHE_STORAGE_DIR);
            File[] files = dir.listFiles();
            for(File f: files){
                cachedFiles.add(f);
            }
        }catch (NullPointerException npe){System.err.println("Cache firectory empty or inexistent");}


        if(cachedFiles.size() < WALLPAPERS_NAMES.length){
            temporalWallpapers = new ArrayList<>();
            //Generate wallpapers
            for(String wallpaperName: enabledWallpapers){
                ExactWallpaper exactWallpaper = new ExactWallpaper(wallpaperName);
                Bitmap bitmap = exactWallpaper.getBitmap();
                bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/3, bitmap.getHeight()/3, false);
                temporalWallpapers.add(bitmap);
                new BitmapStorageExport(bitmap, null, CACHE_STORAGE_DIR, wallpaperName).execute();
            }
        }

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

//        ExactWallpaper g = new ExactWallpaper(Constants.W_ARCS);
//        Drawable a = new BitmapDrawable(g.getBitmap());
//        String p = Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES).getAbsolutePath()+ File.separator + mContext.getString(R.string.app_name);
//        new BitmapStorageExport(g.getBitmap(), v, p).execute();

//        picture.setImageDrawable(g.getBitmap());

        if(cachedFiles.size() < WALLPAPERS_NAMES.length){
            picture.setImageBitmap(temporalWallpapers.get(position));
        }
        else{
            Glide.with(mContext)
                    .load(cachedFiles.get(position))
                    .thumbnail(.1f)
                    .into(picture);
        }

        return v;
    }


}

