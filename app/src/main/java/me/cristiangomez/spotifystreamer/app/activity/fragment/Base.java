package me.cristiangomez.spotifystreamer.app.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cristian on 07/06/15.
 */
public class Base extends Fragment {
    //========================================================
    //FIELDS
    //========================================================

    //========================================================
    //CONSTRUCTORS
    //========================================================

    //========================================================
    //OVERRIDDEN METHODS
    //========================================================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(), getLayoutResource(), container);
        initializeView(view);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //========================================================
    //METHODS
    //========================================================
    protected void initialize() {

    }

    protected void initializeView(View view) {

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
