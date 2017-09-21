package dev.copa.mytvshows;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import dev.copa.mytvshows.adapters.OnPopularItemClickListener;
import dev.copa.mytvshows.adapters.TVShowsPopularAdapter;
import dev.copa.mytvshows.models.TVShow;
import dev.copa.mytvshows.models.TVShowResponse;
import dev.copa.mytvshows.services.ServiceGenerator;
import dev.copa.mytvshows.services.TheTVShowService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowsPopularActivity extends AppCompatActivity implements OnPopularItemClickListener {

    private RecyclerView showspopularRecyclerView;
    private TVShowsPopularAdapter showsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshows_popular);

        showspopularRecyclerView = (RecyclerView) findViewById(R.id.tvshowspopularRecyclerView);
        showspopularRecyclerView.setHasFixedSize(true);
        showspopularRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        TheTVShowService service = ServiceGenerator.createService(TheTVShowService.class);

        Call<TVShowResponse> call = service.popularTVShows(1);

        call.enqueue(new Callback<TVShowResponse>() {
            @Override
            public void onResponse(Call<TVShowResponse> call, Response<TVShowResponse> response) {

                if(response.isSuccessful()) {

                    List<TVShow> tvshows = response.body().getTvShows();

                    showsAdapter = new TVShowsPopularAdapter(tvshows, TVShowsPopularActivity.this);
                    showspopularRecyclerView.setAdapter(showsAdapter);

                }

            }

            @Override
            public void onFailure(Call<TVShowResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void onPopularItemClick(TVShow s) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("id", s.getId());
        startActivity(intent);
    }

}
