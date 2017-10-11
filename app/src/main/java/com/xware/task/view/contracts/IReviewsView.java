package com.xware.task.view.contracts;

import com.xware.task.data.models.Review;

import java.util.ArrayList;

/**
 * Created by yasser on 10/10/17.
 */

public interface IReviewsView {
    void showReviews(ArrayList<Review> reviews);

    void showError(String errorMessage);
}
