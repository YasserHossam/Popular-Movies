package com.xware.task.presenter;

import com.xware.task.data.Error.ErrorConstants;
import com.xware.task.data.callbacks.MovieDetailsCallback;
import com.xware.task.data.models.Movie;
import com.xware.task.data.network.DataCallsImpl;
import com.xware.task.presenter.contracts.IMovieDetailsPresenter;
import com.xware.task.view.contracts.IMovieDetailsView;

/**
 * Created by yasser on 11/10/17.
 */

public class MovieDetailsPresenter implements IMovieDetailsPresenter {

    private IMovieDetailsView iMovieDetailsView;

    public MovieDetailsPresenter(IMovieDetailsView iMovieDetailsView) {
        this.iMovieDetailsView = iMovieDetailsView;
    }

    @Override
    public void getMovieDetails(int movieId) {
        iMovieDetailsView.showProgressBar();
        DataCallsImpl.getInstance().getMovieDetails(movieId, movieDetailsCallback);
    }

    MovieDetailsCallback movieDetailsCallback = new MovieDetailsCallback() {
        @Override
        public void success(Movie movie) {
            iMovieDetailsView.hideProgressBar();
            iMovieDetailsView.showDetails(movie);
        }

        @Override
        public void error(String error) {
            iMovieDetailsView.hideProgressBar();
            iMovieDetailsView.showError(error);
            if (error.equals(ErrorConstants.NETWORK_ERROR))
                iMovieDetailsView.showReloadButton();
        }
    };
}
