package imdh.tfm.proceduralwallpapers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by CarlosAB on 09/08/2017.
 */

public class PalettesDisplayActivity extends AppCompatActivity {

    ArrayList<Palette> palettes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.palettes_display_layout);
        palettes = new ArrayList<>();

        RecyclerView recyclerViewPalettes = (RecyclerView) findViewById(R.id.rvPalettes);
        populatePalettes();

        PalettesAdapter adapter = new PalettesAdapter(this, palettes);
        recyclerViewPalettes.setAdapter(adapter);
        recyclerViewPalettes.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewPalettes.setHasFixedSize(true);

    }

    public void populatePalettes(){

        try {
            JSONArray jsonArray =  new JSONArray(openJSONPalettes(Constants.PALETTES_SAMPLE_200));
            System.out.println(jsonArray.getJSONArray(0));
            for(int i = 0; i < jsonArray.length(); i++){
                JSONArray palette = jsonArray.getJSONArray(i);
                palettes.add(new Palette(palette.getString(0), palette.getString(1), palette.getString(2), palette.getString(3), palette.getString(4)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String openJSONPalettes(int sample){
        //TODO: check whether the int sample is valid
        InputStream is = getResources().openRawResource(sample);
        if(is == null) is = getResources().openRawResource(Constants.PALETTES_SAMPLE_100);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return writer.toString();
    }

}
