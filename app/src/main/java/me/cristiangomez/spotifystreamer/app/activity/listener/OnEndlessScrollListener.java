package me.cristiangomez.spotifystreamer.app.activity.listener;

import android.widget.AbsListView;

/**
 * Created by cristian on 07/06/15.
 */
/*
* Reference https://github.com/codepath/android_guides/wiki/Endless-Scrolling-with-AdapterViews
* I use this for load more artists/tracks when listview is scrolled down using
* spotify pagination
 */
public abstract class OnEndlessScrollListener implements AbsListView.OnScrollListener {
    //========================================================
    //FIELDS
    //========================================================
    private int preLast;
    //========================================================
    //CONSTRUCTORS
    //========================================================

    //========================================================
    //OVERRIDDEN METHODS
    //========================================================
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        int lastItem = firstVisibleItem + visibleItemCount;
        if (lastItem == totalItemCount) {
            if (preLast != lastItem) {
                preLast = lastItem;
                onListEndReached();
            }
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }
    //========================================================
    //METHODS
    //========================================================
    public abstract void onListEndReached();
    //========================================================
    //INNER CLASSES
    //========================================================

    //========================================================
    //GETTERS AND SETTERS
    //========================================================
}
