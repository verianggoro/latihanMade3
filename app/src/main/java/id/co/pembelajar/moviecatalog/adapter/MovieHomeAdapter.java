package id.co.pembelajar.moviecatalog.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.co.pembelajar.moviecatalog.R;
import id.co.pembelajar.moviecatalog.model.MovieModel;
import id.co.pembelajar.moviecatalog.model.MovieModels;

public class MovieHomeAdapter extends RecyclerView.Adapter<MovieHomeAdapter.MovieHomeViewHolder> {
    private static final String URL_POSTER = "https://image.tmdb.org/t/p/w500";
    private Context mContext;
    private List<MovieModels.Results> models;
    private OnItemClickCallback onItemClickCallback;

    public interface OnItemClickCallback{
        void onClickedItem(MovieModels.Results model);
    }

    public MovieHomeAdapter(Context mContext, List<MovieModels.Results> listData) {
        this.mContext = mContext;
        this.models = listData;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public MovieHomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewerInflater = LayoutInflater.from(mContext).inflate(R.layout.item_list, viewGroup, false);
        return new MovieHomeViewHolder(viewerInflater);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieHomeViewHolder movieHomeViewHolder, int i) {
        MovieModels.Results model = models.get(i);
        movieHomeViewHolder.titleMovie.setText(model.getTitle());
        movieHomeViewHolder.releaseCard.setText(model.getReleaseDate());
        movieHomeViewHolder.genreCard.setText(String.valueOf(model.getPopularity()));
        Glide.with(mContext)
                .load(URL_POSTER+model.getPosterPath())
                .into(movieHomeViewHolder.imgPoster);
        movieHomeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onClickedItem(models.get(movieHomeViewHolder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MovieHomeViewHolder extends RecyclerView.ViewHolder {
        TextView titleMovie, releaseCard, genreCard;
        ImageView imgPoster;
        public MovieHomeViewHolder(@NonNull View itemView) {
            super(itemView);
            titleMovie = itemView.findViewById(R.id.title_Card);
            releaseCard = itemView.findViewById(R.id.card_release);
            genreCard = itemView.findViewById(R.id.card_genre);
            imgPoster = itemView.findViewById(R.id.ic_poster_Card);
        }
    }
}
