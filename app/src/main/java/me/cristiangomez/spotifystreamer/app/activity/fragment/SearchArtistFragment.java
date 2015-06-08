package me.cristiangomez.spotifystreamer.app.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kaaes.spotify.webapi.android.models.ArtistsPager;
import me.cristiangomez.spotifystreamer.R;
import me.cristiangomez.spotifystreamer.app.activity.listener.OnArtistSelectedListener;
import me.cristiangomez.spotifystreamer.app.activity.listener.ArtistsFetcherListener;
import me.cristiangomez.spotifystreamer.app.net.FetchArtistsTasks;
import me.cristiangomez.spotifystreamer.app.pojo.ArtistSearch;
import me.cristiangomez.spotifystreamer.app.ui.adapter.ArtistAdapter;
import me.cristiangomez.spotifystreamer.app.ui.adapter.OnEndlessScrollListener;

/**
 * Created by cristian on 07/06/15.
 */
public class SearchArtistFragment extends Base {
    //========================================================
    //FIELDS
    //========================================================
    private ListView mResultsListView;
    private ArtistAdapter mArtistAdapter;
    private EditText mSearch;
    private static final String cARTISTPAGER = "ARTIST_PAGER";
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
    protected void initialize(Bundle savedInstance) {
        super.initialize(savedInstance);
        if (savedInstance != null) {
            Log.d(cLOG_TAG, "view is being restored");
            mIndex = savedInstance.getInt(cINDEX);
            mTop = savedInstance.getInt(cTOP_POSITION);
            String artistPagerJson = savedInstance.getString(cARTISTPAGER);
            Gson gson = new GsonBuilder().create();
            mArtistPager = gson.fromJson(artistPagerJson, ArtistsPager.class);
        }
    }

    @Override
    protected void initializeView(View view) {
        mArtistAdapter = new ArtistAdapter(getActivity());
        mResultsListView = (ListView) view.findViewById(R.id.f_search_artist_lv_results);
        mResultsListView.setAdapter(mArtistAdapter);
        if (mIndex != 0 || mTop != 0) {
            mResultsListView.setSelectionFromTop(mIndex, mTop);
        }
        if (mArtistPager != null) {
            mArtistAdapter.setPager(mArtistPager, false);
        }
        mSearch = (EditText) view.findViewById(R.id.f_search_artist_et_artist);
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
        outState.putString(cARTISTPAGER, artistPagerJson);
    }


    @Override
    public void onResume() {
        super.onResume();
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(cLOG_TAG, "Text has change");
                mArtistAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

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
                if (mArtistAdapter != null && (artistPager.artists.offset < artistPager.artists.total)) {
                    ArtistSearch search = new ArtistSearch(artistPager.artists.offset + artistPager.artists.limit,
                            artistPager.artists.limit, mSearch.getText().toString());
                    new FetchArtistsTasks(new ArtistsFetcherListener() {
                        @Override
                        public void onArtistsFetched(ArtistsPager artistsPager) {
                            mArtistAdapter.setPager(artistsPager, false);
                        }
                    }).execute(search);
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
