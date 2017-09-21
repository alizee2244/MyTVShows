package dev.copa.mytvshows.services;

import dev.copa.mytvshows.models.DetailResponse;
import dev.copa.mytvshows.models.TVShowResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Copa on 20/9/2017.
 */

public interface TheTVShowService {

    //Search TV Shows
    @GET("search")
    Call<TVShowResponse> buscarTVShows(@Query("q") String q, @Query("p") int p);

    //Show Most Popular TV Shows
    @GET("most-popular")
    Call<TVShowResponse> popularTVShows(@Query("p") int p);

    //Show Details from TV Show
    @GET("show-details")
    Call<DetailResponse> detailTVShow(@Query("q") int q);

}
