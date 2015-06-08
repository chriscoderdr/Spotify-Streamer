package me.cristiangomez.spotifystreamer.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import me.cristiangomez.spotifystreamer.R;
import me.cristiangomez.spotifystreamer.app.activity.fragment.ArtistTopTracksFragment;

/**
 * Created by cristian on 07/06/15.
 */
public class ArtistTopTracksActivity extends BaseActivity {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.a_artist_top_tracks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.a_artist_top_tracks_menu_setting:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
