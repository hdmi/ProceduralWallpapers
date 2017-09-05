package imdh.tfm.proceduralwallpapers.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import imdh.tfm.proceduralwallpapers.R;

/**
 * Created by CarlosAB on 13/12/2016.
 */

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInsanceState){
        super.onCreate(savedInsanceState);
        getSupportActionBar().hide();
        showSkipButton(false);

        addSlide(AppIntroFragment.newInstance(getString(R.string.Slide1_title), getString(R.string.Slide1_description), R.mipmap.escaner_de_bolsillo, getResources().getColor(R.color.colorPrimaryDark)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.Slide2_title), getString(R.string.Slide2_description), R.mipmap.camera_intro, getResources().getColor(R.color.colorPrimaryDark)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.Slide3_title), getString(R.string.Slide3_description), R.mipmap.documentos, 0xFF6050));
        addSlide(AppIntroFragment.newInstance(getString(R.string.Slide4_title), getString(R.string.Slide4_description), R.mipmap.edit_2, getResources().getColor(R.color.colorPrimaryDark)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.Slide5_title), getString(R.string.Slide5_description), R.mipmap.grid_view, getResources().getColor(R.color.colorPrimaryDark)));
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}
