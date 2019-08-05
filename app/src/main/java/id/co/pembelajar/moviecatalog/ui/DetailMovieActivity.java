package id.co.pembelajar.moviecatalog.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;
import id.co.pembelajar.moviecatalog.R;
import id.co.pembelajar.moviecatalog.model.MovieModels;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String PARCELABLE = "info_movie";
    private static final String URL_POSTER = "https://image.tmdb.org/t/p/w500/";
    private static final String URL_BAKCDROP = "https://image.tmdb.org/t/p/w780/";
    private TextView tiltleMovieTxt, releaseTxt, genresTxt, descripTxt, budgetTxt;
    private ImageView imgView, imgBd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        tiltleMovieTxt = findViewById(R.id.txt_title_movie);
        releaseTxt = findViewById(R.id.txt_release_date);
        genresTxt = findViewById(R.id.txt_genre);
        descripTxt = findViewById(R.id.txt_descrip);
        imgView = findViewById(R.id.poster_detail);
        budgetTxt = findViewById(R.id.txt_budget_info);
        imgBd = findViewById(R.id.head_img_movie);
        showingData();
    }


    private void showingData(){
        MovieModels.Results model = getIntent().getParcelableExtra(PARCELABLE);
        Resources res = getResources();
        tiltleMovieTxt.setText(model.getTitle());
        releaseTxt.setText(res.getString(R.string.release_date)+model.getReleaseDate());
        genresTxt.setText(String.valueOf(model.getPopularity()));
        descripTxt.setText(model.getOverview());
        budgetTxt.setText(res.getString(R.string.vote_avg)+model.getVoteAvg());
        Glide.with(this)
                .load(URL_POSTER+model.getPosterPath())
                .into(imgView);
        Glide.with(this)
                .load(URL_BAKCDROP+model.getBackdropPath())
                .into(imgBd);
    }

}
