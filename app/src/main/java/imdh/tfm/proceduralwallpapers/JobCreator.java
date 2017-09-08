package imdh.tfm.proceduralwallpapers;


import com.evernote.android.job.Job;

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
            updateWallpaperJob = new UpdateWallpaperJob();
            return updateWallpaperJob;
        }
        return null;
    }

    public void destroy(){
        if(updateWallpaperJob != null){
            updateWallpaperJob.cancel();
        }
    }


}
