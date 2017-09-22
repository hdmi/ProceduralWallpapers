package imdh.tfm.proceduralwallpapers.presentation.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import imdh.tfm.proceduralwallpapers.App;
import imdh.tfm.proceduralwallpapers.R;

/**
 * Created by CarlosAB on 08/09/2017.
 */

public class PreferencesActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            switch (key){
                case "prefSyncFrequency":
                    ((App) getApplication()).preferencesChanged();
                    break;
            }
    }
}
