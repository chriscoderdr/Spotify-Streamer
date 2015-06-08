package me.cristiangomez.spotifystreamer.app.activity.listener;

import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import kaaes.spotify.webapi.android.models.Pager;

/**
 * Created by cristian on 07/06/15.
 */
public interface ArtistsFetcherListener {
    void onArtistsFetched(ArtistsPager artistsPager);
}
