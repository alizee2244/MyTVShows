package dev.copa.mytvshows;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import dev.copa.mytvshows.models.DetailResponse;
import dev.copa.mytvshows.models.TVShow;
import dev.copa.mytvshows.services.ServiceGenerator;
import dev.copa.mytvshows.services.TheTVShowService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private ImageView tvshowImageView;
    private TextView tvshowNameTextView;
    private TextView tvshowGenresTextView;
    private TextView tvshowDescriptionTextView;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tvshow);

        tvshowImageView = (ImageView) findViewById(R.id.tvshowImageView);
        tvshowNameTextView = (TextView) findViewById(R.id.tvshowNameTextView);
        tvshowGenresTextView = (TextView) findViewById(R.id.tvshowGenresTextView);
        tvshowDescriptionTextView = (TextView) findViewById(R.id.tvshowDescriptionTextView);

        id = getIntent().getIntExtra("id",0);
        //Toast.makeText(this, "id..." + id, Toast.LENGTH_SHORT).show();

        TheTVShowService service = ServiceGenerator.createService(TheTVShowService.class);

        Call<DetailResponse> call = service.detailTVShow(id);
        //Log.e("PRUEBA","ENTRA");
        call.enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
                //Log.e("PRUEBA","AQUI TAMBIEN");

                TVShow s = response.body().getTvShow();

                String url = s.getImagePath();

                tvshowNameTextView.setText(s.getName());
                //Toast.makeText(DetailActivity.this, "name..." + s.getName(), Toast.LENGTH_SHORT).show();
                //tvshowGenresTextView.setText(s.getGenres());
                tvshowDescriptionTextView.setText(s.getDescription());

                Glide.with(DetailActivity.this).load(url).into(tvshowImageView);

            }

            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {

            }
        });

    }
}
