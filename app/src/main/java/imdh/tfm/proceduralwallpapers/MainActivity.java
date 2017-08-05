package imdh.tfm.proceduralwallpapers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView wallpaper = (ImageView) findViewById(R.id.wallpaper);

        GenericWallpaper primerWallpaper = new GenericWallpaper();

        primerWallpaper.fillWithColor(0xFF00FF00);

        primerWallpaper.getCanvas()
    }
}