package com.practiceapps.donal.rottentomato.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.practiceapps.donal.rottentomato.*;
import com.practiceapps.donal.rottentomato.pojo.MyMovies.*;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by donal on 04/03/2015.
 */
public class InTheatresAdapter extends RecyclerView.Adapter <InTheatresAdapter.InTheatresViewHolder>{

    private ArrayList <Movie>  movieArrayList;
    private Context mContext;
    private final String NA = "NA";

    public InTheatresAdapter(Context context, ArrayList movieList){
        this.mContext = context;
        this.movieArrayList = movieList;
    }


    @Override
    public InTheatresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_movie_row, parent, false);
        InTheatresViewHolder inTheatresViewHolder = new InTheatresViewHolder(layout);
        return inTheatresViewHolder;
    }

    @Override
    public void onBindViewHolder(InTheatresViewHolder inTheatresViewHolder, int position) {

        Movie current = getMovieArrayList().get(position);

        inTheatresViewHolder.movieTitle.setText(current.getTitle());

        if (current.getReleaseDates().getTheater() != null) {
            inTheatresViewHolder.releaseDate.setText(current.getReleaseDates().getTheater().toString());
        } else {
            inTheatresViewHolder.releaseDate.setText(current.getYear().toString());
        }

        int audienceScore = current.getRatings().getAudienceScore();
        if (audienceScore == -1) {
            inTheatresViewHolder.ratingBar.setRating(0.0F);
            inTheatresViewHolder.ratingBar.setAlpha(0.5F);
        } else {
            inTheatresViewHolder.ratingBar.setRating(audienceScore / 20.0F);
            inTheatresViewHolder.ratingBar.setAlpha(1.0F);
        }

        String imgUrl = current.getPosters().getThumbnail();
//        Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);
        if (!imgUrl.equals(NA)){
            Picasso.with(mContext).load(imgUrl).into(inTheatresViewHolder.imageView);
        }



    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public ArrayList<Movie> getMovieArrayList() {
        return movieArrayList;
    }

    public void setMovieArrayList(ArrayList<Movie> movieArrayList) {
        this.movieArrayList = movieArrayList;
        notifyDataSetChanged();
    }

    public class InTheatresViewHolder extends RecyclerView.ViewHolder {

        // items in the custom_movie_row
        private TextView movieTitle;
        private RatingBar ratingBar;
        private ImageView imageView;
        private TextView releaseDate;


        public InTheatresViewHolder(View itemView) {
            super(itemView);

            movieTitle = (TextView) itemView.findViewById(R.id.movieTitle);
            releaseDate = (TextView) itemView.findViewById(R.id.movieReleaseDate);
            ratingBar = (RatingBar) itemView.findViewById(R.id.movieAudienceScore);
            imageView = (ImageView) itemView.findViewById(R.id.movieThumbnail);

        }
    }
}

