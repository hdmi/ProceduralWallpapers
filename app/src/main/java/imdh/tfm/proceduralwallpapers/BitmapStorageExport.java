package imdh.tfm.proceduralwallpapers;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.design.widget.Snackbar;

import java.io.File;
import java.io.FileOutputStream;

import static android.os.Environment.DIRECTORY_PICTURES;
/**
 * Created by CarlosAB on 07/08/2017.
 */

public class BitmapStorageExport extends AsyncTask<Void, Integer, String>{

    //TODO: Move file_path path to preferences
    private String file_path = Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES).getAbsolutePath()+ File.separator + R.string.app_name;
    private File directory;
    private Bitmap bitmap;

    public BitmapStorageExport(Bitmap bitmap){
        //Check directory if exists, otherwise create it
        this.bitmap = bitmap;
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

    protected void onPostExecute(Long result) {
        Snackbar.make(findViewById(android.R.id.content), "Image saved", Snackbar.LENGTH_SHORT)
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
        }
    }
}
