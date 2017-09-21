package dev.copa.mytvshows.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Usuario on 20/9/2017.
 */

public class DetailResponse {

    @SerializedName("tvShow")
    private TVShow tvShow;

    public TVShow getTvShow() {
        return tvShow;
    }

    public void setTvShow(TVShow tvShow) {
        this.tvShow = tvShow;
    }

}
