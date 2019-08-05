package id.co.pembelajar.moviecatalog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.co.pembelajar.moviecatalog.R;
import id.co.pembelajar.moviecatalog.model.TvShowsModels;

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder> {
    private static final String URL_POSTER = "https://image.tmdb.org/t/p/w500";
    private Context mContext;
    private List<TvShowsModels.Result> modelsList;
    private OnItemCallback onItemCallback;

    public interface OnItemCallback{
        void onSetItemCallbak(TvShowsModels.Result tvModel);
    }

    public void setOnItemCallback(OnItemCallback onItemCallback) {
        this.onItemCallback = onItemCallback;
    }

    public TvShowsAdapter(Context mContext, List<TvShowsModels.Result> list) {
        this.mContext = mContext;
        this.modelsList = list;
    }

    @NonNull
    @Override
    public TvShowsAdapter.TvShowsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
        return new TvShowsViewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull final TvShowsAdapter.TvShowsViewHolder holder, final int position) {
        TvShowsModels.Result model = modelsList.get(position);
        holder.titleTvShow.setText(model.getTitle());
        holder.genreTvShows.setText(String.valueOf(model.getPopularity()));
        holder.releaseTvShow.setText(model.getDateRelease());
        Glide.with(mContext)
                .load(URL_POSTER+model.getPosterPath())
                .into(holder.imgTvShows);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallback.onSetItemCallbak(modelsList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelsList.size();
    }

    public class TvShowsViewHolder extends RecyclerView.ViewHolder {
        TextView titleTvShow, releaseTvShow, genreTvShows;
        ImageView imgTvShows;
        public TvShowsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTvShow = itemView.findViewById(R.id.title_Card);
            releaseTvShow = itemView.findViewById(R.id.card_release);
            genreTvShows = itemView.findViewById(R.id.card_genre);
            imgTvShows = itemView.findViewById(R.id.ic_poster_Card);
        }
    }
}
