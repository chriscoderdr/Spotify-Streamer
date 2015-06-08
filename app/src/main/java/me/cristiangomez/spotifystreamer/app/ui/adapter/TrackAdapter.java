package me.cristiangomez.spotifystreamer.app.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;
import kaaes.spotify.webapi.android.models.TracksPager;
import me.cristiangomez.spotifystreamer.R;

/**
 * Created by cristian on 07/06/15.
 */
public class TrackAdapter extends BaseAdapter {
    //========================================================
    //FIELDS
    //========================================================
    private static final String cLOG_TAG = TrackAdapter.class.getSimpleName();
    private List<Track> mTracks;
    private Context mContext;
    //========================================================
    //CONSTRUCTORS
    //========================================================
    public TrackAdapter(Context context, List<Track> tracks) {
        mTracks = tracks;
        mContext = context;
    }
    //========================================================
    //OVERRIDDEN METHODS
    //========================================================

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            rowView = inflater.inflate(R.layout.track_list_item, parent, false);
            TrackViewHolder holder = new TrackViewHolder(rowView);
            rowView.setTag(holder);
        }

        TrackViewHolder holder = (TrackViewHolder) rowView.getTag();
        Track track = getItem(position);
        if (!track.album.images.isEmpty()) {
            String previewImage = null;
            for (Image image: track.album.images) {
                if (image.height == 200) {
                    previewImage = image.url;
                    break;
                }
            }
            if (previewImage == null) {
                previewImage = track.album.images.get(0).url;
            }
            Picasso.with(mContext).load(previewImage).into(holder.image);
        }
        else {
            Picasso.with(mContext).load(R.drawable.track_placeholder).into(holder.image
            );
        }
        holder.name.setText(track.name);
        return rowView;
    }



    public Track getItem(int position) {
        return mTracks.get(position);
    }

    @Override
    public int getCount() {
        return mTracks.size();
    }

    public void addAll(List<Track> tracks) {
        mTracks.addAll(tracks);
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //========================================================
    //METHODS
    //========================================================

    //========================================================
    //INNER CLASSES
    //========================================================
    private static class TrackViewHolder {
        ImageView image;
        TextView name;
        public TrackViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.track_list_item_image);
            name = (TextView) view.findViewById(R.id.track_list_item_name);
        }
    }
    //========================================================
    //GETTERS AND SETTERS
    //========================================================
    public List<Track> getTracks() {
        return mTracks;
    }
}
