package id.co.pembelajar.moviecatalog.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowsModels {
    @SerializedName("results")
    private List<Result>results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public class Result{
        @SerializedName("original_name")
        private String title;
        @SerializedName("first_air_date")
        private String dateRelease;
        @SerializedName("poster_path")
        private String posterPath;
        @SerializedName("backdrop_path")
        private String backdropPath;
        @SerializedName("overview")
        private String overview;
        @SerializedName("popularity")
        private double popularity;
        @SerializedName("vote_average")
        private double voteAvg;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDateRelease() {
            return dateRelease;
        }

        public void setDateRelease(String dateRelease) {
            this.dateRelease = dateRelease;
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

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public double getVoteAvg() {
            return voteAvg;
        }

        public void setVoteAvg(double voteAvg) {
            this.voteAvg = voteAvg;
        }
    }
}
