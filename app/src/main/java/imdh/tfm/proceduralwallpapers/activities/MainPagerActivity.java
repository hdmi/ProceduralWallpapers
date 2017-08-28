package imdh.tfm.proceduralwallpapers.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import imdh.tfm.proceduralwallpapers.GenericWallpaper;
import imdh.tfm.proceduralwallpapers.R;
import imdh.tfm.proceduralwallpapers.fragments.FirstFragment;
import imdh.tfm.proceduralwallpapers.fragments.SecondFragment;
import imdh.tfm.proceduralwallpapers.fragments.ThirdFragment;

/**
 * Created by CarlosAB on 24/08/2017.
 */

public class MainPagerActivity extends FragmentActivity {

    private GenericWallpaper genericWallpaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_main);
        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setTintColor(Color.parseColor("#05000000"));
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            System.out.println(pos);
            switch(pos) {
                case 0: return FirstFragment.newInstance("FirstFragment, Instance 1");
                case 1: return SecondFragment.newInstance("SecondFragment, Instance 1");
                case 2: return ThirdFragment.newInstance("ThirdFragment, Instance 1");
                case 3: return ThirdFragment.newInstance("ThirdFragment, Instance 2");
                case 4: return ThirdFragment.newInstance("ThirdFragment, Instance 3");
                default: return ThirdFragment.newInstance("ThirdFragment, Default");
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
