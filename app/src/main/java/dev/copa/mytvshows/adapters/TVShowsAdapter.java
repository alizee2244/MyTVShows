package dev.copa.mytvshows.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import dev.copa.mytvshows.R;
import dev.copa.mytvshows.TVShowsActivity;
import dev.copa.mytvshows.models.TVShow;

public class TVShowsAdapter extends RecyclerView.Adapter<TVShowsAdapter.ViewHolder> {

    private List<TVShow> dataset;
    private Context context;
    private OnShowItemClickListener onShowItemClickListener;

    public TVShowsAdapter(List<TVShow> dataset, TVShowsActivity context) {
        this.dataset = dataset;
        this.context = context;
        this.onShowItemClickListener = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tvshows, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TVShow s = dataset.get(position);

        holder.nameTextView.setText(s.getName());
        holder.statusTextView.setText(s.getStatus());

        String url = s.getImageThumbnailPath();

        Glide.with(context).load(url).into(holder.imageImageView);

        holder.setOnShowItemClick(s, onShowItemClickListener);

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView statusTextView;
        ImageView imageImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            statusTextView = (TextView) itemView.findViewById(R.id.statusTextView);

            imageImageView = (ImageView) itemView.findViewById(R.id.imageImageView);
        }

        public void setOnShowItemClick(final TVShow s, final OnShowItemClickListener onShowItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onShowItemClickListener.onShowItemClick(s);
                }
            });
        }

    }

    public void add(TVShow show) {
        dataset.add(show);
        notifyDataSetChanged();
    }

    public void setDataset(List<TVShow> shows) {
        if (shows == null) {
            dataset = new ArrayList<>();
        } else {
            dataset = shows;
        }
        notifyDataSetChanged();
    }

}
