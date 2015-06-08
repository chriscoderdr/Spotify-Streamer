package me.cristiangomez.spotifystreamer.app.pojo;

/**
 * Created by cristian on 07/06/15.
 */
public class ArtistTopTracksQuery {
    //========================================================
    //FIELDS
    //========================================================
    private String mArtistId;
    private String mCountry;
    //========================================================
    //CONSTRUCTORS
    //========================================================

    public ArtistTopTracksQuery(String artistId, String country) {
        mArtistId = artistId;
        mCountry = country;
    }

    //========================================================
    //OVERRIDDEN METHODS
    //========================================================

    //========================================================
    //METHODS
    //========================================================

    public String getArtistId() {
        return mArtistId;
    }

    public String getCountry() {
        return mCountry;
    }

    //========================================================
    //INNER CLASSES
    //========================================================

    //========================================================
    //GETTERS AND SETTERS
    //========================================================
}
