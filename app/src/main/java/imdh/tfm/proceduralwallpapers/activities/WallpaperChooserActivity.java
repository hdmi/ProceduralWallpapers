package imdh.tfm.proceduralwallpapers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import imdh.tfm.proceduralwallpapers.R;
import imdh.tfm.proceduralwallpapers.adapters.WallpaperChooserAdapter;

/**
 * Created by CarlosAB on 16/09/2017.
 */

public class WallpaperChooserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper_chooser);
        setTitle(R.string.wallpaper_chooser_title);

        WallpaperChooserAdapter adapter = new WallpaperChooserAdapter(this);
        GridView grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(adapter);

    }
}
