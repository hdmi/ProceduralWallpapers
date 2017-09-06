package imdh.tfm.proceduralwallpapers;


import com.evernote.android.job.Job;

/**
 * Created by CarlosAB on 06/09/2017.
 */

public class JobCreator implements com.evernote.android.job.JobCreator{

    @Override
    public Job create(String tag) {
        System.out.println("JobCreator");

        switch (tag) {
            case UpdateWallpaperJob.TAG:
                System.out.println("job creado");
                return new UpdateWallpaperJob();
            default:
                return null;
        }
    }
}
