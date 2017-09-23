package imdh.tfm.proceduralwallpapers.presentation.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;

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

        Preference presentation_trigger = findPreference("presentation_trigger");
        presentation_trigger.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                Intent i = new Intent(PreferencesActivity.this, IntroActivity.class);
                PreferencesActivity.this.startActivity(i);
                return true;
            }
        });

        Preference about_libraries_trigger = findPreference("about_libraries_trigger");
        about_libraries_trigger.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                new LibsBuilder()
                        .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                        .withActivityTitle(getResources().getString(R.string.about_title))
                        .withAboutIconShown(true)
                        .withAboutVersionShown(true)
                        .withAboutDescription(getResources().getString(R.string.about_description))
                        .withAutoDetect(true)
                        .start(PreferencesActivity.this);
                return true;
            }
        });
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
