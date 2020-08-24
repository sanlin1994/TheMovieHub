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
import android.widget.TextView;

import com.sanlin.moviehub.R;
import com.sanlin.moviehub.adapter.adapterVertical;
import com.sanlin.moviehub.models.MovieModel;
import com.sanlin.moviehub.models.movieList;
import com.sanlin.moviehub.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

public class movieListVertical extends AppCompatActivity {

    private List<MovieModel> MovieList = new ArrayList<>();
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private MovieViewModel movieViewModel;
    private String type;
    private adapterVertical adapter;
    private LinearLayoutManager linearLayoutManager;
    private int page = 1;
    private movieList movieList;
    private Boolean isLoading;
    TextView movieTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list_vertical);

        Toolbar myToolbar = findViewById(R.id.searchToolbar);
        setSupportActionBar(myToolbar);
        movieTitle = myToolbar.findViewById(R.id.movieTitle);
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

        adapter = new adapterVertical(MovieList,getApplicationContext());

        recyclerView = (RecyclerView) findViewById(R.id.recyclerVertical);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        type = getIntent().getStringExtra("type");
        String title = getIntent().getStringExtra("title");
        movieTitle.setText(title);
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        getData(page);

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
                movieList = new movieList();
                if (!isLoading && page!=movieList.getTotal_pages()) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE) {
                       getData(page++);
                    }
                }

            }
        });

    }

    private void getData(int page){
        isLoading = true;
        if(type.equals("all")) {
            movieViewModel.getMovieTrending("all", "week",page).observe(this, new Observer<movieList>() {
                @Override
                public void onChanged(movieList movieModels) {
                    progressDialog.dismiss();
                    MovieList.addAll(movieModels.getMovieList());
                    adapter.notifyDataSetChanged();
                    isLoading = false;
                }
            });
        }else{
            movieViewModel.getMovie(type,page).observe(this, new Observer<com.sanlin.moviehub.models.movieList>() {
                @Override
                public void onChanged(com.sanlin.moviehub.models.movieList movieList) {
                    progressDialog.dismiss();
                    MovieList.addAll(movieList.getMovieList());
                    adapter.notifyDataSetChanged();
                    isLoading = false;
                }
            });
        }

    }



}
