package me.cristiangomez.spotifystreamer.app.activity;

import android.os.Bundle;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import me.cristiangomez.spotifystreamer.R;
import me.cristiangomez.spotifystreamer.app.activity.fragment.SearchArtistFragment;

/**
 * Created by cristian on 07/06/15.
 */
public class SearchArtistActivity extends Base {
    //========================================================
    //FIELDS
    //========================================================
    private SpotifyApi spotifyApi = new SpotifyApi();
    private SpotifyService mSpotifyService = spotifyApi.getService();
    //========================================================
    //CONSTRUCTORS
    //========================================================

    //========================================================
    //OVERRIDDEN METHODS
    //========================================================

    @Override
    protected int getLayoutResource() {
        return R.layout.a_search_artist;
    }

    @Override
    protected void initialize(Bundle savedInstance) {
        super.initialize(savedInstance);
        if (savedInstance == null) {
            showSearchArtistFragment();
        }
    }


    //========================================================
    //METHODS
    //========================================================
    public void showSearchArtistFragment() {
        SearchArtistFragment fragment = new SearchArtistFragment();
        mFragmentManager.beginTransaction().add(R.id.a_search_artist_container,
                fragment).commit();
    }
    //========================================================
    //INNER CLASSES
    //========================================================

    //========================================================
    //GETTERS AND SETTERS
    //========================================================
}
