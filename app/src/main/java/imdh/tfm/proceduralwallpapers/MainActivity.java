package imdh.tfm.proceduralwallpapers;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static imdh.tfm.proceduralwallpapers.Constants.PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    private boolean isImageFitToScreen = false;
    private ImageView wallpaper;
    private GenericWallpaper genericWallpaper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wallpaper = (ImageView) findViewById(R.id.wallpaper);


        Button btnRefresh = (Button) findViewById(R.id.buttonRefresh);
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

        Button btnExport = (Button) findViewById(R.id.buttonExport);
        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if(doIHavePermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    new BitmapStorageExport(genericWallpaper.getBitmap()).execute();
                }
            }
        });

        wallpaper.setImageBitmap(drawLineWallapaper());
    }

    private void askForPermission(final String permission) {
        if (ContextCompat.checkSelfPermission(this,
                permission)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    permission)) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle(R.string.storage_permission_title);
                alertBuilder.setMessage(R.string.storage_permission_explanation);
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{permission},
                                PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                    }
                });

                AlertDialog alert = alertBuilder.create();
                alert.show();

            }
            else{
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{permission},
                        PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }
    }

    private Bitmap drawLineWallapaper() {
        LinesWallpaper primerWallpaper = new LinesWallpaper();
        genericWallpaper = primerWallpaper;
        return primerWallpaper.getBitmap();
    }

    private boolean doIHavePermission(String permission)
    {
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}
