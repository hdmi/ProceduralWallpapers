package imdh.tfm.proceduralwallpapers.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by CarlosAB on 02/09/2017.
 */

public class SplashActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainPagerActivity.class);
        startActivity(intent);
        finish();
    }
}
