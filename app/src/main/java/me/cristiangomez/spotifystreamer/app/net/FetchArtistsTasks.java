package me.cristiangomez.spotifystreamer.app.net;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.util.HashMap;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import kaaes.spotify.webapi.android.models.Pager;
import me.cristiangomez.spotifystreamer.app.activity.listener.ArtistsFetcherListener;
import me.cristiangomez.spotifystreamer.app.pojo.ArtistSearch;
import retrofit.RetrofitError;

/**
 * Created by cristian on 07/06/15.
 */
public class FetchArtistsTasks extends AsyncTask<ArtistSearch, Void, ArtistsPager> {
    //========================================================
    //FIELDS
    //========================================================
    private SpotifyApi mSpotifyApi = new SpotifyApi();
    private SpotifyService mSpotifyService = mSpotifyApi.getService();
    private ArtistsFetcherListener mListener;
    private static final String cLOG_TAG = FetchArtistsTasks.class.getSimpleName();
    //========================================================
    //CONSTRUCTORS
    //========================================================
    public FetchArtistsTasks(ArtistsFetcherListener listener) {
        mListener = listener;
    }
    //========================================================
    //OVERRIDDEN METHODS
    //========================================================

    @Override
    protected ArtistsPager doInBackground(ArtistSearch... params) {
        Log.d(cLOG_TAG, "offset"+params[0].getOffset());
        HashMap<String, Object> values = new HashMap<>();
        values.put(SpotifyService.OFFSET, params[0].getOffset());
        values.put(SpotifyService.LIMIT, params[0].getLimit());
        try {
            return mSpotifyService.searchArtists(params[0].getQuery(), values);
        } catch (RetrofitError e) {
            Log.d(cLOG_TAG, "error downloading artists", e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArtistsPager artistsPager) {
        super.onPostExecute(artistsPager);
        mListener.onArtistsFetched(artistsPager);
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
