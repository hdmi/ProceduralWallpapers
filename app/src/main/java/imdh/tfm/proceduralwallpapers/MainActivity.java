package imdh.tfm.proceduralwallpapers;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private boolean isImageFitToScreen = false;
    private ImageView wallpaper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wallpaper = (ImageView) findViewById(R.id.wallpaper);


        Button btnRefresh = (Button) findViewById(R.id.button);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wallpaper.setImageBitmap(drawLineWallapaper());
            }
        });

        wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isImageFitToScreen) {
                    isImageFitToScreen=false;
                    wallpaper.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    wallpaper.setAdjustViewBounds(true);
                }else{
                    isImageFitToScreen=true;
                    wallpaper.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    wallpaper.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });


        wallpaper.setImageBitmap(drawLineWallapaper());
    }

    private Bitmap drawLineWallapaper() {
        LinesWallpaper primerWallpaper = new LinesWallpaper();
        return primerWallpaper.getBitmap();
    }

}