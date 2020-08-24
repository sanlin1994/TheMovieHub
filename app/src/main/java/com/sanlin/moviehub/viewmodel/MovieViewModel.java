package com.sanlin.moviehub.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sanlin.moviehub.models.CreditsModel;
import com.sanlin.moviehub.models.MovieDetails;
import com.sanlin.moviehub.models.PeopleModel;
import com.sanlin.moviehub.models.ReviewModelList;
import com.sanlin.moviehub.models.movieList;
import com.sanlin.moviehub.repository.MovieRespository;

public class MovieViewModel extends AndroidViewModel {

    private MovieRespository movieRespository;
    private LiveData<movieList> data;
    private LiveData<movieList> similar;
    private LiveData<movieList> popular;
    private LiveData<MovieDetails> movieDetails;
    private LiveData<CreditsModel> creditsModelLiveData;
    private LiveData<ReviewModelList> reviewModelListLiveData;
    private LiveData<PeopleModel> peopleModelLiveData;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRespository = new MovieRespository();
    }

    public LiveData<movieList> getMovieTrending(String mediaType, String timeWindow,int page) {

        data = movieRespository.getMovieTrending(mediaType, timeWindow,page);

        return data;
    }

    public LiveData<MovieDetails> getMovieDetails(long id){

        movieDetails = movieRespository.getMovieDetails(id);

        return movieDetails;

    }

    public LiveData<movieList> getMovieSimilar(long id) {

        similar = movieRespository.getMovieSimilar(id);

        return similar;
    }

    public LiveData<movieList> getMovie(String category,int page) {

        popular = movieRespository.getMovie(category,page);
        return popular;

    }

    public LiveData<CreditsModel> getMovieCredits(long id){

        creditsModelLiveData = movieRespository.getMovieCredits(id);
        return  creditsModelLiveData;


    }

    public LiveData<ReviewModelList> getMovieReviews(long id,int page){

        reviewModelListLiveData = movieRespository.getMovieReviews(id,page);
        return  reviewModelListLiveData;


    }

    public LiveData<PeopleModel> getMoviePeople(long id){

        peopleModelLiveData = movieRespository.getMoviePeople(id);
        return  peopleModelLiveData;

    }


    public LiveData<movieList> getMovieSearch(String query,int page){

        data = movieRespository.getMovieSearch(query,page);
        return data;

    }
}
