package me.cristiangomez.spotifystreamer.app.activity.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;
import me.cristiangomez.spotifystreamer.R;
import me.cristiangomez.spotifystreamer.app.activity.listener.ArtistTopTracksFetcherListener;
import me.cristiangomez.spotifystreamer.app.net.FetchArtistTopTracksTasks;
import me.cristiangomez.spotifystreamer.app.pojo.ArtistTopTracksQuery;
import me.cristiangomez.spotifystreamer.app.ui.adapter.TrackAdapter;
import me.cristiangomez.spotifystreamer.app.util.SharedPreferencesController;

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
    private static final String cINDEX = "index";
    private static final String cTOP_POSITION = "top_position";
    private static final String cTRACK_LISTS = "track_lists";
    private List<Track> mTrackList;
    private int mIndex;
    private int mTop;
    private Gson mGson;
    private SharedPreferencesController mPreferencesController;
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
        mTracksListView = (ListView) view.findViewById(R.id.f_artist_top_tracks_lv_tracks);
        if (mTrackList == null) {
            ArtistTopTracksQuery query = new ArtistTopTracksQuery(mArtistId, mPreferencesController.getCountry());
            new FetchArtistTopTracksTasks(new ArtistTopTracksFetcherListener() {
                @Override
                public void onArtistTopTracksFetched(Tracks tracks) {
                    mTracksAdapter = new TrackAdapter(getActivity(), tracks.tracks);
                    mTracksListView.setAdapter(mTracksAdapter);
                    mTracksAdapter.addAll(tracks.tracks);
                    mTrackList = tracks.tracks;
                }
            }).execute(query);
        }
        else {
            mTracksAdapter = new TrackAdapter(getActivity(), mTrackList);
            mTracksListView.setAdapter(mTracksAdapter);
        }
        if (mIndex != 0 || mTop != 0) {
            mTracksListView.setSelectionFromTop(mIndex, mTop);
        }
    }

    @Override
    protected void initialize(Bundle savedInstance) {
        super.initialize(savedInstance);
        mGson = new GsonBuilder().create();
        Bundle args = getArguments();
        mArtistId = args.getString(cARGS_ARTIST_ID);
        if (savedInstance != null) {
            String trackListJson = savedInstance.getString(cTRACK_LISTS);
            Type trackListType = new TypeToken<List<Track>>() {}.getType();
            mTrackList = mGson.fromJson(trackListJson, trackListType);
            mIndex = savedInstance.getInt(cINDEX);
            mTop = savedInstance.getInt(cTOP_POSITION);
        }
        mPreferencesController = SharedPreferencesController.getsInstance();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int index = mTracksListView.getFirstVisiblePosition();
        View v = mTracksListView.getChildAt(0);
        int top = (v == null) ? 0 : (v.getTop() - mTracksListView.getPaddingTop());
        outState.putInt(cINDEX, index);
        outState.putInt(cTOP_POSITION, top);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Type trackListType = new TypeToken<List<Track>>() {}.getType();
        Gson gson = gsonBuilder.create();
        String trackLists = gson.toJson(mTrackList, trackListType);
        outState.putString(cTRACK_LISTS, trackLists);
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
