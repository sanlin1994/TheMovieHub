package com.sanlin.moviehub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sanlin.moviehub.R;
import com.sanlin.moviehub.models.ReviewModel;

import java.util.ArrayList;
import java.util.List;

import at.blogc.android.views.ExpandableTextView;

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewAdapter.ReviewViewHolder> {

    private List<ReviewModel> reviewModelList = new ArrayList<>();
    private Context context;

    public MovieReviewAdapter(List<ReviewModel> reviewModelList, Context context) {
        this.reviewModelList = reviewModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieReviewAdapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item,parent,false);
        return new MovieReviewAdapter.ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {

        ReviewModel reviewModel = reviewModelList.get(position);
        holder.author.setText(reviewModel.getAuthor());
        holder.review.setText(reviewModel.getContent());

    }

    @Override
    public int getItemCount() {
        return reviewModelList.size();
    }


    public class ReviewViewHolder extends RecyclerView.ViewHolder {

        TextView author;
        ExpandableTextView review;
        ImageButton expand;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.review_item_author);
            review = itemView.findViewById(R.id.review_item_review);
            expand = itemView.findViewById(R.id.review_item_expand_button);

            review.setAnimationDuration(750L);
            review.setInterpolator(new OvershootInterpolator());
            review.setExpandInterpolator(new OvershootInterpolator());
            review.setCollapseInterpolator(new OvershootInterpolator());

            expand.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(final View v)
                {
                    if (review.isExpanded())
                    {
                        review.collapse();
                        expand.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
                    }
                    else
                    {
                        review.expand();
                        expand.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                    }
                }
            });


        }
    }


}
