package com.xware.task.data.callbacks;

import com.xware.task.data.models.Review;

import java.util.ArrayList;

/**
 * Created by yasser on 09/10/17.
 */

public interface MovieReviewsCallback {
    void success(ArrayList<Review> reviews);

    void error(String error);
}
