package imdh.tfm.proceduralwallpapers.persistence;

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
    private String fileName;

    private View content;

    public BitmapStorageExport(Bitmap bitmap, View content, String filePath, String fileName){
        //Check directory if exists, otherwise create it
        this.bitmap = bitmap;
        this.content = content;
        this.fileName = fileName;
        directory = new File(filePath);
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
        if(content != null){
            Snackbar.make(content, R.string.image_saved_snackbar, Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    private void saveBitmap(Bitmap bitmap){
        File fileBitmap;
        if(fileName == null || fileName.equals("")){
            fileBitmap = new File(directory, "wallpaper_" + System.currentTimeMillis()+ ".png");
        }
        else{
            fileBitmap = new File(directory, fileName);
        }
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
