package id.co.pembelajar.moviecatalog.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;
import id.co.pembelajar.moviecatalog.R;
import id.co.pembelajar.moviecatalog.model.MovieModel;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String PARCELABLE = "info_movie";
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
        MovieModel model = getIntent().getParcelableExtra(PARCELABLE);
        Resources res = getResources();
        tiltleMovieTxt.setText(model.getTitleMovie());
        releaseTxt.setText(res.getString(R.string.release_date)+model.getDateRelease());
        genresTxt.setText(model.getGenre());
        descripTxt.setText(model.getDescrip());
        budgetTxt.setText(res.getString(R.string.budget_film)+model.getBudget());
        Glide.with(this)
                .load(model.getIcon())
                .into(imgView);
        Glide.with(this)
                .load(model.getBd_movie())
                .into(imgBd);
    }

}
