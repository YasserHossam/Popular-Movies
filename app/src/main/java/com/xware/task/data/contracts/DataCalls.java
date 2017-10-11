package com.xware.task.data.contracts;

import com.xware.task.data.callbacks.MovieDetailsCallback;
import com.xware.task.data.callbacks.MovieReviewsCallback;
import com.xware.task.data.callbacks.MovieTrailersCallback;
import com.xware.task.data.callbacks.PopularMoviesCallback;

/**
 * Created by yasser on 09/10/17.
 */

public interface DataCalls {
    void getPopularMovies(int page, PopularMoviesCallback presenterCallback);

    void getMovieReviews(int movieID, MovieReviewsCallback movieReviewsCallback);

    void getMovieTrailers(int movieID, MovieTrailersCallback movieTrailersCallback);

    void getMovieDetails(int movieID, MovieDetailsCallback movieDetailsCallback);
}
