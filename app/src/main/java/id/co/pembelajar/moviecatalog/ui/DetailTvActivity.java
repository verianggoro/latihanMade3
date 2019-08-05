package id.co.pembelajar.moviecatalog.ui;

import android.content.res.Resources;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import id.co.pembelajar.moviecatalog.R;
import id.co.pembelajar.moviecatalog.model.TvShowsModel;

import android.widget.ImageView;
import android.widget.TextView;

public class DetailTvActivity extends AppCompatActivity {
    public static final String PARCELABLE = "info_tv";
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
        TvShowsModel model = getIntent().getParcelableExtra(PARCELABLE);
        Resources res = getResources();
        titleMovieTxt.setText(model.getTitleTvShows());
        releaseTxt.setText(res.getString(R.string.release_date)+model.getDateReleseTvShows());
        genresTxt.setText(model.getGenreTvShows());
        descripTxt.setText(model.getDescripTvShow());
        statusTxt.setText(model.getStatusTvShows());
        Glide.with(this)
                .load(model.getIcTvShows())
                .into(imgView);
        Glide.with(this)
                .load(model.getBdTvShows())
                .into(imgHeadTv);
    }
}
