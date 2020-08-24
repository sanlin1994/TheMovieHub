package com.sanlin.moviehub.models;

import java.util.List;

public class ReviewModelList {

    private long page;
    private long total_pages;
    private long total_results;
    private List<ReviewModel> results;

    public long getPage() {
        return page;
    }

    public long getTotal_pages() {
        return total_pages;
    }

    public long getTotal_results() {
        return total_results;
    }

    public List<ReviewModel> getResults() {
        return results;
    }
}
