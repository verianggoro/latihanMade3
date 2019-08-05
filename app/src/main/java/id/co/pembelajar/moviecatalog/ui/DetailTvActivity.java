package id.co.pembelajar.moviecatalog.ui;

import android.content.res.Resources;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import id.co.pembelajar.moviecatalog.R;
import id.co.pembelajar.moviecatalog.model.TvShowsModels;

import android.widget.ImageView;
import android.widget.TextView;

public class DetailTvActivity extends AppCompatActivity {
    public static final String PARCELABLE = "info_tv";
    private static final String URL_POSTER = "https://image.tmdb.org/t/p/w500/";
    private static final String URL_BAKCDROP = "https://image.tmdb.org/t/p/w780/";
    private TextView titleMovieTxt, releaseTxt, genresTxt, descripTxt, statusTxt;
    private ImageView imgView, imgHeadTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_tv);
        setSupportActionBar(toolbar);

        titleMovieTxt = findViewById(R.id.txt_title_tv);
        releaseTxt = findViewById(R.id.txt_release_date_tv);
        genresTxt = findViewById(R.id.txt_genre_tv);
        descripTxt = findViewById(R.id.desc_tv);
        statusTxt = findViewById(R.id.txt_status_tv);
        imgView = findViewById(R.id.img_tv_poster_detail);
        imgHeadTv = findViewById(R.id.head_img_tv);
        showingData();
    }

    private void showingData(){
        TvShowsModels.Result model = getIntent().getParcelableExtra(PARCELABLE);
        Resources res = getResources();
        titleMovieTxt.setText(model.getTitle());
        releaseTxt.setText(res.getString(R.string.release_date)+model.getDateRelease());
        genresTxt.setText(String.valueOf(model.getPopularity()));
        descripTxt.setText(model.getOverview());
        statusTxt.setText(String.valueOf(model.getVoteAvg()));
        Glide.with(this)
                .load(URL_POSTER+ model.getPosterPath())
                .into(imgView);
        Glide.with(this)
                .load(URL_BAKCDROP+ model.getBackdropPath())
                .into(imgHeadTv);
    }
}
