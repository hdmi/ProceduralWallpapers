package imdh.tfm.proceduralwallpapers.presentation.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
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
import android.view.Window;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import imdh.tfm.proceduralwallpapers.App;
import imdh.tfm.proceduralwallpapers.R;
import imdh.tfm.proceduralwallpapers.presentation.fragments.WallpaperGeneratorFragment;
import imdh.tfm.proceduralwallpapers.presentation.fragments.PalettesShowcaseFragment;
import imdh.tfm.proceduralwallpapers.presentation.fragments.MyGalleryFragment;
import imdh.tfm.proceduralwallpapers.dataitems.Palette;
import imdh.tfm.proceduralwallpapers.dataitems.wallpapers.GenericWallpaper;

import static imdh.tfm.proceduralwallpapers.utils.Constants.PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE;
import static imdh.tfm.proceduralwallpapers.R.id.viewPager;

/**
 * Created by CarlosAB on 24/08/2017.
 */

public class MainPagerActivity extends AppCompatActivity implements PalettesShowcaseFragment.OnPaletteSelectedListener, MyGalleryFragment.WallpaperSelectedListener{

    private WallpaperGeneratorFragment firstFragment;
    private PalettesShowcaseFragment secondFragment;
    private MyGalleryFragment thirdFragment;
    private ViewPager pager;
    private AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_main);
        pager = (ViewPager) findViewById(viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        setTitle(R.string.app_title);



//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//
//        toolbar.inflateMenu(R.menu.actionbar_menu);


        View decorView = getWindow().getDecorView();
        final Window window = getWindow();
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
                    case 0:
                        window.setStatusBarColor(getResources().getColor(R.color.color_tab_1_dark));
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color_tab_1)));
                        bottomNavigation.setCurrentItem(0);
                        setTitle(R.string.app_title);
                        break;
                    case 1:
                        window.setStatusBarColor(getResources().getColor(R.color.color_tab_2_dark));
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color_tab_2)));
                        bottomNavigation.setCurrentItem(1);
                        setTitle(R.string.palettes_showcase_title);
                        break;
                    case 2:
                        window.setStatusBarColor(getResources().getColor(R.color.color_tab_3_dark));
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color_tab_3)));
                        bottomNavigation.setCurrentItem(2);
                        setTitle(R.string.gallery_title); break;
                    default:
                        setTitle(R.string.app_title);
                        break;
                }
            }
        });

        setupBottomNavigation();

        firstTimeRun();
    }

    private void setupBottomNavigation() {
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_check_white_small, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_palette_white_small, R.color.color_tab_2);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_heart_white_small, R.color.color_tab_3);

        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

//        bottomNavigation.setBehaviorTranslationEnabled(true);

        bottomNavigation.setBehaviorTranslationEnabled(false);

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(0xff00ff00);

        // Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);

        // Change colors
        bottomNavigation.setAccentColor(0xff00ff00);
        bottomNavigation.setInactiveColor(0xff00ff00);

        // Force to tint the drawable (useful for font with icon for example)
//        bottomNavigation.setForceTint(true);

        // Manage titles
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);

        // Use colored navigation with circle reveal effect
        bottomNavigation.setColored(true);

        // Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position){
                    case 0:
                        pager.setCurrentItem(0);
                        break;
                    case 1:
                        pager.setCurrentItem(1);
                        break;
                    case 2:
                        pager.setCurrentItem(2);
                        break;
                    default:break;
                }
                return true;
            }
        });
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
            case R.id.open_wallpaper_chooser_action:
                startActivity(new Intent(this, WallpaperChooserActivity.class));
                break;
            default:
                startActivity(i);
                break;
        }
        return true;
    }

    @Override
    public void onPaletteSelected(Palette palette) {
        if (firstFragment != null) {
            pager.setCurrentItem(0);
            firstFragment.setWallpaperWithPalette(palette);
        }
    }

    @Override
    public void onWallpaperSelected(GenericWallpaper genericWallpaper) {
        if (firstFragment != null) {
            pager.setCurrentItem(0);
            firstFragment.setThisWallpaper(genericWallpaper);
        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            System.out.println(pos);
            switch(pos) {
                case 0: firstFragment = WallpaperGeneratorFragment.newInstance(); return firstFragment;
                case 1: secondFragment = PalettesShowcaseFragment.newInstance(); return secondFragment;
                case 2: thirdFragment = MyGalleryFragment.newInstance(); return thirdFragment;
                default: thirdFragment = MyGalleryFragment.newInstance(); return thirdFragment;
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

    @Override
    protected void onResume(){
        super.onResume();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }
}
