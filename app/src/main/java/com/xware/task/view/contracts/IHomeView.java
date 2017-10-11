package com.xware.task.view.contracts;

import com.xware.task.data.models.Movie;

import java.util.ArrayList;

/**
 * Created by yasser on 09/10/17.
 */

public interface IHomeView {
    void showMovies(ArrayList<Movie> popularMovies);

    void showError(String errorMessage);

    void showProgressBar();

    void hideProgressBar();

    void showReloadButton();
}
