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

import com.bumptech.glide.Glide;
import com.sanlin.moviehub.R;
import com.sanlin.moviehub.models.CastModel;
import com.sanlin.moviehub.models.MovieModel;
import com.sanlin.moviehub.view.MovieDetails;
import com.sanlin.moviehub.view.PeopleDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    List<MovieModel> articleArrayList;
    List<CastModel> castModelList= new ArrayList<>();
    private static int TYPE_POSTER = 1;
    private static int TYPE_BANNER = 2;
    private static int TYPE_CAST = 3;
    private int type;

    public MovieArticleAdapter(Context context, List<MovieModel> articleArrayList,List<CastModel> castModelList,int type) {
        this.context = context;
        this.articleArrayList = articleArrayList;
        this.type = type;
        this.castModelList = castModelList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if (type == TYPE_POSTER) { // for call layout
            view = LayoutInflater.from(context).inflate(R.layout.poster_item, viewGroup, false);
            return new PosterViewHolder(view);
        } else if(type == TYPE_BANNER){ // for email layout
            view = LayoutInflater.from(context).inflate(R.layout.wide_poster_item, viewGroup, false);
            return new BannerViewHolder(view);
        } else{
            view = LayoutInflater.from(context).inflate(R.layout.cast_item, viewGroup, false);
            return new CastViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {

        if(type == TYPE_POSTER)
            ((PosterViewHolder) viewHolder).SetPosterDetails(i);
        else if(type == TYPE_BANNER)
            ((BannerViewHolder) viewHolder).setBannerDetails(i);
        else
            ((CastViewHolder) viewHolder).setCastDetails(i);

    }

    @Override
    public int getItemCount() {
        if(type == TYPE_POSTER)
            return articleArrayList.size();
        else if(type == TYPE_BANNER)
            return articleArrayList.size();
        else
            return castModelList.size();
    }

    public class PosterViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgViewCover;
        private final TextView rating;

        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            rating = (TextView) itemView.findViewById(R.id.rating_text);
            imgViewCover=(ImageView) itemView.findViewById(R.id.imgViewCover);
        }

        void SetPosterDetails(final int i){
            String poster_base_url = "https://image.tmdb.org/t/p/w185";

            MovieModel article=articleArrayList.get(i);
            rating.setText(String.valueOf(article.getVote_average()));
            Glide.with(context)
                    .load(poster_base_url+article.getPoster_path())
                    .into(imgViewCover);

            imgViewCover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MovieDetails.class);
                    intent.putExtra("id",articleArrayList.get(i).getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder{
        String poster_base_url = "https://image.tmdb.org/t/p/w500";
        private TextView title,date,popularity;
        private ImageView banner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.upcommingTitle);
            popularity = (TextView) itemView.findViewById(R.id.upcommingPopularity);
            date = (TextView) itemView.findViewById(R.id.upcommingDate);
            banner = (ImageView) itemView.findViewById(R.id.wide_poster);
        }

        void setBannerDetails(final int i){

            title.setText(articleArrayList.get(i).getTitle());
            popularity.setText(String.valueOf(articleArrayList.get(i).getPopularity()));
            date.setText(articleArrayList.get(i).getRelease_date());
            Picasso.get().load(poster_base_url+articleArrayList.get(i).getBackdrop_path()).into(banner);

            banner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MovieDetails.class);
                    intent.putExtra("id",articleArrayList.get(i).getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });

        }
    }

    public class CastViewHolder extends RecyclerView.ViewHolder{
        String poster_base_url = "https://image.tmdb.org/t/p/w185";
        private TextView castName,castCharacter;
        private ImageView castProfile;

        public CastViewHolder(@NonNull View itemView) {
            super(itemView);
            castProfile = (ImageView) itemView.findViewById(R.id.castImage);
            castName = (TextView) itemView.findViewById(R.id.castName);
            castCharacter = (TextView) itemView.findViewById(R.id.castCharacterName);
        }

        void setCastDetails(final int i){

            castName.setText(castModelList.get(i).getName());
            castCharacter.setText(castModelList.get(i).getCharacter());
            Picasso.get().load(poster_base_url+castModelList.get(i).getProfile_path()).into(castProfile);

            castProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long id = castModelList.get(i).getId();
                    Intent intent = new Intent(context, PeopleDetails.class);
                    intent.putExtra("id",id);
                    context.startActivity(intent);
                }
            });


        }

    }

    @Override
    public int getItemViewType(int position) {
        if(type == 1)
            return TYPE_POSTER;
        else if(type==2)
            return TYPE_BANNER;
        else return TYPE_CAST;
    }
}