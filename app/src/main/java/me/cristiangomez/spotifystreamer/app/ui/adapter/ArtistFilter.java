package me.cristiangomez.spotifystreamer.app.ui.adapter;

import android.widget.Filter;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.ArtistsPager;

/**
 * Created by cristian on 07/06/15.
 */
public class ArtistFilter extends Filter {
    //========================================================
    //FIELDS
    //========================================================
    private SpotifyApi mSpotifyApi = new SpotifyApi();
    private SpotifyService mSpotifyService = mSpotifyApi.getService();
    private ArtistAdapter mAdapter;
    //========================================================
    //CONSTRUCTORS
    //========================================================
    public ArtistFilter(ArtistAdapter adapter) {
        mAdapter = adapter;
    }
    //========================================================
    //OVERRIDDEN METHODS
    //========================================================

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults results = new FilterResults();
        if (!charSequence.toString().isEmpty()) {
            ArtistsPager pager = mSpotifyService.searchArtists(charSequence.toString());
            results.values = pager;
            results.count = pager.artists.items.size();
        }
        else {
            results.values = new ArrayList<>();
            results.count = 0;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults results) {
        if (results.values instanceof  ArtistsPager) {
            ArtistsPager pager = (ArtistsPager) results.values;
            if (pager != null) {
                mAdapter.setPager((ArtistsPager) results.values, true);
            }
        }
        else {
            mAdapter.clear();
            mAdapter.notifyDataSetChanged();
        }
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
