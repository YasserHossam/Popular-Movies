package com.xware.task.data.callbacks;

import com.xware.task.data.models.Movie;

/**
 * Created by yasser on 11/10/17.
 */

public interface MovieDetailsCallback {
    void success(Movie movie);

    void error(String error);
}
