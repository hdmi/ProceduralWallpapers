package imdh.tfm.proceduralwallpapers;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

import static android.os.Environment.DIRECTORY_PICTURES;

/**
 * Created by CarlosAB on 07/08/2017.
 */

public class LocalStorage {

    //TODO: Move file_path path to preferences
    private String file_path = Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES).getAbsolutePath()+ File.separator + R.string.app_name;
    private File directory;

    public LocalStorage(){
        //Check directory if exists, otherwise create it
        directory = new File(file_path);
        if(!directory.exists()){
            directory.mkdirs();
        }
    }

    public void saveBitmap(Bitmap bitmap){
        File fileBitmap = new File(directory, "wallpaper_" + System.currentTimeMillis()+ ".jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileBitmap);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
