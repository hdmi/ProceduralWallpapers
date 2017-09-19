package imdh.tfm.proceduralwallpapers;


import android.app.Application;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

import com.evernote.android.job.JobManager;

import java.io.File;

import static android.os.Environment.DIRECTORY_PICTURES;
import static imdh.tfm.proceduralwallpapers.Constants.ENABLED_WALLPAPERS_PREFERENCE_COUNTER_NAME;

/**
 * Created by CarlosAB on 06/09/2017.
 */

public class App extends Application{

    private String IMAGES_PATH;

    @Override
    public void onCreate() {
        super.onCreate();
        IMAGES_PATH = Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES).getAbsolutePath()+ File.separator + getString(R.string.app_name);
        checkPreferences();

    }

    public String getIMAGES_PATH() {
        return IMAGES_PATH;
    }

    public void preferencesChanged(){
        System.out.println("preferenceChanged");

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        long frequency = Long.parseLong(sharedPrefs.getString("prefSyncFrequency", "-1")) * 1000L * 60L;
        System.out.println("Frequency from prefences: "+ frequency);

        JobManager.create(this).addJobCreator(JobCreator.getInstance());

        if(frequency <= 0){
            JobCreator.getInstance().destroy();
            return;
        }
        else{
            UpdateWallpaperJob updateWallpaperJob = (UpdateWallpaperJob) JobCreator.getInstance().create(UpdateWallpaperJob.TAG);
            updateWallpaperJob.scheduleJob(frequency);
        }

    }

    public void checkPreferences(){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        System.out.println(sharedPrefs.getAll());

        if(!sharedPrefs.contains(ENABLED_WALLPAPERS_PREFERENCE_COUNTER_NAME)
                || sharedPrefs.getInt(ENABLED_WALLPAPERS_PREFERENCE_COUNTER_NAME, -100) <= 0){
            editor.putInt(ENABLED_WALLPAPERS_PREFERENCE_COUNTER_NAME, 0);
            editor.apply();
        }
        for(String wallpaperName: Constants.WALLPAPERS_NAMES){
            if(!sharedPrefs.contains(wallpaperName)){
                editor.putBoolean(wallpaperName, true);
                editor.putInt(ENABLED_WALLPAPERS_PREFERENCE_COUNTER_NAME,
                        sharedPrefs.getInt(ENABLED_WALLPAPERS_PREFERENCE_COUNTER_NAME, -100)+1);
            }
            editor.apply();
        }
        editor.commit();
    }

}