package com.xware.task.data.network;

import com.xware.task.data.Error.ErrorConstants;
import com.xware.task.data.callbacks.MovieDetailsCallback;
import com.xware.task.data.callbacks.MovieReviewsCallback;
import com.xware.task.data.callbacks.MovieTrailersCallback;
import com.xware.task.data.callbacks.PopularMoviesCallback;
import com.xware.task.data.contracts.DataCalls;
import com.xware.task.data.models.BaseResponse;
import com.xware.task.data.models.Movie;
import com.xware.task.data.models.Review;
import com.xware.task.data.models.Video;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yasser on 09/10/17.
 */

public class DataCallsImpl implements DataCalls {
    Retrofit retrofit;

    MoviesAPI moviesAPI;

    private String apiKey = "25b0a0d7fe8741fa2d6dfb81bd1cb9f6";

    private static DataCallsImpl dataCalls = new DataCallsImpl();

    private DataCallsImpl() {
        retrofit = new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        moviesAPI = retrofit.create(MoviesAPI.class);
    }

    public static DataCallsImpl getInstance() {
        return dataCalls;
    }

    @Override
    public void getPopularMovies(int page, final PopularMoviesCallback presenterCallback) {

        Call<BaseResponse<Movie>> call = moviesAPI.getPopularMovies(page, apiKey);

        call.enqueue(new Callback<BaseResponse<Movie>>() {
            @Override
            public void onResponse(Call<BaseResponse<Movie>> call, Response<BaseResponse<Movie>> response) {
                presenterCallback.success(response.body().getResults());
            }

            @Override
            public void onFailure(Call<BaseResponse<Movie>> call, Throwable t) {
                if (t instanceof java.net.UnknownHostException)
                    presenterCallback.error(ErrorConstants.NETWORK_ERROR);
            }
        });
    }

    @Override
    public void getMovieReviews(int movieID, final MovieReviewsCallback movieReviewsCallback) {
        Call<BaseResponse<Review>> call = moviesAPI.getMovieReviews(movieID, apiKey);

        call.enqueue(new Callback<BaseResponse<Review>>() {
            @Override
            public void onResponse(Call<BaseResponse<Review>> call, Response<BaseResponse<Review>> response) {
                movieReviewsCallback.success(response.body().getResults());
            }

            @Override
            public void onFailure(Call<BaseResponse<Review>> call, Throwable t) {
            }
        });
    }

    @Override
    public void getMovieTrailers(int movieID, final MovieTrailersCallback movieTrailersCallback) {
        Call<BaseResponse<Video>> call = moviesAPI.getMovieTrailers(movieID, apiKey);

        call.enqueue(new Callback<BaseResponse<Video>>() {
            @Override
            public void onResponse(Call<BaseResponse<Video>> call, Response<BaseResponse<Video>> response) {
                movieTrailersCallback.success(response.body().getResults());
            }

            @Override
            public void onFailure(Call<BaseResponse<Video>> call, Throwable t) {
            }
        });
    }

    @Override
    public void getMovieDetails(int movieID, final MovieDetailsCallback movieDetailsCallback) {
        Call<Movie> call = moviesAPI.getMovieDetails(movieID, apiKey);

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movieDetailsCallback.success(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                if (t instanceof java.net.UnknownHostException)
                    movieDetailsCallback.error(ErrorConstants.NETWORK_ERROR);
            }
        });
    }
}
