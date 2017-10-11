package com.xware.task.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.xware.task.R;
import com.xware.task.data.models.Movie;
import com.xware.task.presenter.HomePresenter;
import com.xware.task.view.adapter.MoviesGridViewAdapter;
import com.xware.task.view.adapter.callbacks.HomeActivityCallbacks;
import com.xware.task.view.contracts.IHomeView;
import com.xware.task.view.custom.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements IHomeView, HomeActivityCallbacks {

    @BindView(R.id.recyclerview_movies)
    RecyclerView moviesRecyclerView;
    @BindView(R.id.home_progressbar)
    ProgressBar progressBar;
    @BindView(R.id.home_btn_reload)
    Button reloadBtn;

    HomePresenter homePresenter;

    private EndlessRecyclerViewScrollListener scrollListener;

    MoviesGridViewAdapter moviesGridViewAdapter;

    ArrayList<Movie> allMovies;

    public static final String MOVIE_ID_KEY = "HomeActivity$MovieId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        homePresenter = new HomePresenter(this);

        setupRecyclerView();

        //Implement Endless Scroll
        setupScrollListener();

        homePresenter.getPopularMovies(1);

        reloadBtn.setOnClickListener(reloadBtnClickListener);
    }

    private void setupScrollListener() {
        scrollListener = new EndlessRecyclerViewScrollListener((GridLayoutManager) moviesRecyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                homePresenter.getPopularMovies(page);
            }
        };
        moviesRecyclerView.addOnScrollListener(scrollListener);
    }

    private void setupRecyclerView() {
        allMovies = new ArrayList<>();
        moviesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        moviesGridViewAdapter = new MoviesGridViewAdapter(this, allMovies, this);
        moviesRecyclerView.setAdapter(moviesGridViewAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void showMovies(ArrayList<Movie> newMovies) {
        int startIndex = allMovies.size();
        allMovies.addAll(newMovies);
        notifyInsertedItems(startIndex, newMovies.size());

    }

    private void notifyInsertedItems(int currentPosition, final int itemsCount) {
        final int firstItemPosition = currentPosition + 1;
        moviesRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                moviesRecyclerView.getAdapter().notifyItemRangeInserted(firstItemPosition, itemsCount);
            }
        });
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showReloadButton() {
        reloadBtn.setVisibility(View.VISIBLE);
        hideProgressBar();
    }

    View.OnClickListener reloadBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            homePresenter.getPopularMovies(1);
            reloadBtn.setVisibility(View.INVISIBLE);
        }
    };

    @Override
    public void onMovieClick(int movieId) {
        //Navigate to Movie Details Page
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(MOVIE_ID_KEY, movieId);
        startActivity(intent);
    }
}
