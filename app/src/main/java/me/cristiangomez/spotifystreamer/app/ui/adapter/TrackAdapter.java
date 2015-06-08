package me.cristiangomez.spotifystreamer.app.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;
import kaaes.spotify.webapi.android.models.TracksPager;
import me.cristiangomez.spotifystreamer.R;

/**
 * Created by cristian on 07/06/15.
 */
public class TrackAdapter extends ArrayAdapter<Track>  {
    //========================================================
    //FIELDS
    //========================================================
    private static final String cLOG_TAG = TrackAdapter.class.getSimpleName();
    //========================================================
    //CONSTRUCTORS
    //========================================================
    public TrackAdapter(Context context) {
        super(context, R.layout.track_list_item, new ArrayList<Track>());
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
            Picasso.with(getContext()).load(previewImage).into(holder.image);
        }
        else {
            Picasso.with(getContext()).load(R.drawable.track_placeholder).into(holder.image
            );
        }
        holder.name.setText(track.name);
        return rowView;
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

}
