package com.xware.task.presenter;

import com.xware.task.data.Error.ErrorConstants;
import com.xware.task.data.callbacks.PopularMoviesCallback;
import com.xware.task.data.models.Movie;
import com.xware.task.data.network.DataCallsImpl;
import com.xware.task.presenter.contracts.IHomePresenter;
import com.xware.task.view.contracts.IHomeView;

import java.util.ArrayList;

/**
 * Created by yasser on 09/10/17.
 */

public class HomePresenter implements IHomePresenter {

    IHomeView homeView;

    public HomePresenter(IHomeView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void getPopularMovies(int page) {
        if (page == 1)
            homeView.showProgressBar();
        DataCallsImpl.getInstance().getPopularMovies(page, popularMoviesCallback);
    }

    PopularMoviesCallback popularMoviesCallback = new PopularMoviesCallback() {
        @Override
        public void success(ArrayList<Movie> popularMovies) {
            homeView.showMovies(popularMovies);
            homeView.hideProgressBar();
        }

        @Override
        public void error(String error) {
            homeView.showError(error);
            homeView.hideProgressBar();
            if (error.equals(ErrorConstants.NETWORK_ERROR))
                homeView.showReloadButton();
        }
    };
}
