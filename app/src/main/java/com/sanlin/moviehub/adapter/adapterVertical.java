package com.sanlin.moviehub.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sanlin.moviehub.R;
import com.sanlin.moviehub.models.MovieModel;
import com.sanlin.moviehub.view.MovieDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class adapterVertical extends RecyclerView.Adapter<adapterVertical.movieviewHolder>{

    private List<MovieModel> movieList = new ArrayList<>();
    private Context context;

    public adapterVertical(List<MovieModel> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public movieviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_movie_item,parent,false);

        return new movieviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull movieviewHolder holder, final int position) {
        String poster_base_url = "https://image.tmdb.org/t/p/w185";
        holder.title.setText(movieList.get(position).getTitle());
        holder.release_date.setText(movieList.get(position).getRelease_date());
        holder.popularity.setText(String.valueOf(movieList.get(position).getPopularity()));
        holder.rating.setText(String.valueOf(movieList.get(position).getVote_average()));
        Picasso.get().load(poster_base_url+movieList.get(position).getPoster_path()).into(holder.poster);

        holder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MovieDetails.class);
                intent.putExtra("id",movieList.get(position).getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class movieviewHolder extends RecyclerView.ViewHolder{

        private TextView title,genre,release_date,popularity,rating;
        private ImageView poster;

        public movieviewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.movieTitle);
            genre = (TextView) itemView.findViewById(R.id.movieGenre);
            release_date = (TextView) itemView.findViewById(R.id.movieReleaseDate);
            popularity = (TextView) itemView.findViewById(R.id.moviePopularity);
            rating = (TextView) itemView.findViewById(R.id.movieRating);
            poster = (ImageView) itemView.findViewById(R.id.moviePoster);

        }
    }

}
