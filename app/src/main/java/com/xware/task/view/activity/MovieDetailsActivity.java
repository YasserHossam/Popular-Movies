package com.xware.task.view.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.xware.task.R;
import com.xware.task.data.models.Movie;
import com.xware.task.presenter.MovieDetailsPresenter;
import com.xware.task.view.adapter.MovieViewPagerAdapter;
import com.xware.task.view.contracts.IMovieDetailsView;
import com.xware.task.view.fragment.ReviewsFragment;
import com.xware.task.view.fragment.TrailersFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity implements IMovieDetailsView, TabLayout.OnTabSelectedListener {


    @BindView(R.id.movieview_viewPager)
    ViewPager viewPager;
    @BindView(R.id.movieview_tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.movieview_appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.movieview_toolbar)
    Toolbar movieViewToolbar;
    @BindView(R.id.movieview_imageview_poster)
    ImageView moviePosterImageView;
    @BindView(R.id.movieview_textview_date)
    TextView releaseDateTextView;
    @BindView(R.id.movieview_textview_genres)
    TextView genresTextView;
    @BindView(R.id.movieview_textview_language)
    TextView languageTextView;
    @BindView(R.id.movieview_textview_moviename)
    TextView movieNameTextView;
    @BindView(R.id.movieview_textview_rating)
    TextView rateTextView;
    @BindView(R.id.movieview_textview_ten)
    TextView tenTextView;
    @BindView(R.id.movieview_progressbar)
    ProgressBar progressBar;
    @BindView(R.id.movieview_btn_reload)
    Button reloadBtn;

    int movieId;

    MovieDetailsPresenter movieDetailsPresenter;

    String baseImageUrl = "http://image.tmdb.org/t/p/w500";

    MovieViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        movieDetailsPresenter = new MovieDetailsPresenter(this);

        movieId = getIntent().getIntExtra(HomeActivity.MOVIE_ID_KEY, 23586);

        initViewPagerAndTabs();

        movieDetailsPresenter.getMovieDetails(movieId);
        reloadBtn.setOnClickListener(reloadBtnClickListener);
    }

    private void initViewPagerAndTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("Trailers"));
        tabLayout.addTab(tabLayout.newTab().setText("Reviews"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(this);


        pagerAdapter = new MovieViewPagerAdapter(getSupportFragmentManager(), 2, movieId);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(2);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void showDetails(Movie movie) {
        Picasso.with(this).load(baseImageUrl + movie.getPoster()).placeholder(R.drawable.placeholder).into(moviePosterImageView);
        releaseDateTextView.setText(movie.getReleaseDate().substring(0, 4));
        for (int i = 0; i < movie.getGenres().size(); i++) {
            if (i != 0)
                genresTextView.setText(genresTextView.getText() + " | " + movie.getGenres().get(i).getName());
            else
                genresTextView.setText(movie.getGenres().get(i).getName());
        }
        languageTextView.setText(movie.getOriginalLanguage());
        movieNameTextView.setText(movie.getMovieName());
        rateTextView.setText(movie.getRating() + "");
        tenTextView.setVisibility(View.VISIBLE);
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
            movieDetailsPresenter.getMovieDetails(movieId);
            ((TrailersFragment) pagerAdapter.getRegisteredFragment(0)).getTrailersPresenter().getTrailers(movieId);
            ((ReviewsFragment) pagerAdapter.getRegisteredFragment(1)).getReviewsPresenter().getReviews(movieId);
            reloadBtn.setVisibility(View.INVISIBLE);
        }
    };
}

