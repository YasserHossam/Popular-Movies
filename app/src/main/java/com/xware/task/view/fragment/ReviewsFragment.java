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
import com.xware.task.data.models.Review;
import com.xware.task.presenter.ReviewsPresenter;
import com.xware.task.view.adapter.ReviewsAdapter;
import com.xware.task.view.contracts.IReviewsView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewsFragment extends Fragment implements IReviewsView {

    public final static String MOVIE_ID_KEY = "ReviewsFragment$MovieId";

    @BindView(R.id.recyclerview_reviews)
    RecyclerView reviewsRecyclerview;
    @BindView(R.id.review_textview_error)
    TextView errorTextView;

    ArrayList<Review> allReviews;

    ReviewsPresenter reviewsPresenter;

    public ReviewsFragment() {
        // Required empty public constructor
    }

    public static ReviewsFragment createInstance(int movieId) {
        ReviewsFragment reviewsFragment = new ReviewsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MOVIE_ID_KEY, movieId);
        reviewsFragment.setArguments(bundle);
        return reviewsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        ButterKnife.bind(this, view);
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView() {
        allReviews = new ArrayList<>();
        reviewsRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        ReviewsAdapter reviewsAdapter = new ReviewsAdapter(allReviews, getActivity());
        reviewsRecyclerview.setAdapter(reviewsAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        reviewsPresenter = new ReviewsPresenter(this);
        int movieId = getArguments().getInt(MOVIE_ID_KEY, 1);
        reviewsPresenter.getReviews(movieId);
    }

    @Override
    public void showReviews(ArrayList<Review> newReviews) {
        errorTextView.setVisibility(View.INVISIBLE);
        allReviews.clear();
        allReviews.addAll(newReviews);
        reviewsRecyclerview.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMessage) {
        errorTextView.setText(errorMessage);
        errorTextView.setVisibility(View.VISIBLE);
    }

    public ReviewsPresenter getReviewsPresenter() {
        return reviewsPresenter;
    }
}
