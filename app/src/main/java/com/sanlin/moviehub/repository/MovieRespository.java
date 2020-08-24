package com.sanlin.moviehub.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sanlin.moviehub.models.CreditsModel;
import com.sanlin.moviehub.models.MovieDetails;
import com.sanlin.moviehub.models.PeopleModel;
import com.sanlin.moviehub.models.ReviewModelList;
import com.sanlin.moviehub.models.movieList;
import com.sanlin.moviehub.retrofit.ApiRequest;
import com.sanlin.moviehub.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieRespository {

    private ApiRequest apiRequest;
    private String api_key = "your_api_key";
    private static final String TAG ="repo";

    public MovieRespository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<movieList> getMovieTrending(String mediaType, String timeWindow,int page){

        final MutableLiveData<movieList> data = new MutableLiveData<>();

        apiRequest.getMovietrending(mediaType,timeWindow,api_key,page).enqueue(new Callback<movieList>() {
            @Override
            public void onResponse(Call<movieList> call, Response<movieList> response) {

                Log.i(TAG, "onResponse: "+response);

                if(response.body()!=null){
                    data.setValue(response.body());
                    Log.i(TAG, "onResponse: list size "+response.body());
                }

            }

            @Override
            public void onFailure(Call<movieList> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
            }
        });

        return data;
    }

    public LiveData<MovieDetails> getMovieDetails(long id){

        final MutableLiveData<MovieDetails> data = new MutableLiveData<>();

        apiRequest.getMovieDetails(id,api_key).enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {

                Log.i(TAG, "onResponse: "+response);

                if(response.body()!=null){
                   data.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
            }
        });
        return data;
    }

    public LiveData<movieList> getMovieSimilar(long id){

        final MutableLiveData<movieList> data = new MutableLiveData<>();

        apiRequest.getMovieSimilar(id,api_key).enqueue(new Callback<movieList>() {
            @Override
            public void onResponse(Call<movieList> call, Response<movieList> response) {
                Log.i(TAG, "onResponse: "+response);
                if(response.body()!=null){
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<movieList> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
            }
        });
        return data;
    }

    public LiveData<movieList> getMovie(String category,int page){

        final MutableLiveData<movieList> data = new MutableLiveData<>();

        apiRequest.getMoviePopular(category,api_key,page).enqueue(new Callback<movieList>() {
            @Override
            public void onResponse(Call<movieList> call, Response<movieList> response) {
                Log.i(TAG, "onResponse: "+response);
                if(response.body()!=null){
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<movieList> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
            }
        });
        return data;
    }

    public LiveData<CreditsModel> getMovieCredits(long id){

        final MutableLiveData<CreditsModel> data = new MutableLiveData<>();

        apiRequest.getMovieCredits(id,api_key).enqueue(new Callback<CreditsModel>() {
            @Override
            public void onResponse(Call<CreditsModel> call, Response<CreditsModel> response) {
                data.setValue(response.body());
//                Log.i(TAG, "credits: "+response.body().getCast().get(0).getName());
            }

            @Override
            public void onFailure(Call<CreditsModel> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
            }
        });
        return data;
    }

    public LiveData<ReviewModelList> getMovieReviews(long id,int page){

        final MutableLiveData<ReviewModelList> data = new MutableLiveData<>();

        apiRequest.getMovieReviews(id,api_key,page).enqueue(new Callback<ReviewModelList>() {
            @Override
            public void onResponse(Call<ReviewModelList> call, Response<ReviewModelList> response) {
                data.setValue(response.body());
//                Log.i(TAG, "credits: "+response.body().getCast().get(0).getName());
            }

            @Override
            public void onFailure(Call<ReviewModelList> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
            }
        });
        return data;
    }

    public LiveData<PeopleModel> getMoviePeople(long id){

        final MutableLiveData<PeopleModel> data = new MutableLiveData<>();

        apiRequest.getMoviePeople(id,api_key).enqueue(new Callback<PeopleModel>() {
            @Override
            public void onResponse(Call<PeopleModel> call, Response<PeopleModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<PeopleModel> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
            }
        });

        return data;

    }

    public LiveData<movieList> getMovieSearch(String query,int page){
        final MutableLiveData<movieList> data = new MutableLiveData<>();

        apiRequest.getMovieSearch(query,api_key,page).enqueue(new Callback<movieList>() {
            @Override
            public void onResponse(Call<movieList> call, Response<movieList> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<movieList> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
            }
        });

        return data;

    }

}
