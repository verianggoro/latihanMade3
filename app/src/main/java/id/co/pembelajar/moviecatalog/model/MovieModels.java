package id.co.pembelajar.moviecatalog.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieModels {
    @SerializedName("results")
    private List<Results> results;

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public class Results implements Parcelable {

        @SerializedName("title")
        private String title;
        @SerializedName("poster_path")
        private String posterPath;
        @SerializedName("backdrop_path")
        private String backdropPath;
        @SerializedName("overview")
        private String overview;
        @SerializedName("release_date")
        private String releaseDate;
        @SerializedName("vote_average")
        private double voteAvg;
        @SerializedName("popularity")
        private double popularity;

        protected Results(Parcel in) {
            title = in.readString();
            posterPath = in.readString();
            backdropPath = in.readString();
            overview = in.readString();
            releaseDate = in.readString();
            voteAvg = in.readDouble();
            popularity = in.readDouble();
        }

        public final Creator<Results> CREATOR = new Creator<Results>() {
            @Override
            public Results createFromParcel(Parcel in) {
                return new Results(in);
            }

            @Override
            public Results[] newArray(int size) {
                return new Results[size];
            }
        };

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public double getVoteAvg() {
            return voteAvg;
        }

        public void setVoteAvg(double voteAvg) {
            this.voteAvg = voteAvg;
        }

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(title);
            dest.writeString(posterPath);
            dest.writeString(backdropPath);
            dest.writeString(overview);
            dest.writeString(releaseDate);
            dest.writeDouble(voteAvg);
            dest.writeDouble(popularity);
        }
    }
}
