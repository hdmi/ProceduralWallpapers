package imdh.tfm.proceduralwallpapers.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import imdh.tfm.proceduralwallpapers.R;
import imdh.tfm.proceduralwallpapers.utils.BitmapStorageExport;
import imdh.tfm.proceduralwallpapers.wallpapers.RandomWallpaper;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static imdh.tfm.proceduralwallpapers.Constants.ENABLED_WALLPAPERS_PREFERENCE_COUNTER_NAME;
import static imdh.tfm.proceduralwallpapers.Constants.ERROR_INT_PREFERENCES;
import static imdh.tfm.proceduralwallpapers.Constants.WALLPAPERS_NAMES;

/**
 * Created by CarlosAB on 09/08/2017.
 */

public class WallpaperChooserAdapter extends BaseAdapter {

    private Context mContext;
    private final LayoutInflater mInflater;

    private ArrayList<String> enabledWallpapers;

    private ArrayList<File> cachedFiles;
    private final String CACHE_STORAGE_DIR;

    private SharedPreferences sharedPreferences;

    public WallpaperChooserAdapter(Context c) {
        mContext = c;
        mInflater = LayoutInflater.from(c);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        enabledWallpapers = new ArrayList<String>(Arrays.asList(WALLPAPERS_NAMES));

        CACHE_STORAGE_DIR = c.getCacheDir().getAbsolutePath() + File.separator + "wallpaperchooser";
        cachedFiles = new ArrayList<File>();
        try{
            fillCachedFiles();
        }catch (NullPointerException npe){System.err.println("Cache firectory empty or inexistent");}


        if(cachedFiles.size() < WALLPAPERS_NAMES.length){
            //Generate wallpapers
            for(String wallpaperName: enabledWallpapers){
                RandomWallpaper randomWallpaper = new RandomWallpaper(wallpaperName);
                Bitmap bitmap = randomWallpaper.getBitmap();
                bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/3, bitmap.getHeight()/3, false);
                try {
                    new BitmapStorageExport(bitmap, null, CACHE_STORAGE_DIR, wallpaperName).execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            fillCachedFiles();
        }

    }

    private void fillCachedFiles() {
        File dir = new File(CACHE_STORAGE_DIR);
        File[] files = dir.listFiles();
        for(File f: files){
            cachedFiles.add(f);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ImageButton picture;

        if (v == null) {
            v = mInflater.inflate(R.layout.item_wallpaper_chooser, parent, false);
        }
        picture = (ImageButton) v.findViewById(R.id.grid_image);

        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    boolean currentState = sharedPreferences.getBoolean(WALLPAPERS_NAMES[position], false);
                    int currentCounter = sharedPreferences.getInt(ENABLED_WALLPAPERS_PREFERENCE_COUNTER_NAME, ERROR_INT_PREFERENCES);

                    if(currentState && currentCounter <= 1){return;}

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(WALLPAPERS_NAMES[position], !currentState);
                    if(currentState){
                        editor.putInt(ENABLED_WALLPAPERS_PREFERENCE_COUNTER_NAME, currentCounter-1);
                    }
                    else {
                        editor.putInt(ENABLED_WALLPAPERS_PREFERENCE_COUNTER_NAME, currentCounter+1);
                    }

                    editor.commit();

                }catch (IndexOutOfBoundsException ioobe){System.err.print(ioobe);}
//                System.out.println(sharedPreferences.getAll());
                notifyDataSetChanged();
            }
        });

        if(sharedPreferences.getBoolean(enabledWallpapers.get(position), false)){
            Glide.with(mContext)
                    .load(cachedFiles.get(position))
                    .thumbnail(.1f)
                    .into(picture);
        }
        else{
            Glide.with(mContext)
                    .load(cachedFiles.get(position))
                    .thumbnail(.1f)
                    .apply(bitmapTransform(new GrayscaleTransformation()))
                    .into(picture);
        }

        return v;
    }

    private Bitmap bitmap2BW(Bitmap toTransform){

        int width = toTransform.getWidth();
        int height = toTransform.getHeight();

        Bitmap.Config config =
                toTransform.getConfig() != null ? toTransform.getConfig() : Bitmap.Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);

        Canvas canvas = new Canvas(bitmap);
        ColorMatrix saturation = new ColorMatrix();
        saturation.setSaturation(0f);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(saturation));
        canvas.drawBitmap(toTransform, 0, 0, paint);

        return bitmap;
    }


}

