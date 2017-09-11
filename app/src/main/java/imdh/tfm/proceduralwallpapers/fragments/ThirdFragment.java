package imdh.tfm.proceduralwallpapers.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import imdh.tfm.proceduralwallpapers.R;
import imdh.tfm.proceduralwallpapers.adapters.GalleryWallpapersAdapter;
import imdh.tfm.proceduralwallpapers.wallpapers.GenericWallpaper;

/**
 * Created by CarlosAB on 24/08/2017.
 */

public class ThirdFragment extends Fragment implements GalleryWallpapersAdapter.GalleryWallpapersAdapterListener {

    WallpaperSelectedListener wallpaperSelectedListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.third_fragment, container, false);

        GridView gridview = (GridView) v.findViewById(R.id.grid_gallery);
        GalleryWallpapersAdapter galleryWallpapersAdapter= new GalleryWallpapersAdapter(getActivity());
        gridview.setAdapter(galleryWallpapersAdapter);
        galleryWallpapersAdapter.setGalleryWallpapersAdapterListener(this);

        if(gridview.getCount() <= 0){
            gridview.setBackground(getResources().getDrawable(R.drawable.gallery_background));
        }
        else{
            gridview.setBackgroundDrawable(null);
        }
        return v;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try{
            wallpaperSelectedListener = (WallpaperSelectedListener) activity;
        }catch (ClassCastException cce){
            throw new ClassCastException(activity.toString() + " must implement WallpaperSelectedListener");
        }
    }

    public static ThirdFragment newInstance() {
        ThirdFragment f = new ThirdFragment();
        return f;
    }

    @Override
    public void onWallpaperSelectedInAdapter(GenericWallpaper genericWallpaper) {
        if (wallpaperSelectedListener != null) {
            wallpaperSelectedListener.onWallpaperSelected(genericWallpaper);
        }
    }

    public interface WallpaperSelectedListener{
        void onWallpaperSelected(GenericWallpaper genericWallpaper);
    }
}
