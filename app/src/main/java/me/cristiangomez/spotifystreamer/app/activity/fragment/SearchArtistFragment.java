package me.cristiangomez.spotifystreamer.app.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import butterknife.InjectView;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import me.cristiangomez.spotifystreamer.R;
import me.cristiangomez.spotifystreamer.app.SpotifyStreamer;
import me.cristiangomez.spotifystreamer.app.activity.listener.OnArtistSelectedListener;
import me.cristiangomez.spotifystreamer.app.ui.adapter.ArtistAdapter;
import me.cristiangomez.spotifystreamer.app.activity.listener.OnEndlessScrollListener;
import me.cristiangomez.spotifystreamer.app.util.Util;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by cristian on 07/06/15.
 */
public class SearchArtistFragment extends BaseFragment {
    //========================================================
    //FIELDS
    //========================================================
    @InjectView(R.id.f_search_artist_lv_results)
    ListView mResultsListView;
    private ArtistAdapter mArtistAdapter;
    @InjectView(R.id.f_search_artist_sv_artist)
    SearchView mSearch;
    private static final String cARTIST_PAGER = "ARTIST_PAGER";
    private static final String cLOG_TAG = SearchArtistFragment.class.getSimpleName();
    private OnArtistSelectedListener mOnArtistSelectedListener;
    private static final String cINDEX = "index";
    private static final String cTOP_POSITION = "top_position";
    private int mIndex;
    private int mTop;
    private ArtistsPager mArtistPager;
    //========================================================
    //CONSTRUCTORS
    //========================================================

    //========================================================
    //OVERRIDDEN METHODS
    //========================================================


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mOnArtistSelectedListener = (OnArtistSelectedListener) activity;
        } catch (ClassCastException e) {
            Log.d(cLOG_TAG, activity.getClass().getSimpleName()+
            " Should implement "+OnArtistSelectedListener.class.getSimpleName());
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.f_search_artist;
    }

    @Override
    protected void initializeView(View view) {
        super.initializeView(view);
        mArtistAdapter = new ArtistAdapter(getActivity());
        mResultsListView.setAdapter(mArtistAdapter);
        if (mIndex != 0 || mTop != 0) {
            mResultsListView.setSelectionFromTop(mIndex, mTop);
        }
        if (mArtistPager != null) {
            mArtistAdapter.setPager(mArtistPager, false);
        }
    }

    @Override
    protected void initialize(Bundle savedInstance) {
        super.initialize(savedInstance);
        if (savedInstance != null) {
            Log.d(cLOG_TAG, "view is being restored");
            mIndex = savedInstance.getInt(cINDEX);
            mTop = savedInstance.getInt(cTOP_POSITION);
            String artistPagerJson = savedInstance.getString(cARTIST_PAGER);
            Gson gson = new GsonBuilder().create();
            mArtistPager = gson.fromJson(artistPagerJson, ArtistsPager.class);
        }
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(cLOG_TAG, "view state being saved");
        int index = mResultsListView.getFirstVisiblePosition();
        View v = mResultsListView.getChildAt(0);
        int top = (v == null) ? 0 : (v.getTop() - mResultsListView.getPaddingTop());
        outState.putInt(cINDEX, index);
        outState.putInt(cTOP_POSITION, top);
        Gson gson = new GsonBuilder().create();
        String artistPagerJson = gson.toJson(mArtistAdapter.getPager());
        outState.putString(cARTIST_PAGER, artistPagerJson);
    }


    @Override
    public void onResume() {
        super.onResume();
        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mArtistAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mResultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                mOnArtistSelectedListener.onArtistSelected(mArtistAdapter.getItem(position));

            }
        });
        mResultsListView.setOnScrollListener(new OnEndlessScrollListener() {
            @Override
            public void onListEndReached() {
                Log.d(cLOG_TAG, "end of list reached");
                final ArtistsPager artistPager = mArtistAdapter.getPager();
                if ((artistPager.artists.offset < artistPager.artists.total)) {
                    SpotifyService spotifyService = SpotifyStreamer.getInstance().getSpotifyService();
                    HashMap<String, Object> params = Util.buildSpotifyParams(artistPager.artists.limit,
                            artistPager.artists.offset + 10);
                    spotifyService.searchArtists(mSearch.getQuery().toString(), params,
                            new Callback<ArtistsPager>() {
                                @Override
                                public void success(final ArtistsPager artistsPager, Response response) {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mArtistAdapter.setPager(artistsPager, false);
                                        }
                                    });
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    Log.e(cLOG_TAG, "Error downloading artist data", error);
                                }
                            });
                }
            }
        });
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
