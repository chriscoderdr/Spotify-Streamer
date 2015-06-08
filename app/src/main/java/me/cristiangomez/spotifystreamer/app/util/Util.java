package me.cristiangomez.spotifystreamer.app.util;

import java.util.HashMap;

import kaaes.spotify.webapi.android.SpotifyService;

/**
 * Created by cristian on 08/06/15.
 */
public class Util {
    //========================================================
    //FIELDS
    //========================================================

    //========================================================
    //CONSTRUCTORS
    //========================================================

    //========================================================
    //OVERRIDDEN METHODS
    //========================================================

    //========================================================
    //METHODS
    //========================================================
    public static HashMap<String, Object> buildSpotifyParams(int limit, int offset) {
        HashMap<String, Object> params = new HashMap<>();
        params.put(SpotifyService.LIMIT, limit);
        params.put(SpotifyService.OFFSET, offset);
        return params;
    }

    public static HashMap<String, Object> buildSpotifyParams(int limit, int offset, String country) {
        HashMap<String, Object> params = new HashMap<>();
        params.put(SpotifyService.LIMIT, limit);
        params.put(SpotifyService.OFFSET, offset);
        params.put(SpotifyService.COUNTRY, country);
        return params;
    }
    //========================================================
    //INNER CLASSES
    //========================================================

    //========================================================
    //GETTERS AND SETTERS
    //========================================================
}
