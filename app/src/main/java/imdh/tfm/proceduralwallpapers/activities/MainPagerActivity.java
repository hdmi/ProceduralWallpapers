package imdh.tfm.proceduralwallpapers.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import imdh.tfm.proceduralwallpapers.App;
import imdh.tfm.proceduralwallpapers.R;
import imdh.tfm.proceduralwallpapers.fragments.FirstFragment;
import imdh.tfm.proceduralwallpapers.fragments.SecondFragment;
import imdh.tfm.proceduralwallpapers.fragments.ThirdFragment;
import imdh.tfm.proceduralwallpapers.wallpapers.GenericWallpaper;

import static imdh.tfm.proceduralwallpapers.Constants.PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE;
import static imdh.tfm.proceduralwallpapers.R.id.viewPager;

/**
 * Created by CarlosAB on 24/08/2017.
 */

public class MainPagerActivity extends AppCompatActivity {

    private GenericWallpaper genericWallpaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_main);
        ViewPager pager = (ViewPager) findViewById(viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        setTitle(R.string.app_title);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

        setTitle(R.string.app_title);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            public void onPageSelected(int position) {
                switch(position){
                    case 0: setTitle(R.string.app_title); break;
                    case 1: setTitle(R.string.palettes_showcase_title); break;
                    case 2: setTitle(R.string.gallery_title); break;
                    default: setTitle(R.string.app_title); break;
                }
            }
        });

        firstTimeRun();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(this, PreferencesActivity.class);
        switch (item.getItemId()) {
            case R.id.open_preferences_action:
                startActivity(i);
                break;
            default:
                startActivity(i);
                break;
        }
        return true;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            System.out.println(pos);
            switch(pos) {
                case 0: return FirstFragment.newInstance();
                case 1: return SecondFragment.newInstance();
                case 2: return ThirdFragment.newInstance();
                default: return ThirdFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public void askForPermission(final String permission) {
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
                        ActivityCompat.requestPermissions(MainPagerActivity.this,
                                new String[]{permission},
                                PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                    }
                });

                AlertDialog alert = alertBuilder.create();
                alert.show();

            }
            else{
                ActivityCompat.requestPermissions(MainPagerActivity.this,
                        new String[]{permission},
                        PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }
    }

    public boolean doIHavePermission(String permission)
    {
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    public void firstTimeRun(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Initialize SharedPreferences
                SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);
                if (isFirstStart) {
                    //Set default values in shared preferences
                    PreferenceManager.setDefaultValues(getBaseContext(), R.xml.preferences, false);
                    ((App)getApplication()).preferencesChanged();

                    //  Launch app intro
                    Intent i = new Intent(MainPagerActivity.this, IntroActivity.class);
                    MainPagerActivity.this.startActivity(i);

                    //  Make a new preferences editor
                    SharedPreferences.Editor e = getPrefs.edit();

                    //  Edit preference to make it false because we don't want this to run again
                    e.putBoolean("firstStart", false);
                    e.apply();
                }
            }
        });
        t.start();
    }
}
