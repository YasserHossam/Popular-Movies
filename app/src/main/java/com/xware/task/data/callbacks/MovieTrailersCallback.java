package com.xware.task.data.callbacks;

import com.xware.task.data.models.Video;

import java.util.ArrayList;

/**
 * Created by yasser on 10/10/17.
 */

public interface MovieTrailersCallback {
    void success(ArrayList<Video> trailers);

    void error(String error);
}
