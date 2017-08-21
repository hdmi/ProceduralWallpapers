package imdh.tfm.proceduralwallpapers;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
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
                wallpaper.setImageBitmap(drawCurrentWallpaper(null));
            }
        });

        Button btnOpenPalettesActivity = (Button) findViewById(R.id.buttonOpenPalettesActivity);
        btnOpenPalettesActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PalettesDisplayActivity.class);
                startActivityForResult(intent, 1);
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
                    new BitmapStorageExport(genericWallpaper.getBitmap(), findViewById(android.R.id.content)).execute();
                }
            }
        });

        Button btnSetWallpaper = (Button) findViewById(R.id.buttonSetWallpaper);
        btnSetWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilsWallpaper.setWallpaper2Desktop(genericWallpaper.getBitmap(), MainActivity.this);
            }
        });

        wallpaper.setImageBitmap(drawCurrentWallpaper(null));
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

    private Bitmap drawCurrentWallpaper(Palette palette) {
        RandomWallpaper primerWallpaper = new RandomWallpaper(palette, getApplicationContext());
        genericWallpaper = primerWallpaper;
        return primerWallpaper.getBitmap();
    }

    private boolean doIHavePermission(String permission)
    {
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Palette palette = (Palette) data.getSerializableExtra("PALETTE");
                wallpaper.setImageBitmap(drawCurrentWallpaper(palette));
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                System.out.println("Resultado de la actividad cancelado");
            }
        }
    }
}
