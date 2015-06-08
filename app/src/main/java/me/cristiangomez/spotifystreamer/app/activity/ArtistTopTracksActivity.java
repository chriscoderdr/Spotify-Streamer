package me.cristiangomez.spotifystreamer.app.activity;

import android.os.Bundle;

import me.cristiangomez.spotifystreamer.R;
import me.cristiangomez.spotifystreamer.app.activity.fragment.ArtistTopTracksFragment;
import me.cristiangomez.spotifystreamer.app.activity.fragment.SearchArtistFragment;

/**
 * Created by cristian on 07/06/15.
 */
public class ArtistTopTracksActivity extends Base {
    //========================================================
    //FIELDS
    //========================================================
    public static final String cARGS_ARTIST_ID = "ARTIST_ID";
    private String mArtistId;
    //========================================================
    //CONSTRUCTORS
    //========================================================

    //========================================================
    //OVERRIDDEN METHODS
    //========================================================

    @Override
    protected int getLayoutResource() {
        return R.layout.a_artist_top_tracks;
    }

    @Override
    protected void initialize(Bundle savedInstance) {
        super.initialize(savedInstance);
        Bundle args = getIntent().getExtras();
        mArtistId = args.getString(cARGS_ARTIST_ID);
        if (savedInstance == null) {
            showSearchArtistFragment();
        }
    }

    //========================================================
    //METHODS
    //========================================================
    public void showSearchArtistFragment() {
        ArtistTopTracksFragment fragment = new ArtistTopTracksFragment();
        Bundle args = new Bundle();
        args.putString(ArtistTopTracksFragment.cARGS_ARTIST_ID, mArtistId);
        fragment.setArguments(args);
        mFragmentManager.beginTransaction().add(R.id.a_artist_top_tracks_container,
                fragment).commit();
    }
    //========================================================
    //INNER CLASSES
    //========================================================

    //========================================================
    //GETTERS AND SETTERS
    //========================================================
}
