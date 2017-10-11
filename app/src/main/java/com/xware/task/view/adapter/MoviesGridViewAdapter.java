package com.xware.task.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xware.task.R;
import com.xware.task.data.models.Movie;
import com.xware.task.view.adapter.callbacks.HomeActivityCallbacks;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yasser on 09/10/17.
 */

public class MoviesGridViewAdapter extends RecyclerView.Adapter<MoviesGridViewAdapter.ViewHolder> {

    private ArrayList<Movie> movies;

    private LayoutInflater mInflater;

    private Context context;

    private HomeActivityCallbacks homeCallbacks;

    String baseUrl = "http://image.tmdb.org/t/p/w342";

    public MoviesGridViewAdapter(Context context, ArrayList<Movie> data, HomeActivityCallbacks homeCallbacks) {
        this.movies = data;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.homeCallbacks = homeCallbacks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cell_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }

    View.OnClickListener onMovieClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int currentPosition = (int) v.getTag();
            homeCallbacks.onMovieClick(movies.get(currentPosition).getId());
        }
    };

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_imageview_poster)
        ImageView moviePoster;
        @BindView(R.id.movie_textview_title)
        TextView movieTitle;
        @BindView(R.id.movie_textview_rating)
        TextView movieRating;

        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }

        public void bind(int position) {
            Picasso.with(context).load(baseUrl + movies.get(position).getPoster()).into(moviePoster);
            movieTitle.setText(movies.get(position).getMovieName());
            movieRating.setText(movies.get(position).getRating() + "");
            view.setTag(position);
            view.setOnClickListener(onMovieClickListener);
        }

    }

}
