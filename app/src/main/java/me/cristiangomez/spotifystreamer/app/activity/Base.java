package me.cristiangomez.spotifystreamer.app.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by cristian on 07/06/15.
 */
abstract class Base extends AppCompatActivity {
    //========================================================
    //FIELDS
    //========================================================
    protected FragmentManager mFragmentManager;
    //========================================================
    //CONSTRUCTORS
    //========================================================

    //========================================================
    //OVERRIDDEN METHODS
    //========================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(savedInstanceState);
    }

    //========================================================
    //METHODS
    //========================================================
    protected void initialize(Bundle savedInstance) {
        if (getLayoutResource() != 0) {
            setContentView(getLayoutResource());
        }
        mFragmentManager = getSupportFragmentManager();
    }
    //========================================================
    //INNER CLASSES
    //========================================================

    //========================================================
    //GETTERS AND SETTERS
    //========================================================
    protected int getLayoutResource() {
        return 0;
    }
}
