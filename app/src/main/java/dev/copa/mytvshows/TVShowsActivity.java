package dev.copa.mytvshows;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import dev.copa.mytvshows.adapters.OnShowItemClickListener;
import dev.copa.mytvshows.adapters.TVShowsAdapter;
import dev.copa.mytvshows.models.TVShow;
import dev.copa.mytvshows.models.TVShowResponse;
import dev.copa.mytvshows.services.ServiceGenerator;
import dev.copa.mytvshows.services.TheTVShowService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowsActivity extends AppCompatActivity implements OnShowItemClickListener {

    private String text;
    private RecyclerView showsRecyclerView;
    private TVShowsAdapter showsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshows);

        showsRecyclerView = (RecyclerView) findViewById(R.id.tvshowsRecyclerView);
        showsRecyclerView.setHasFixedSize(true);
        showsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        text = getIntent().getStringExtra("text");
        //Toast.makeText(TVShowsActivity.this, "Text: " + text, Toast.LENGTH_SHORT).show();

        TheTVShowService service = ServiceGenerator.createService(TheTVShowService.class);

        Call<TVShowResponse> call = service.buscarTVShows(text,1);
        
        call.enqueue(new Callback<TVShowResponse>() {
            @Override
            public void onResponse(Call<TVShowResponse> call, Response<TVShowResponse> response) {
                
                if(response.isSuccessful()) {
                    
                    List<TVShow> tvshows = response.body().getTvShows();
                    
                    showsAdapter = new TVShowsAdapter(tvshows, TVShowsActivity.this);
                    showsRecyclerView.setAdapter(showsAdapter);
                    
                }
                
            }

            @Override
            public void onFailure(Call<TVShowResponse> call, Throwable t) {

            }
        });
        
    }

    @Override
    public void onShowItemClick(TVShow s) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("id", s.getId());
        startActivity(intent);
    }

}
