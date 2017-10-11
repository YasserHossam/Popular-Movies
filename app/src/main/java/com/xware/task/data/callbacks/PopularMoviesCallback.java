package com.xware.task.data.callbacks;

import com.xware.task.data.models.Movie;

import java.util.ArrayList;

/**
 * Created by yasser on 09/10/17.
 */

public interface PopularMoviesCallback {
    void success(ArrayList<Movie> popularMovies);

    void error(String error);
}
