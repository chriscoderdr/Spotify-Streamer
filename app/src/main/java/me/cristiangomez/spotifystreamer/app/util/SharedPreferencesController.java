package me.cristiangomez.spotifystreamer.app.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import me.cristiangomez.spotifystreamer.R;
import me.cristiangomez.spotifystreamer.app.SpotifyStreamer;

/**
 * Created by cristian on 08/06/15.
 */
public class SharedPreferencesController {
    //========================================================
    //FIELDS
    //========================================================
    private static SharedPreferencesController sInstance;
    private SharedPreferences mSharedPreferences;
    private Context mContext;
    private final String cCOUNTRY_KEY;
    private final String cDEFAULT_COUNTRY;
    //========================================================
    //CONSTRUCTORS
    //========================================================
    private SharedPreferencesController() {
        mContext = SpotifyStreamer.getInstance();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        cCOUNTRY_KEY = mContext.getString(R.string.preferences_country_key);
        cDEFAULT_COUNTRY = mContext.getString(R.string.default_country);
    }
    //========================================================
    //OVERRIDDEN METHODS
    //========================================================

    //========================================================
    //METHODS
    //========================================================
    public static SharedPreferencesController getsInstance() {
        if (sInstance == null) {
            sInstance = new SharedPreferencesController();
        }
        return sInstance;
    }
    //========================================================
    //INNER CLASSES
    //========================================================

    //========================================================
    //GETTERS AND SETTERS
    //========================================================
    public String getCountry() {
        return mSharedPreferences.getString(cCOUNTRY_KEY, cDEFAULT_COUNTRY);
    }
}
