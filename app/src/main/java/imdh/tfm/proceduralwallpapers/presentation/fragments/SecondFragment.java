package imdh.tfm.proceduralwallpapers.presentation.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import imdh.tfm.proceduralwallpapers.persistence.PaletteDatabase;
import imdh.tfm.proceduralwallpapers.R;
import imdh.tfm.proceduralwallpapers.adapters.PalettesAdapter;
import imdh.tfm.proceduralwallpapers.dataitems.Palette;

/**
 * Created by CarlosAB on 24/08/2017.
 */

public class SecondFragment extends Fragment implements PalettesAdapter.PalettesAdapterListener{

    OnPaletteSelectedListener mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.second_fragment, container, false);
        RecyclerView recyclerViewPalettes = (RecyclerView) v.findViewById(R.id.rvPalettes);

        PalettesAdapter adapter = new PalettesAdapter((AppCompatActivity) getActivity(), PaletteDatabase.getInstance(getActivity().getApplicationContext()).getPalettesList());
        recyclerViewPalettes.setAdapter(adapter);
        recyclerViewPalettes.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setPalettesAdapterListenerCallback(this);

        recyclerViewPalettes.setHasFixedSize(true);
        return v;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try{
            mCallback = (OnPaletteSelectedListener) activity;
        }catch (ClassCastException cce){
            throw new ClassCastException(activity.toString() + " must implement OnPaletteSelectedListener");
        }
    }

    public static SecondFragment newInstance() {
        SecondFragment f = new SecondFragment();
        return f;
    }

    @Override
    public void onPaletteSelected(Palette palette) {
        mCallback.onPaletteSelected(palette);
    }

    public interface OnPaletteSelectedListener {
        void onPaletteSelected(Palette palette);
    }
}
