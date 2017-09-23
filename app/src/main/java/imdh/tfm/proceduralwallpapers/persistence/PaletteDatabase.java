package imdh.tfm.proceduralwallpapers.persistence;

import android.content.Context;

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
import java.util.List;
import java.util.Random;

import imdh.tfm.proceduralwallpapers.utils.Constants;
import imdh.tfm.proceduralwallpapers.dataitems.Palette;

/**
 * Created by CarlosAB on 06/08/2017.
 */

public class PaletteDatabase {

    private static List<Palette> palettes;
    private static Context context;
    private static PaletteDatabase singleton = null;
    private static Random random;

    private PaletteDatabase(Context context) {
        this.context = context;
        this.palettes = new ArrayList<Palette>();
        this.random = new Random();
        populatePalettes();
    }

    public static PaletteDatabase getInstance(Context context) {
        if(singleton == null){
            singleton = new PaletteDatabase(context);
        }
        return singleton;
    }

    private void populatePalettes(){
        try {
            JSONArray jsonArray =  new JSONArray(openJSONPalettes(Constants.PALETTES_SAMPLE_500));
            System.out.println(jsonArray.getJSONArray(0));
            for(int i = 0; i < jsonArray.length(); i++){
                JSONArray palette = jsonArray.getJSONArray(i);
                palettes.add(new Palette(palette.getString(0), palette.getString(1), palette.getString(2), palette.getString(3), palette.getString(4)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String openJSONPalettes(int sample){
        //TODO: check whether the int sample is valid
        InputStream is = context.getResources().openRawResource(sample);
        if(is == null) is = context.getResources().openRawResource(Constants.PALETTES_SAMPLE_100);
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

    public List<Palette> getPalettesList(){return palettes;}

    public Palette getRandomPalette(){return palettes.get(random.nextInt(palettes.size()));}

}
