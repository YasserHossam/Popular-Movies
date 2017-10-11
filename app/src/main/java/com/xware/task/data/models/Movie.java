package com.xware.task.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by yasser on 09/10/17.
 */

public class Movie {
    @SerializedName("id")
    private int id;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("title")
    private String movieName;

    @SerializedName("vote_average")
    private float rating;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("genres")
    private ArrayList<Genre> genres;

    public int getId() {
        return id;
    }

    public String getPoster() {
        return poster;
    }

    public String getMovieName() {
        return movieName;
    }

    public float getRating() {
        return rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }
}
