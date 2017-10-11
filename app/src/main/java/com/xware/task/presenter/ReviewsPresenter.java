package com.xware.task.presenter;

import com.xware.task.data.callbacks.MovieReviewsCallback;
import com.xware.task.data.models.Review;
import com.xware.task.data.network.DataCallsImpl;
import com.xware.task.presenter.contracts.IReviewsPresenter;
import com.xware.task.view.contracts.IReviewsView;

import java.util.ArrayList;

/**
 * Created by yasser on 10/10/17.
 */

public class ReviewsPresenter implements IReviewsPresenter {

    IReviewsView iReviewsView;

    public ReviewsPresenter(IReviewsView iReviewsView) {
        this.iReviewsView = iReviewsView;
    }

    @Override
    public void getReviews(int movieID) {
        DataCallsImpl.getInstance().getMovieReviews(movieID, movieReviewsCallback);
    }

    MovieReviewsCallback movieReviewsCallback = new MovieReviewsCallback() {
        @Override
        public void success(ArrayList<Review> reviews) {
            if (reviews != null && reviews.size() != 0)
                iReviewsView.showReviews(reviews);
            else
                error("No reviews for this movie");
        }

        @Override
        public void error(String error) {
            iReviewsView.showError(error);
        }
    };
}
