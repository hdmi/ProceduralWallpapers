package imdh.tfm.proceduralwallpapers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by CarlosAB on 09/08/2017.
 */

public class PalettesDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.palettes_display_layout);

        RecyclerView recyclerViewPalettes = (RecyclerView) findViewById(R.id.rvPalettes);

        PalettesAdapter adapter = new PalettesAdapter(this, PaletteDatabase.getInstance(getApplicationContext()).getPalettes());
        recyclerViewPalettes.setAdapter(adapter);
        recyclerViewPalettes.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewPalettes.setHasFixedSize(true);

    }



}
