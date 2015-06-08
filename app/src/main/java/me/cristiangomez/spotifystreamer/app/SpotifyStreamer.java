package me.cristiangomez.spotifystreamer.app;

import android.app.Application;

/**
 * Created by cristian on 08/06/15.
 */
public class SpotifyStreamer extends Application {
    //========================================================
    //FIELDS
    //========================================================
    private static SpotifyStreamer sInstance;
    //========================================================
    //CONSTRUCTORS
    //========================================================

    //========================================================
    //OVERRIDDEN METHODS
    //========================================================

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    //========================================================
    //METHODS
    //========================================================

    public static SpotifyStreamer getInstance() {
        if (sInstance == null) {
            sInstance = new SpotifyStreamer();
        }
        return sInstance;
    }

    //========================================================
    //INNER CLASSES
    //========================================================

    //========================================================
    //GETTERS AND SETTERS
    //========================================================
}
