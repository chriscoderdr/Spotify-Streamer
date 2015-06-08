package me.cristiangomez.spotifystreamer.app.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by cristian on 07/06/15.
 */
public class BaseFragment extends android.support.v4.app.Fragment {
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
        initialize(savedInstanceState);
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
    protected void initialize(Bundle savedInstance) {
    }

    protected void initializeView(View view) {
        ButterKnife.inject(this, view);
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
