package me.cristiangomez.spotifystreamer.app.activity.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import kaaes.spotify.webapi.android.models.Tracks;
import me.cristiangomez.spotifystreamer.R;
import me.cristiangomez.spotifystreamer.app.activity.listener.ArtistTopTracksFetcherListener;
import me.cristiangomez.spotifystreamer.app.net.FetchArtistTopTracksTasks;
import me.cristiangomez.spotifystreamer.app.pojo.ArtistTopTracksQuery;
import me.cristiangomez.spotifystreamer.app.ui.adapter.TrackAdapter;

/**
 * Created by cristian on 07/06/15.
 */
public class ArtistTopTracksFragment extends Base {
    //========================================================
    //FIELDS
    //========================================================
    private TrackAdapter mTracksAdapter;
    private ListView mTracksListView;
    private String mArtistId;
    public static final String cARGS_ARTIST_ID = "ARTIST_ID";
    //========================================================
    //CONSTRUCTORS
    //========================================================

    //========================================================
    //OVERRIDDEN METHODS
    //========================================================

    @Override
    protected int getLayoutResource() {
        return R.layout.f_artist_top_tracks;
    }

    @Override
    protected void initializeView(View view) {
        mTracksAdapter = new TrackAdapter(getActivity());
        mTracksListView = (ListView) view.findViewById(R.id.f_artist_top_tracks_lv_tracks);
        mTracksListView.setAdapter(mTracksAdapter);
        ArtistTopTracksQuery query = new ArtistTopTracksQuery(mArtistId, "US");
        new FetchArtistTopTracksTasks(new ArtistTopTracksFetcherListener() {
            @Override
            public void onArtistTopTracksFetched(Tracks tracks) {
                mTracksAdapter.addAll(tracks.tracks);
            }
        }).execute(query);
    }

    @Override
    protected void initialize() {
        super.initialize();
        Bundle args = getArguments();
        mArtistId = args.getString(cARGS_ARTIST_ID);
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
