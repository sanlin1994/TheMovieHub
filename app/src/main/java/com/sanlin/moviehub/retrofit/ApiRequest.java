package com.sanlin.moviehub.retrofit;

import com.sanlin.moviehub.models.CreditsModel;
import com.sanlin.moviehub.models.MovieDetails;
import com.sanlin.moviehub.models.PeopleModel;
import com.sanlin.moviehub.models.ReviewModelList;
import com.sanlin.moviehub.models.movieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiRequest {

    @GET("trending/{media_type}/{time_window}")
    Call<movieList> getMovietrending(@Path("media_type") String mediaType,
                                     @Path("time_window") String timeWindow,
                                     @Query("api_key") String apiKey,
                                     @Query("page") int page
                                            );

    @GET("movie/{id}")
    Call<MovieDetails> getMovieDetails(@Path("id") long id,@Query("api_key") String apiKey);

    @GET("movie/{id}/similar")
    Call<movieList> getMovieSimilar(@Path("id") long id,@Query("api_key") String apiKey);

    @GET("movie/{category}")
    Call<movieList> getMoviePopular(@Path("category") String category,@Query("api_key") String apiKey,@Query("page") int page);

    @GET("movie/{id}/credits")
    Call<CreditsModel> getMovieCredits(@Path("id") long id, @Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Call<ReviewModelList> getMovieReviews(@Path("id") long id, @Query("api_key") String apiKey,@Query("page") int page);

    @GET("person/{person_id}")
    Call<PeopleModel> getMoviePeople(@Path("person_id") long person_id, @Query("api_key") String apiKey);

    @GET("search/multi")
    Call<movieList> getMovieSearch(@Query("query") String query,@Query("api_key") String apiKey,@Query("page") int page);


}
