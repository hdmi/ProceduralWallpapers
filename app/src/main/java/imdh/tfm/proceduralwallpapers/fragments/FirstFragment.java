package imdh.tfm.proceduralwallpapers.fragments;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import imdh.tfm.proceduralwallpapers.App;
import imdh.tfm.proceduralwallpapers.R;
import imdh.tfm.proceduralwallpapers.activities.MainPagerActivity;
import imdh.tfm.proceduralwallpapers.models.Palette;
import imdh.tfm.proceduralwallpapers.utils.BitmapStorageExport;
import imdh.tfm.proceduralwallpapers.utils.UtilsWallpaper;
import imdh.tfm.proceduralwallpapers.wallpapers.GenericWallpaper;
import imdh.tfm.proceduralwallpapers.wallpapers.RandomWallpaper;

import static imdh.tfm.proceduralwallpapers.Constants.ANIMATION_DURATION_1_SEC;

/**
 * Created by CarlosAB on 24/08/2017.
 */

public class FirstFragment extends Fragment {

    private ImageButton imageButton0;
    private ImageButton imageButton1;
    private ImageButton imageButton2;

    private ImageView wallpaperImageView;

    private boolean buttonsVisibility;

    private GenericWallpaper currentWallpaper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.first_fragment, container, false);

        imageButton0 = (ImageButton) v.findViewById(R.id.imageButton0);
        imageButton1 = (ImageButton) v.findViewById(R.id.imageButton1);
        imageButton2 = (ImageButton) v.findViewById(R.id.imageButton2);

        wallpaperImageView = (ImageView) v.findViewById(R.id.wallpaperImageView);

        wallpaperImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        setWallpaper(drawNewWallpaper(null));

        buttonsVisibility = true;
        wallpaperImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonsVisibility){

                    imageButton0
                            .animate()
                            .translationY(imageButton0.getHeight()*2)
                            .setDuration(ANIMATION_DURATION_1_SEC)
                            .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            imageButton0.setVisibility(View.GONE);
                        }
                        });
                    imageButton1
                            .animate()
                            .translationY(imageButton1.getHeight()*2)
                            .setDuration(ANIMATION_DURATION_1_SEC)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    imageButton1.setVisibility(View.GONE);
                                }
                            });
                    imageButton2
                            .animate()
                            .translationY(imageButton2.getHeight()*2)
                            .setDuration(ANIMATION_DURATION_1_SEC)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    imageButton2.setVisibility(View.GONE);
                                }
                            });
                }
                else{
                    imageButton0
                            .animate()
                            .translationY(0)
                            .setDuration(ANIMATION_DURATION_1_SEC)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    imageButton0.setVisibility(View.VISIBLE);
                                }
                            });
                    imageButton1
                            .animate()
                            .translationY(0)
                            .setDuration(ANIMATION_DURATION_1_SEC)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    imageButton1.setVisibility(View.VISIBLE);
                                }
                            });
                    imageButton2
                            .animate()
                            .translationY(0)
                            .setDuration(ANIMATION_DURATION_1_SEC)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    imageButton2.setVisibility(View.VISIBLE);
                                }
                            });
                }
                buttonsVisibility = !buttonsVisibility;
            }
        });

        imageButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWallpaperWithPalette(null);
            }
        });

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilsWallpaper.setWallpaper2Desktop(currentWallpaper.getBitmap(), getActivity().getApplicationContext());
                Toast.makeText(getActivity().getApplicationContext(), R.string.wallpaper_set, Toast.LENGTH_SHORT).show();
                getActivity().finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainPagerActivity) getActivity()).askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if(((MainPagerActivity) getActivity()).doIHavePermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    String filePath2Save = ((App) getActivity().getApplication()).getIMAGES_PATH();
                    new BitmapStorageExport(currentWallpaper.getBitmap(), getActivity().findViewById(android.R.id.content), filePath2Save).execute();
                }
            }
        });
        return v;
    }

    public static FirstFragment newInstance() {
        FirstFragment f = new FirstFragment();
        return f;
    }

    private GenericWallpaper drawNewWallpaper(Palette palette) {
        RandomWallpaper randomWallpaper = new RandomWallpaper(palette, getContext().getApplicationContext());
        currentWallpaper = randomWallpaper;
        return randomWallpaper;
    }

    public void setWallpaperWithPalette(Palette palette){
        setWallpaper(drawNewWallpaper(palette));
    }

    public void setThisWallpaper(GenericWallpaper genericWallpaper) {
        setWallpaper(genericWallpaper);
    }
    private void setWallpaper(GenericWallpaper genericWallpaper){
        currentWallpaper = genericWallpaper;
        wallpaperImageView.setImageBitmap(currentWallpaper.getBitmap());
    }
}
