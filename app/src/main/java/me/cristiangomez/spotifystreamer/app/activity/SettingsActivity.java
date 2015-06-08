package me.cristiangomez.spotifystreamer.app.activity;

import android.os.Bundle;

import me.cristiangomez.spotifystreamer.R;
import me.cristiangomez.spotifystreamer.app.activity.fragment.SettingsFragment;

/**
 * Created by cristian on 08/06/15.
 */
public class SettingsActivity extends BaseActivity {
    //========================================================
    //FIELDS
    //========================================================

    //========================================================
    //CONSTRUCTORS
    //========================================================

    //========================================================
    //OVERRIDDEN METHODS
    //========================================================

    @Override
    protected int getLayoutResource() {
        return R.layout.a_settings;
    }

    @Override
    protected void initialize(Bundle savedInstance) {
        super.initialize(savedInstance);
        if (savedInstance == null) {
            showSettingsFragment();
        }
    }

    //========================================================
    //METHODS
    //========================================================
    private void showSettingsFragment() {
        mFragmentManager.beginTransaction().add(R.id.a_settings_container,
                new SettingsFragment()).commit();
    }
    //========================================================
    //INNER CLASSES
    //========================================================

    //========================================================
    //GETTERS AND SETTERS
    //========================================================
}
