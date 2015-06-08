package me.cristiangomez.spotifystreamer.app.pojo;

/**
 * Created by cristian on 07/06/15.
 */
public class ArtistSearch {
    //========================================================
    //FIELDS
    //========================================================
    private int mOffset;
    private int mLimit;
    private String mQuery;
    //========================================================
    //CONSTRUCTORS
    //========================================================

    public ArtistSearch(int offset, int limit, String query) {
        mOffset = offset;
        mLimit = limit;
        mQuery = query;
    }

    //========================================================
    //OVERRIDDEN METHODS
    //========================================================

    //========================================================
    //METHODS
    //========================================================

    public int getLimit() {
        return mLimit;
    }

    public int getOffset() {
        return mOffset;
    }

    public String getQuery() {
        return mQuery;
    }

    //========================================================
    //INNER CLASSES
    //========================================================

    //========================================================
    //GETTERS AND SETTERS
    //========================================================
}
