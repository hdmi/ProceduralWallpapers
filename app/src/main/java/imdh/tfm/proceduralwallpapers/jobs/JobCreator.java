package imdh.tfm.proceduralwallpapers.jobs;


import com.evernote.android.job.Job;
import com.evernote.android.job.JobManager;

/**
 * Created by CarlosAB on 06/09/2017.
 */

public class JobCreator implements com.evernote.android.job.JobCreator{

    private static JobCreator instance = new JobCreator();
    private Job updateWallpaperJob;

    private JobCreator(){}

    public static JobCreator getInstance(){
        return instance;
    }

    @Override
    public Job create(String tag) {
        if(tag.equals(UpdateWallpaperJob.TAG)){
            System.out.println("Job created");
            updateWallpaperJob = new UpdateWallpaperJob();
            return updateWallpaperJob;
        }
        return null;
    }

    public void destroy(){
        if(updateWallpaperJob != null){
            System.out.println("Job destroyed");
            JobManager.instance().cancelAll();
            updateWallpaperJob = null;
        }
    }

    public Job getJob(){
        return updateWallpaperJob;
    }


}
