package id.co.pembelajar.moviecatalog.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShowsModel implements Parcelable {
    private int icTvShows;
    private String titleTvShows;
    private String statusTvShows;
    private String dateReleseTvShows;
    private String descripTvShow;
    private String genreTvShows;
    private int bdTvShows;

    public int getIcTvShows() {
        return icTvShows;
    }

    public void setIcTvShows(int icTvShows) {
        this.icTvShows = icTvShows;
    }

    public String getTitleTvShows() {
        return titleTvShows;
    }

    public void setTitleTvShows(String titleTvShows) {
        this.titleTvShows = titleTvShows;
    }

    public String getStatusTvShows() {
        return statusTvShows;
    }

    public void setStatusTvShows(String statusTvShows) {
        this.statusTvShows = statusTvShows;
    }

    public String getDateReleseTvShows() {
        return dateReleseTvShows;
    }

    public void setDateReleseTvShows(String dateReleseTvShows) {
        this.dateReleseTvShows = dateReleseTvShows;
    }

    public String getDescripTvShow() {
        return descripTvShow;
    }

    public void setDescripTvShow(String descripTvShow) {
        this.descripTvShow = descripTvShow;
    }

    public String getGenreTvShows() {
        return genreTvShows;
    }

    public void setGenreTvShows(String genreTvShows) {
        this.genreTvShows = genreTvShows;
    }

    public int getBdTvShows() {
        return bdTvShows;
    }

    public void setBdTvShows(int bdTvShows) {
        this.bdTvShows = bdTvShows;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.icTvShows);
        dest.writeString(this.titleTvShows);
        dest.writeString(this.statusTvShows);
        dest.writeString(this.dateReleseTvShows);
        dest.writeString(this.descripTvShow);
        dest.writeString(this.genreTvShows);
        dest.writeInt(this.bdTvShows);
    }

    public TvShowsModel() {
    }

    protected TvShowsModel(Parcel in) {
        this.icTvShows = in.readInt();
        this.titleTvShows = in.readString();
        this.statusTvShows = in.readString();
        this.dateReleseTvShows = in.readString();
        this.descripTvShow = in.readString();
        this.genreTvShows = in.readString();
        this.bdTvShows = in.readInt();
    }

    public static final Parcelable.Creator<TvShowsModel> CREATOR = new Parcelable.Creator<TvShowsModel>() {
        @Override
        public TvShowsModel createFromParcel(Parcel source) {
            return new TvShowsModel(source);
        }

        @Override
        public TvShowsModel[] newArray(int size) {
            return new TvShowsModel[size];
        }
    };
}
