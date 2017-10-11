package com.xware.task.view.contracts;

import com.xware.task.data.models.Video;

import java.util.ArrayList;

/**
 * Created by yasser on 10/10/17.
 */

public interface ITrailersView {
    void showTrailers(ArrayList<Video> trailers);

    void showError(String errorMessage);
}
