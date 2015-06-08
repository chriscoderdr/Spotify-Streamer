package me.cristiangomez.spotifystreamer.app.net;

import android.os.AsyncTask;
import android.util.Log;

import java.util.HashMap;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import kaaes.spotify.webapi.android.models.Tracks;
import kaaes.spotify.webapi.android.models.TracksPager;
import me.cristiangomez.spotifystreamer.app.activity.listener.ArtistTopTracksFetcherListener;
import me.cristiangomez.spotifystreamer.app.activity.listener.ArtistsFetcherListener;
import me.cristiangomez.spotifystreamer.app.pojo.ArtistSearch;
import me.cristiangomez.spotifystreamer.app.pojo.ArtistTopTracksQuery;
import retrofit.RetrofitError;

/**
 * Created by cristian on 07/06/15.
 */
public class FetchArtistTopTracksTasks extends AsyncTask<ArtistTopTracksQuery, Void, Tracks> {
    //========================================================
    //FIELDS
    //========================================================
    private SpotifyApi mSpotifyApi = new SpotifyApi();
    private SpotifyService mSpotifyService = mSpotifyApi.getService();
    private ArtistTopTracksFetcherListener mListener;
    private static final String cLOG_TAG = FetchArtistTopTracksTasks.class.getSimpleName();
    private static final int cTRACKS_LIMIT = 10;
    //========================================================
    //CONSTRUCTORS
    //========================================================
    public FetchArtistTopTracksTasks(ArtistTopTracksFetcherListener listener) {
        mListener = listener;
    }
    //========================================================
    //OVERRIDDEN METHODS
    //========================================================

    @Override
    protected Tracks doInBackground(ArtistTopTracksQuery... params) {
        HashMap<String, Object> values = new HashMap<>();
        values.put(SpotifyService.COUNTRY, params[0].getCountry());
        values.put(SpotifyService.OFFSET, 0);
        values.put(SpotifyService.LIMIT, cTRACKS_LIMIT);
        try {
            return mSpotifyService.getArtistTopTrack(params[0].getArtistId(), values);
        } catch (RetrofitError e) {
            Log.d(cLOG_TAG, "error downloading artists", e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Tracks tracks) {
        super.onPostExecute(tracks);
        mListener.onArtistTopTracksFetched(tracks);
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
