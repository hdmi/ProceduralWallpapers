package imdh.tfm.proceduralwallpapers;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

import imdh.tfm.proceduralwallpapers.utils.UtilsWallpaper;
import imdh.tfm.proceduralwallpapers.wallpapers.RandomWallpaper;

/**
 * Created by CarlosAB on 06/09/2017.
 */

class UpdateWallpaperJob extends Job {

    public static final String TAG = "UpdateWallpaperJob";

    @Override
    @NonNull
    protected Result onRunJob(Params params) {
        System.out.println(TAG+": running");
        UtilsWallpaper.setWallpaper2Desktop(new RandomWallpaper(null, this.getContext()).getBitmap(), this.getContext());
//        scheduleJob();

        this.getParams().getId();

        return Result.SUCCESS;
    }

    public static void scheduleJob(long periodicInterval) {
        if(periodicInterval < Constants.MIN_PERIODIC_INTERVAL){
            periodicInterval = Constants.MIN_PERIODIC_INTERVAL;
        }
        new JobRequest.Builder(UpdateWallpaperJob.TAG)
//              .setExecutionWindow(1_000L, 2_000L)
                .setPeriodic(periodicInterval, periodicInterval)
                .build()
                .schedule();
    }
}
