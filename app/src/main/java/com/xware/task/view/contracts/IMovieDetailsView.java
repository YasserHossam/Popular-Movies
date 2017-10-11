package com.xware.task.view.contracts;

import com.xware.task.data.models.Movie;

/**
 * Created by yasser on 11/10/17.
 */

public interface IMovieDetailsView {
    void showDetails(Movie movie);

    void showError(String errorMessage);

    void showProgressBar();

    void hideProgressBar();

    void showReloadButton();
}
