package imdh.tfm.proceduralwallpapers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import imdh.tfm.proceduralwallpapers.R;
import imdh.tfm.proceduralwallpapers.adapters.SavedWallpapersAdapter;

/**
 * Created by CarlosAB on 24/08/2017.
 */

public class ThirdFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.third_fragment, container, false);

        GridView gridview = (GridView) v.findViewById(R.id.grid_gallery);
        gridview.setAdapter(new SavedWallpapersAdapter(getActivity()));

        getActivity().setTitle("Mi Galería");

        return v;
    }

    public static ThirdFragment newInstance() {
        ThirdFragment f = new ThirdFragment();
        return f;
    }
}
