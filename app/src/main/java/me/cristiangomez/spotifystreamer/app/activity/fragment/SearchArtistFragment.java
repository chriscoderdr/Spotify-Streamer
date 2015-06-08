package me.cristiangomez.spotifystreamer.app.activity.fragment;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

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
    private static final String cSCROLL_POSITION = "scroll_position";
    private static final String cLOG_TAG = SearchArtistFragment.class.getSimpleName();
    private OnArtistSelectedListener mOnArtistSelectedListener;
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
        mArtistAdapter = new ArtistAdapter(getActivity());
        mResultsListView = (ListView) view.findViewById(R.id.f_search_artist_lv_results);
        mResultsListView.setAdapter(mArtistAdapter);
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
        mSearch = (EditText) view.findViewById(R.id.f_search_artist_et_artist);
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mArtistAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

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
