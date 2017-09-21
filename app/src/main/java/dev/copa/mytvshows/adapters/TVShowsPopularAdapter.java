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
import dev.copa.mytvshows.TVShowsPopularActivity;
import dev.copa.mytvshows.models.TVShow;

public class TVShowsPopularAdapter extends RecyclerView.Adapter<TVShowsPopularAdapter.ViewHolder> {

    private List<TVShow> dataset;
    private Context context;
    private OnPopularItemClickListener onPopularItemClickListener;

    public TVShowsPopularAdapter(List<TVShow> dataset, TVShowsPopularActivity context) {
        this.dataset = dataset;
        this.context = context;
        this.onPopularItemClickListener = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tvshowspopular, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TVShow s = dataset.get(position);

        holder.nameTextView.setText(s.getName());
        holder.networkTextView.setText(s.getNetwork());

        String url = s.getImageThumbnailPath();

        Glide.with(context).load(url).into(holder.imageImageView);

        holder.setOnPopularItemClick(s, onPopularItemClickListener);

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView networkTextView;
        ImageView imageImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            networkTextView = (TextView) itemView.findViewById(R.id.networkTextView);

            imageImageView = (ImageView) itemView.findViewById(R.id.imageImageView);
        }

        public void setOnPopularItemClick(final TVShow s, final OnPopularItemClickListener onPopularItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onPopularItemClickListener.onPopularItemClick(s);
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
