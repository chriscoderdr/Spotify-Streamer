package me.cristiangomez.spotifystreamer.app.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import me.cristiangomez.spotifystreamer.R;

/**
 * Created by cristian on 07/06/15.
 */
public class ArtistAdapter extends ArrayAdapter<Artist> implements Filterable {
    //========================================================
    //FIELDS
    //========================================================
    private static final String cLOG_TAG = ArtistAdapter.class.getSimpleName();
    private ArtistsPager mPager;
    private ArtistFilter mFilter;
    //========================================================
    //CONSTRUCTORS
    //========================================================
    public ArtistAdapter(Context context) {
        super(context, R.layout.artist_list_item, new ArrayList<Artist>());
    }
    //========================================================
    //OVERRIDDEN METHODS
    //========================================================

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            rowView = inflater.inflate(R.layout.artist_list_item, parent, false);
            ArtistViewHolder holder = new ArtistViewHolder(rowView);
            rowView.setTag(holder);
        }

        ArtistViewHolder holder = (ArtistViewHolder) rowView.getTag();
        Artist artist = getItem(position);
        if (!artist.images.isEmpty()) {
            Picasso.with(getContext()).load(artist.images.get(0).url).into(holder.image);
        }
        else {
            Picasso.with(getContext()).load(R.drawable.artist_placeholder).into(holder.image
            );
        }
        holder.name.setText(artist.name);
        return rowView;
    }


    //========================================================
    //METHODS
    //========================================================

    //========================================================
    //INNER CLASSES
    //========================================================
    private static class ArtistViewHolder {
        ImageView image;
        TextView name;
        public ArtistViewHolder (View view) {
            image = (ImageView) view.findViewById(R.id.artist_list_item_iv_image);
            name = (TextView) view.findViewById(R.id.artist_list_item_tv_name);
        }
    }
    //========================================================
    //GETTERS AND SETTERS
    //========================================================
    public ArtistsPager getPager() {
        return mPager;
    }
    public void setPager(ArtistsPager pager, boolean clear) {
        if (pager != null) {
            mPager = pager;
            if (clear) {
                clear();
            }
            addAll(pager.artists.items);
            if (pager.artists.total == 0) {
                Toast.makeText(getContext(), R.string.no_artist_found, Toast.LENGTH_SHORT).show();
            }
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ArtistFilter(this);
        }
        return mFilter;
    }

}
