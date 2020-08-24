package com.sanlin.moviehub.models;

import java.util.List;

public class CreditsModel {

    private int id;
    private List<CastModel> cast;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CastModel> getCast() {
        return cast;
    }

    public void setCast(List<CastModel> cast) {
        this.cast = cast;
    }
}
