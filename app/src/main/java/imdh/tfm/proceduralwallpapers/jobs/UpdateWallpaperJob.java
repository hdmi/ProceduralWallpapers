package imdh.tfm.proceduralwallpapers.jobs;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

import java.util.Calendar;
import java.util.Date;

import imdh.tfm.proceduralwallpapers.utils.Constants;
import imdh.tfm.proceduralwallpapers.utils.UtilsWallpaper;
import imdh.tfm.proceduralwallpapers.dataitems.wallpapers.RandomWallpaper;


public class UpdateWallpaperJob extends Job {

    public static final String TAG = "UpdateWallpaperJob";

    @Override
    @NonNull
    protected Result onRunJob(Params params) {
        System.out.println(TAG+": running");
        UtilsWallpaper.setWallpaper2Desktop(new RandomWallpaper(null, this.getContext()).getBitmap(), this.getContext());
        return Result.SUCCESS;
    }

    public void scheduleJob(long periodicInterval) {

        System.out.println("Starting job scheduling");

        //If the periodicInterval is less than zero or zero we return without scheduling the Job
        if (periodicInterval <= 0) {
            System.out.println("Scheduling job we got zero or less interval");
            return;
        }

        //Assuring that the periodic interval it is not less than the allowed (15 min)
        if(periodicInterval < Constants.MIN_PERIODIC_INTERVAL){
            System.out.println("Interval to small, making it default");
            periodicInterval = Constants.MIN_PERIODIC_INTERVAL;
        }
        //TODO: Make the periodic window wider
        new JobRequest.Builder(UpdateWallpaperJob.TAG)
                .setPeriodic(periodicInterval)
                .setUpdateCurrent(true)
                .setPersisted(true)
                .build()
                .schedule();

        System.out.println("Job scheduled for the interval: "+periodicInterval/1000/60+" minutes");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, (int) periodicInterval/1000/60);
        System.out.println("Next run on: "+ cal.getTime() +" minutes");
        System.out.println("Ending job scheduling");

    }
}
