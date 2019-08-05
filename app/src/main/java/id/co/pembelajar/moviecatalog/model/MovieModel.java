package id.co.pembelajar.moviecatalog.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel implements Parcelable {
    private int icon;
    private int bd_movie;
    private String titleMovie;
    private String dateRelease;
    private String genre;
    private String descrip;
    private String budget;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getBd_movie() {
        return bd_movie;
    }

    public void setBd_movie(int bd_movie) {
        this.bd_movie = bd_movie;
    }

    public String getTitleMovie() {
        return titleMovie;
    }

    public void setTitleMovie(String titleMovie) {
        this.titleMovie = titleMovie;
    }

    public String getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(String dateRelease) {
        this.dateRelease = dateRelease;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.icon);
        dest.writeInt(this.bd_movie);
        dest.writeString(this.titleMovie);
        dest.writeString(this.dateRelease);
        dest.writeString(this.genre);
        dest.writeString(this.descrip);
        dest.writeString(this.budget);
    }

    public MovieModel() {
    }

    protected MovieModel(Parcel in) {
        this.icon = in.readInt();
        this.bd_movie = in.readInt();
        this.titleMovie = in.readString();
        this.dateRelease = in.readString();
        this.genre = in.readString();
        this.descrip = in.readString();
        this.budget = in.readString();
    }

    public static final Parcelable.Creator<MovieModel> CREATOR = new Parcelable.Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel source) {
            return new MovieModel(source);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };
}
