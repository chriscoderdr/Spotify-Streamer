package me.cristiangomez.spotifystreamer.app.activity.listener;

import kaaes.spotify.webapi.android.models.Tracks;
import kaaes.spotify.webapi.android.models.TracksPager;

/**
 * Created by cristian on 07/06/15.
 */
public interface ArtistTopTracksFetcherListener {
    void onArtistTopTracksFetched(Tracks tracks);
}
