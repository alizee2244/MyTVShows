package dev.copa.mytvshows.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Usuario on 20/9/2017.
 */

public class TVShowResponse {

    @SerializedName("tv_shows")
    private List<TVShow> tvShows = null;

    public List<TVShow> getTvShows() {
        return tvShows;
    }

    public void setTvShows(List<TVShow> tvShows) {
        this.tvShows = tvShows;
    }
}
