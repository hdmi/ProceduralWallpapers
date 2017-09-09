package imdh.tfm.proceduralwallpapers;


import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.evernote.android.job.JobManager;

/**
 * Created by CarlosAB on 06/09/2017.
 */

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
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
}