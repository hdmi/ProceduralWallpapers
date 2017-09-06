package imdh.tfm.proceduralwallpapers;


import android.app.Application;

import com.evernote.android.job.JobManager;

/**
 * Created by CarlosAB on 06/09/2017.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("APplciation extasion");

        com.evernote.android.job.JobCreator jobCreator = new JobCreator();
        JobManager.create(this).addJobCreator(jobCreator);
        UpdateWallpaperJob updateWallpaperJob = (UpdateWallpaperJob) jobCreator.create(UpdateWallpaperJob.TAG);
        updateWallpaperJob.scheduleJob();
    }
}
