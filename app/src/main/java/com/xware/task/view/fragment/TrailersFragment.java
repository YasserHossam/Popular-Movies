package com.xware.task.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xware.task.R;
import com.xware.task.data.models.Video;
import com.xware.task.presenter.TrailersPresenter;
import com.xware.task.view.adapter.TrailersAdapter;
import com.xware.task.view.contracts.ITrailersView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrailersFragment extends Fragment implements ITrailersView {

    public final static String MOVIE_ID_KEY = "TrailersFragment$MovieId";

    @BindView(R.id.recyclerview_trailers)
    RecyclerView trailersRecyclerView;
    @BindView(R.id.trailer_textview_error)
    TextView errorTextView;

    ArrayList<Video> allTrailers;

    TrailersPresenter trailersPresenter;

    public TrailersFragment() {
        // Required empty public constructor
    }

    public static TrailersFragment createInstance(int movieId) {
        TrailersFragment trailersFragment = new TrailersFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MOVIE_ID_KEY, movieId);
        trailersFragment.setArguments(bundle);
        return trailersFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trailers, container, false);
        ButterKnife.bind(this, view);
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView() {
        allTrailers = new ArrayList<>();
        trailersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        TrailersAdapter reviewsAdapter = new TrailersAdapter(allTrailers, getActivity());
        trailersRecyclerView.setAdapter(reviewsAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        trailersPresenter = new TrailersPresenter(this);
        int movieId = getArguments().getInt(MOVIE_ID_KEY, 1);
        trailersPresenter.getTrailers(movieId);

    }

    @Override
    public void showTrailers(ArrayList<Video> newTrailers) {
        errorTextView.setVisibility(View.INVISIBLE);
        allTrailers.clear();
        allTrailers.addAll(newTrailers);
        trailersRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMessage) {
        errorTextView.setVisibility(View.VISIBLE);
        errorTextView.setText(errorMessage);
    }

    public TrailersPresenter getTrailersPresenter() {
        return trailersPresenter;
    }
}
