package imdh.tfm.proceduralwallpapers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import imdh.tfm.proceduralwallpapers.PaletteDatabase;
import imdh.tfm.proceduralwallpapers.PalettesAdapter;
import imdh.tfm.proceduralwallpapers.R;

/**
 * Created by CarlosAB on 24/08/2017.
 */

public class SecondFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.second_fragment, container, false);
        RecyclerView recyclerViewPalettes = (RecyclerView) v.findViewById(R.id.rvPalettes);

        PalettesAdapter adapter = new PalettesAdapter((AppCompatActivity) getActivity(), PaletteDatabase.getInstance(getActivity().getApplicationContext()).getPalettes());
        recyclerViewPalettes.setAdapter(adapter);
        recyclerViewPalettes.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerViewPalettes.setHasFixedSize(true);
        return v;
    }

    public static SecondFragment newInstance(String text) {
        SecondFragment f = new SecondFragment();
        return f;
    }
}
