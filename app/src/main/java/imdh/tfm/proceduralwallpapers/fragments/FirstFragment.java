package imdh.tfm.proceduralwallpapers.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import imdh.tfm.proceduralwallpapers.Palette;
import imdh.tfm.proceduralwallpapers.R;
import imdh.tfm.proceduralwallpapers.RandomWallpaper;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.first_fragment, container, false);

//        TextView tv = (TextView) v.findViewById(R.id.tvFragFirst);
//        tv.setText(getArguments().getString("msg"));

        imageButton0 = (ImageButton) v.findViewById(R.id.imageButton0);
        imageButton1 = (ImageButton) v.findViewById(R.id.imageButton1);
        imageButton2 = (ImageButton) v.findViewById(R.id.imageButton2);

        wallpaperImageView = (ImageView) v.findViewById(R.id.wallpaperImageView);

        wallpaperImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        wallpaperImageView.setImageBitmap(drawCurrentWallpaper(null));

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

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wallpaperImageView.setImageBitmap(drawCurrentWallpaper(null));
            }
        });

        return v;
    }

    public static FirstFragment newInstance(String text) {
        FirstFragment f = new FirstFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }

    private Bitmap drawCurrentWallpaper(Palette palette) {
        RandomWallpaper primerWallpaper = new RandomWallpaper(palette, getContext().getApplicationContext());
        return primerWallpaper.getBitmap();
    }
}
