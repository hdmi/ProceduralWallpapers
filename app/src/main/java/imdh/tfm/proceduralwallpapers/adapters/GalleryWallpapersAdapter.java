package imdh.tfm.proceduralwallpapers.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import imdh.tfm.proceduralwallpapers.R;
import imdh.tfm.proceduralwallpapers.dataitems.wallpapers.GenericWallpaper;

import static android.os.Environment.DIRECTORY_PICTURES;

/**
 * Created by CarlosAB on 09/08/2017.
 */

public class GalleryWallpapersAdapter extends BaseAdapter {

    private Context mContext;
    private final LayoutInflater mInflater;
    private File[] files;
    private ArrayList<File> images;
    private final String MEDIA_STORAGE_DIR;
    private final String[] SUPPORTED_IMAGES = {"jpeg", "jpg", "png", "JPG", "JPEG", "PNG"};
    private GalleryWallpapersAdapterListener galleryWallpapersAdapterListener;

    public GalleryWallpapersAdapter(Context c) {
        mContext = c;
        mInflater = LayoutInflater.from(c);
        MEDIA_STORAGE_DIR = Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES).getAbsolutePath()+ File.separator + mContext.getString(R.string.app_name);

        images = new ArrayList<File>();
        try{
            File dir = new File(MEDIA_STORAGE_DIR);
            files = dir.listFiles();
            images = stripNonImages(files);
        //TODO: Change this exception to a suited exception
        }catch (Exception fnfe){System.err.print("No existe el directorio de imágenes"+fnfe);}

    }


    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ImageView picture;
        ImageButton dislikeButton;


        if (v == null) {
            v = mInflater.inflate(R.layout.item_wallpaper, parent, false);
        }
        picture = (ImageView) v.findViewById(R.id.imageItemGridView);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (galleryWallpapersAdapterListener != null) {
                    Bitmap bitmap = BitmapFactory.decodeFile(((File)getItem(position)).getAbsolutePath());
                    galleryWallpapersAdapterListener.onWallpaperSelectedInAdapter(new GenericWallpaper(bitmap));
                }
            }
        });

        dislikeButton = (ImageButton) v.findViewById(R.id.imageButtonDislike);
        dislikeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(mContext);
                adb.setTitle(R.string.dialog_delete_wallpaper_title);
                adb.setMessage(R.string.dialog_delete_message);
                adb.setNegativeButton(R.string.dialog_cancel, null);
                adb.setPositiveButton(R.string.dialog_ok, new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        images.get(position).delete();
                        images.remove(position);

                        notifyDataSetChanged();
                    }});
                adb.show();
            }
        });

        Glide.with(mContext)
                .load(images.get(position))
                .thumbnail(.1f)
                .into(picture);

        return v;
    }

    public ArrayList<File> stripNonImages(File[] files){
        ArrayList<File> images = new ArrayList<File>();

        //Get file, if it's image add to arraylist, otherwise don't add it
        for (File child : files) {
            if(isImage(child)){
                images.add(child);
            }
        }
        return images;
    }

    public boolean isImage(File file){
        String fileName = file.getName();
        if(file.isDirectory()) return false;

        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i >= 0) {
            extension = fileName.substring(i+1);
        }

        if(Arrays.asList(SUPPORTED_IMAGES).contains(extension)){
            return true;
        }
        return false;
    }

    public interface GalleryWallpapersAdapterListener{
        void onWallpaperSelectedInAdapter(GenericWallpaper genericWallpaper);
    }

    public void setGalleryWallpapersAdapterListener(GalleryWallpapersAdapterListener galleryWallpapersAdapterListener){
        this.galleryWallpapersAdapterListener = galleryWallpapersAdapterListener;
    }

}
