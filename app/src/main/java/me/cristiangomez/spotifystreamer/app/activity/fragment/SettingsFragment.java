package me.cristiangomez.spotifystreamer.app.activity.fragment;

import android.os.Bundle;

import com.github.machinarius.preferencefragment.PreferenceFragment;

import me.cristiangomez.spotifystreamer.R;

/**
 * Created by cristian on 08/06/15.
 */
public class SettingsFragment extends PreferenceFragment {
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefrence_general);
    }

    //========================================================
    //METHODS
    //========================================================

    //========================================================
    //INNER CLASSES
    //========================================================

    //========================================================
    //GETTERS AND SETTERS
    //========================================================
}
