package imdh.tfm.proceduralwallpapers.utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

import imdh.tfm.proceduralwallpapers.R;
/**
 * Created by CarlosAB on 07/08/2017.
 */

public class BitmapStorageExport extends AsyncTask<Void, Integer, String>{

    //TODO: Move file_path path to preferences
    private File directory;
    private Bitmap bitmap;

    private View content;

    public BitmapStorageExport(Bitmap bitmap, View content, String file_path){
        //Check directory if exists, otherwise create it
        this.bitmap = bitmap;
        this.content = content;
        directory = new File(file_path);
        if(!directory.exists()){
            directory.mkdirs();
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        System.out.println("Start");
        saveBitmap(bitmap);
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Snackbar.make(content, R.string.image_saved_snackbar, Snackbar.LENGTH_LONG)
                .show();
    }

    private void saveBitmap(Bitmap bitmap){
        File fileBitmap = new File(directory, "wallpaper_" + System.currentTimeMillis()+ ".png");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileBitmap);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            System.out.println("guardao");
        } catch (java.io.IOException e) {
            e.printStackTrace();
            cancel(true);
        }
    }
}
