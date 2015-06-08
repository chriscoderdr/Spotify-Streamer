package me.cristiangomez.spotifystreamer.app;

import android.app.Application;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;

/**
 * Created by cristian on 08/06/15.
 */
public class SpotifyStreamer extends Application {
    //========================================================
    //FIELDS
    //========================================================
    private static SpotifyStreamer sInstance;
    private SpotifyApi mSpotifyApi;
    private SpotifyService mSpotifyService;
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
        initialize();
    }

    //========================================================
    //METHODS
    //========================================================

    public static SpotifyStreamer getInstance() {
        if (sInstance == null) {
            sInstance = new SpotifyStreamer();
            sInstance.initialize();
        }
        return sInstance;
    }

    private void initialize() {
        mSpotifyApi = new SpotifyApi();
        mSpotifyService = mSpotifyApi.getService();
    }

    //========================================================
    //INNER CLASSES
    //========================================================

    //========================================================
    //GETTERS AND SETTERS
    //========================================================
    public SpotifyService getSpotifyService() {
        return mSpotifyService;
    }
}
