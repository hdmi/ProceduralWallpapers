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

        JobManager.create(this).addJobCreator(JobCreator.getInstance());
        UpdateWallpaperJob updateWallpaperJob = (UpdateWallpaperJob) JobCreator.getInstance().create(UpdateWallpaperJob.TAG);
        updateWallpaperJob.scheduleJob(periodicInterval);
    }
}
