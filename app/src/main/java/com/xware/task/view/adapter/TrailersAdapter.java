package com.xware.task.view.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xware.task.R;
import com.xware.task.data.models.Video;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yasser on 10/10/17.
 */

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.ViewHolder> {
    private ArrayList<Video> trailers;

    private LayoutInflater mInflater;

    private Context context;

    private static final String VIEW_TAG = "ViewTag$ViewPosition";

    String baseUrl = "https://img.youtube.com/vi/";
    String size = "/sddefault.jpg";

    public TrailersAdapter(ArrayList<Video> videos, Context context) {
        this.trailers = videos;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public TrailersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_trailer, parent, false);
        return new TrailersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrailersAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    View.OnClickListener onTrailerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int currentPosition = (int) v.getTag();
            watchYoutubeVideo(trailers.get(currentPosition).getKey());
        }
    };

    private void watchYoutubeVideo(String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.trailer_imageview_thumbnail)
        ImageView thumbnail;
        @BindView(R.id.trailer_textview_name)
        TextView name;

        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }

        public void bind(int position) {
            Picasso.with(context).load(baseUrl + trailers.get(position).getKey() + size).placeholder(R.drawable.placeholder).into(thumbnail);
            name.setText(trailers.get(position).getVideoName());
            itemView.setTag(position);
            itemView.setOnClickListener(onTrailerClickListener);
        }
    }
}
