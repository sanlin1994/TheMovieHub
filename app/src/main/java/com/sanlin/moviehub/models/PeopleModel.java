package com.sanlin.moviehub.models;

public class PeopleModel {

    private String birthday,known_for_department,deathday,name,biography,place_of_birth,profile_path,homepage;
    private int gender,id;
    private float popularity;
    private String[] also_known_as;

    public String getBirthday() {
        return birthday;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public String getDeathday() {
        return deathday;
    }


    public String getName() {
        return name;
    }

    public String[] getAlso_known_as() {
        return also_known_as;
    }

    public String getBiography() {
        return biography;
    }


    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public String getHomepage() {
        return homepage;
    }

    public int getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public float getPopularity() {
        return popularity;
    }
}
