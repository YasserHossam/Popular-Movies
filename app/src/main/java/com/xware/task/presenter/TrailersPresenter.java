package com.xware.task.presenter;

import com.xware.task.data.callbacks.MovieTrailersCallback;
import com.xware.task.data.models.Video;
import com.xware.task.data.network.DataCallsImpl;
import com.xware.task.presenter.contracts.ITrailersPresenter;
import com.xware.task.view.contracts.ITrailersView;

import java.util.ArrayList;

/**
 * Created by yasser on 10/10/17.
 */

public class TrailersPresenter implements ITrailersPresenter {

    ITrailersView iTrailersView;

    public TrailersPresenter(ITrailersView iTrailersView) {
        this.iTrailersView = iTrailersView;
    }

    @Override
    public void getTrailers(int movieID) {
        DataCallsImpl.getInstance().getMovieTrailers(movieID, movieTrailersCallback);
    }

    MovieTrailersCallback movieTrailersCallback = new MovieTrailersCallback() {
        @Override
        public void success(ArrayList<Video> trailers) {
            if (trailers != null && trailers.size() != 0)
                iTrailersView.showTrailers(trailers);
            else
                error("No trailers for this movie");
        }

        @Override
        public void error(String error) {
            iTrailersView.showError(error);
        }
    };
}
