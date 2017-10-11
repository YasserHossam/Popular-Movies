package com.xware.task.data.network;

import com.xware.task.data.models.BaseResponse;
import com.xware.task.data.models.Movie;
import com.xware.task.data.models.Review;
import com.xware.task.data.models.Video;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by yasser on 09/10/17.
 */

public interface MoviesAPI {
    @GET("movie/popular")
    Call<BaseResponse<Movie>> getPopularMovies(@Query("page") int page, @Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Call<BaseResponse<Review>> getMovieReviews(@Path("id") int movieId, @Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Call<BaseResponse<Video>> getMovieTrailers(@Path("id") int movieId, @Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<Movie> getMovieDetails(@Path("id") int movieId, @Query("api_key") String apiKey);
}
