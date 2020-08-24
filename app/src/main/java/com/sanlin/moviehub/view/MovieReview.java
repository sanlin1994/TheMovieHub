package com.sanlin.moviehub.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sanlin.moviehub.R;
import com.sanlin.moviehub.adapter.MovieReviewAdapter;
import com.sanlin.moviehub.models.ReviewModel;
import com.sanlin.moviehub.models.ReviewModelList;
import com.sanlin.moviehub.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

public class MovieReview extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ReviewModel> reviewModel = new ArrayList<>();
    MovieViewModel movieViewModel;
    private ProgressDialog progressDialog;
    private long movie_id;
    private int page = 1;
    private Boolean isLoading;
    private ReviewModelList reviewModelList;
    MovieReviewAdapter movieReviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_review);

        Toolbar myToolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(myToolbar);
        ImageView back = myToolbar.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("please wait...");
        progressDialog.show();

        movie_id = getIntent().getLongExtra("id",000);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        movieReviewAdapter = new MovieReviewAdapter(reviewModel,getApplicationContext());

        recyclerView = findViewById(R.id.ReviewRecycler);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(movieReviewAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                reviewModelList = new ReviewModelList();
                if (!isLoading && page!=reviewModelList.getTotal_pages()) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE) {
                        getData(page++);
                    }
                }

            }
        });

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        getData(page);

    }

    private void getData(int page){
        isLoading = true;
            movieViewModel.getMovieReviews(movie_id,page).observe(this,new Observer<ReviewModelList>() {
                @Override
                public void onChanged(ReviewModelList reviewModelList) {
                    progressDialog.dismiss();
                    reviewModel.addAll(reviewModelList.getResults());
                    movieReviewAdapter.notifyDataSetChanged();
                    isLoading = false;
                }
            });
        }

}
