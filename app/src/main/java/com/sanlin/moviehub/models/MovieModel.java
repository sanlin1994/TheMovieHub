package com.sanlin.moviehub.models;

public class MovieModel {

    private long id;
    private String title;
    private long vote_count;
    private float vote_average;
    private String poster_path;
    private String release_date;
    private float popularity;
    private String backdrop_path;

    public float getPopularity() {
        return popularity;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getVote_count() {
        return vote_count;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }
}
