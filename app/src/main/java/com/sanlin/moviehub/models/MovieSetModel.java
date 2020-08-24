package com.sanlin.moviehub.models;
import java.util.List;

public class MovieSetModel {

    private String title;
    private String type_title;
    private List<MovieModel> movieModelArrayList;
    private  int type;
    private List<CastModel> castModelList;

    public List<CastModel> getCastModelList() {
        return castModelList;
    }

    public void setCastModelList(List<CastModel> castModelList) {
        this.castModelList = castModelList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MovieModel> getMovieModelArrayList() {
        return movieModelArrayList;
    }

    public void setMovieModelArrayList(List<MovieModel> movieModelArrayList) {
        this.movieModelArrayList = movieModelArrayList;
    }

    public String getType_title() {
        return type_title;
    }

    public void setType_title(String type_title) {
        this.type_title = type_title;
    }
}
