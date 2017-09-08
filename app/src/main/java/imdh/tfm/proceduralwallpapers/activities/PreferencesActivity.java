package imdh.tfm.proceduralwallpapers.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import imdh.tfm.proceduralwallpapers.R;

/**
 * Created by CarlosAB on 08/09/2017.
 */

public class PreferencesActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
