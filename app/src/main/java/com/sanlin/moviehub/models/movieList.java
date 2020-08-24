package com.sanlin.moviehub.models;

import java.util.List;

public class movieList {

    private long page;
    private long total_pages;
    private long total_results;
    private List<MovieModel> results;

    private int type;

    public long getPage() {
        return page;
    }

    public long getTotal_pages() {
        return total_pages;
    }

    public long getTotal_results() {
        return total_results;
    }

    public List<MovieModel> getMovieList() {
        return results;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
