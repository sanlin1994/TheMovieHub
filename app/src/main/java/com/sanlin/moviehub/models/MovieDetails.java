package com.sanlin.moviehub.models;

import java.util.List;

public class MovieDetails {

    private String backdrop_path,homepage,imdb_id,
            original_language,original_title,overview,
            poster_path,release_date;
    private long budget,id,revenue,runtime,vote_count;
    private List<Genre> genres;
    private float popularity,vote_average;
    private List<ProductionCompanies> production_companies;

    public List<ProductionCompanies> getProduction_companies() {
        return production_companies;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public long getBudget() {
        return budget;
    }

    public long getId() {
        return id;
    }

    public long getRevenue() {
        return revenue;
    }

    public long getRuntime() {
        return runtime;
    }

    public long getVote_count() {
        return vote_count;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public float getPopularity() {
        return popularity;
    }

    public float getVote_average() {
        return vote_average;
    }
}
